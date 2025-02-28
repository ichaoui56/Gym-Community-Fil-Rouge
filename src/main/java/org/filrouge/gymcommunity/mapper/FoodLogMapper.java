package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.foodLog.FoodLogReqDTO;
import org.filrouge.gymcommunity.dto.foodLog.FoodLogResDTO;
import org.filrouge.gymcommunity.model.entity.FoodLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodLogMapper extends GenericMapper<FoodLog, FoodLogResDTO, FoodLogReqDTO>{
}
