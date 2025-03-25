package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.UserNutrition;

import java.util.Optional;

public interface UserNutritionRepository extends GenericRepository<UserNutrition, Integer>{
    Optional<UserNutrition> findByUser_Id(Integer userId);
}
