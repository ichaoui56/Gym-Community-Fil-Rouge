package org.filrouge.gymcommunity.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.filrouge.gymcommunity.dto.foodLog.FoodLogReqDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Food;
import org.filrouge.gymcommunity.model.entity.FoodLog;
import org.filrouge.gymcommunity.repository.FoodRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class FoodAspect {

    private final SecurityHelper securityHelper;
    private final FoodRepository foodRepository;

    /**
     * Sets the author of a FoodLog entity before persisting it.
     */
    @Around("execution(* org.filrouge.gymcommunity.mapper.FoodLogMapper.fromRequestDTO(..)) && args(foodLogReqDTO)")
    public Object setFoodLogAuthorAndFoodId(ProceedingJoinPoint joinPoint, FoodLogReqDTO foodLogReqDTO) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof FoodLog foodLog) {
            if (foodLog.getId() == null) {
                AppUser user = securityHelper.getAuthenticatedUser();
                foodLog.setUser(user);
            }

            Optional<Food> foodOptional = foodRepository.findByName(foodLogReqDTO.foodName());
            System.out.println("food is here: " + foodOptional);
            foodOptional.ifPresent(foodLog::setFood);
        }

        return result;
    }

}
