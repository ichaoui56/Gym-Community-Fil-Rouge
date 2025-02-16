package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.admin.AdminReqDTO;
import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper extends GenericMapper<Admin, AdminResDTO, AdminReqDTO>{
}
