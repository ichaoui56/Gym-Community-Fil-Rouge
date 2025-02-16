package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.exception.UserAlreadyExistsException;
import org.filrouge.gymcommunity.exception.UserPhoneAlreadyExistsException;
import org.filrouge.gymcommunity.helper.calculeNutrition.CalorieCalculationResult;
import org.filrouge.gymcommunity.helper.calculeNutrition.CalorieCalculator;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.UserNutrition;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ThreadLocal to hold the calculation result between advices
    private static final ThreadLocal<CalorieCalculationResult> resultHolder = new ThreadLocal<>();

    @Around("execution(* org.filrouge.gymcommunity.service.crud.CreateService.create(..)) && args(requestDTO)")
    public Object validateUserCreation(ProceedingJoinPoint joinPoint, UserReqDTO requestDTO) throws Throwable {
        System.out.println("Aspect triggered: Validating user creation for " + requestDTO.email());

        if (userRepository.existsByEmail(requestDTO.email())) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        if (userRepository.existsByPhone(requestDTO.phone())) {
            throw new UserPhoneAlreadyExistsException("Phone already exists");
        }

        System.out.println("Validation passed, proceeding with user creation.");
        return joinPoint.proceed();
    }

    @Around("execution(* org.filrouge.gymcommunity.service.crud.CreateService.create(..)) && args(requestDTO)")
    public Object calculateTheBMR(ProceedingJoinPoint joinPoint, UserNutritionReqDTO requestDTO) throws Throwable {
        System.out.println("Calculating BMR for UserNutrition");

        CalorieCalculationResult result = CalorieCalculator.calculate(
                requestDTO.gender(),
                requestDTO.weight(),
                requestDTO.height(),
                requestDTO.age(),
                requestDTO.activityLevel(),
                requestDTO.targetDate(),
                requestDTO.targetWeight(),
                requestDTO.goal(),
                requestDTO.workoutLevel(),
                requestDTO.eatingStyle()
        );

        try {
            resultHolder.set(result);
            return joinPoint.proceed();
        } finally {
            resultHolder.remove();
        }
    }

    @Before("execution(* org.filrouge.gymcommunity.repository.GenericRepository.save(..)) && args(entity)")
    public void encodePassword(Object entity) {
        if (entity instanceof AppUser appUser && appUser.getId() == null) {
            if (appUser.getPassword() != null && !appUser.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(appUser.getPassword());
                appUser.setPassword(encodedPassword);
                System.out.println("Password encoded for user: " + appUser.getEmail());
            }
        } else if (entity instanceof Admin admin && admin.getId() == null) {
            if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(admin.getPassword());
                admin.setPassword(encodedPassword);
                System.out.println("Password encoded for user: " + admin.getEmail());
            }
        }
    }

    @Before("execution(* org.filrouge.gymcommunity.repository.GenericRepository.save(..)) && args(entity)")
    public void settingUserNutrition(Object entity) {
        if (entity instanceof UserNutrition userNutrition) {
            CalorieCalculationResult result = resultHolder.get();
            if (result != null) {
                // Set calculated nutrition values
                userNutrition.setDailyCalorieGoal((float) result.getDailyCalories());
                userNutrition.setProteins((float) result.getProteins());
                userNutrition.setCarbs((float) result.getCarbs());
                userNutrition.setFats((float) result.getFats());

                // Set the current user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();
                AppUser user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                userNutrition.setUser(user);

                System.out.println("Set UserNutrition values for user: " + email);
            }
        }
    }
}