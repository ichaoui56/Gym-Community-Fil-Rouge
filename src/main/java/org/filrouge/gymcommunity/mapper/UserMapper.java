package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.user.UserReqDTO;
import org.filrouge.gymcommunity.dto.user.UserResDTO;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<AppUser, UserResDTO, UserReqDTO> {
}
