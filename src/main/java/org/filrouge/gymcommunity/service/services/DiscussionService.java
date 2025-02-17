package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.discussion.DiscussionReqDTO;
import org.filrouge.gymcommunity.dto.discussion.DiscussionResDTO;
import org.filrouge.gymcommunity.mapper.DiscussionMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Discussion;
import org.filrouge.gymcommunity.repository.DiscussionRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscussionService extends GenericServiceImpl<DiscussionResDTO, DiscussionReqDTO, Discussion, Integer> {

    private final DiscussionMapper discussionMapper;
    private final DiscussionRepository discussionRepository;

    @Override
    public GenericRepository<Discussion, Integer> getRepository() {
        return discussionRepository;
    }

    @Override
    public GenericMapper<Discussion, DiscussionResDTO, DiscussionReqDTO> getMapper() {
        return discussionMapper;
    }
}
