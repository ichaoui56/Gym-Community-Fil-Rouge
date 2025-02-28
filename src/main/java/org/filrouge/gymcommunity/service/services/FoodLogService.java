package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.foodLog.FoodLogReqDTO;
import org.filrouge.gymcommunity.dto.foodLog.FoodLogResDTO;
import org.filrouge.gymcommunity.helper.SecurityHelper;
import org.filrouge.gymcommunity.mapper.FoodLogMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.FoodLog;
import org.filrouge.gymcommunity.repository.FoodLogRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodLogService extends GenericServiceImpl<FoodLogResDTO, FoodLogReqDTO, FoodLog, Integer> {

    public final FoodLogRepository foodLogRepository;
    public final FoodLogMapper foodLogMapper;

    @Override
    public GenericRepository<FoodLog, Integer> getRepository() {
        return foodLogRepository;
    }

    @Override
    public GenericMapper<FoodLog, FoodLogResDTO, FoodLogReqDTO> getMapper() {
        return foodLogMapper;
    }

}
