package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.admin.AdminReqDTO;
import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.mapper.AdminMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.repository.AdminRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService extends GenericServiceImpl<AdminResDTO, AdminReqDTO, Admin, Integer> {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public GenericRepository<Admin, Integer> getRepository() {
        return adminRepository;
    }

    @Override
    public GenericMapper<Admin, AdminResDTO, AdminReqDTO> getMapper() {
        return adminMapper;
    }
}
