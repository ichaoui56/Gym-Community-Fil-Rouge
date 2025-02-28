package org.filrouge.gymcommunity.repository;

import org.filrouge.gymcommunity.model.entity.Food;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends GenericRepository<Food, Integer>{
    Optional<Food> findByName(String name);
}
