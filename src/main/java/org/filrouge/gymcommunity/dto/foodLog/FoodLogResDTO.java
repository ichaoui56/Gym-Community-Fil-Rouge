package org.filrouge.gymcommunity.dto.foodLog;

import org.filrouge.gymcommunity.model.entity.Food;

public record FoodLogResDTO(
    Integer id,
    Food food
) {
}
