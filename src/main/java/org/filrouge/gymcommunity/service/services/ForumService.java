package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumResDTO;
import org.filrouge.gymcommunity.mapper.ForumMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.repository.ForumRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumService extends GenericServiceImpl<ForumResDTO, ForumReqDTO, Forum, Integer> {

    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;

    @Override
    public GenericRepository<Forum, Integer> getRepository() {
        return forumRepository;
    }

    @Override
    public GenericMapper<Forum, ForumResDTO, ForumReqDTO> getMapper() {
        return forumMapper;
    }
}
