package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.blog.BlogResDTO;
import org.filrouge.gymcommunity.mapper.BlogMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.filrouge.gymcommunity.repository.BlogRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService extends GenericServiceImpl<BlogResDTO, BlogReqDTO, Blog, Integer> {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    @Override
    public GenericRepository<Blog, Integer> getRepository() {
        return blogRepository;
    }

    @Override
    public GenericMapper<Blog, BlogResDTO, BlogReqDTO> getMapper() {
        return blogMapper;
    }
}
