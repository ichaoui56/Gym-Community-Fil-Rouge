package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
import org.filrouge.gymcommunity.helper.calculeNutrition.CalorieCalculationResult;
import org.filrouge.gymcommunity.helper.calculeNutrition.CalorieCalculator;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.mapper.UserNutritionMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.UserNutrition;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.UserNutritionRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserNutritionService extends GenericServiceImpl<UserNutritionResDTO, UserNutritionReqDTO, UserNutrition, Integer> {

    public final UserNutritionRepository userNutritionRepository;
    public final UserNutritionMapper userNutritionMapper;
    private final UserRepository userRepository;

    @Override
    public GenericRepository<UserNutrition, Integer> getRepository() {
        return userNutritionRepository;
    }

    @Override
    public GenericMapper<UserNutrition, UserNutritionResDTO, UserNutritionReqDTO> getMapper() {
        return userNutritionMapper;
    }

}
