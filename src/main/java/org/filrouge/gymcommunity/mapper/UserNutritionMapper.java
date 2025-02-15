package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.userNutr.UserNutritionReqDTO;
import org.filrouge.gymcommunity.dto.userNutr.UserNutritionResDTO;
import org.filrouge.gymcommunity.model.entity.UserNutrition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserNutritionMapper extends GenericMapper<UserNutrition, UserNutritionResDTO, UserNutritionReqDTO>{
}
