package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.foodLog.FoodLogReqDTO;
import org.filrouge.gymcommunity.dto.foodLog.FoodLogResDTO;
import org.filrouge.gymcommunity.model.entity.FoodLog;
import org.filrouge.gymcommunity.service.services.FoodLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foodLog")
public class FoodLogController extends GenericController<FoodLogResDTO, FoodLogReqDTO, FoodLog, Integer>{

    public FoodLogController(FoodLogService foodLogService){
        super(foodLogService, FoodLog.class);
    }

}
