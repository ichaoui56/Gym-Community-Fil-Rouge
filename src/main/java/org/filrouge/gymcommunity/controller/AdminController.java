package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.admin.AdminReqDTO;
import org.filrouge.gymcommunity.dto.admin.AdminResDTO;
import org.filrouge.gymcommunity.model.entity.Admin;
import org.filrouge.gymcommunity.service.services.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController extends GenericController<AdminResDTO, AdminReqDTO, Admin, Integer>{
    public AdminController(AdminService adminService){
        super(adminService, Admin.class);
    }
}
