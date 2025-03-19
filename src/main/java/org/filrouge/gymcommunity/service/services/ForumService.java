package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.forum.ForumReqDTO;
import org.filrouge.gymcommunity.dto.forum.ForumResDTO;
import org.filrouge.gymcommunity.mapper.ForumMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Forum;
import org.filrouge.gymcommunity.repository.ForumRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumService extends GenericServiceImpl<ForumResDTO, ForumReqDTO, Forum, Integer> {

    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;
    private final UserRepository userRepository;

    @Override
    public GenericRepository<Forum, Integer> getRepository() {
        return forumRepository;
    }

    @Override
    public GenericMapper<Forum, ForumResDTO, ForumReqDTO> getMapper() {
        return forumMapper;
    }

    public ForumResDTO joinForum(Integer forumId, Integer userId) {
        Forum forum = forumRepository.findById(forumId)
                .orElseThrow(() -> new UsernameNotFoundException("Forum not found"));
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        forum.getMembers().add(user);
        forum.setMember(true);
        forumRepository.save(forum);
        System.out.println("test"+forum.toString());
        return forumMapper.toResponseDTO(forum);
    }

    public ForumResDTO leaveForum(Integer forumId, Integer userId) {
        Forum forum = forumRepository.findById(forumId)
                .orElseThrow(() -> new UsernameNotFoundException("Forum not found"));
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        forum.getMembers().remove(user);
        forumRepository.save(forum);
        System.out.println("test2"+forum.toString());
        forumRepository.save(forum);
        return forumMapper.toResponseDTO(forum);
    }
}
