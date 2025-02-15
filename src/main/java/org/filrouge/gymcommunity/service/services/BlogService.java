package org.filrouge.gymcommunity.service.services;

import lombok.RequiredArgsConstructor;
import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.blog.BlogResDTO;
import org.filrouge.gymcommunity.mapper.BlogMapper;
import org.filrouge.gymcommunity.mapper.GenericMapper;
import org.filrouge.gymcommunity.model.entity.AppUser;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.filrouge.gymcommunity.repository.BlogRepository;
import org.filrouge.gymcommunity.repository.GenericRepository;
import org.filrouge.gymcommunity.repository.UserRepository;
import org.filrouge.gymcommunity.service.GenericServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService extends GenericServiceImpl<BlogResDTO, BlogReqDTO, Blog, Integer> {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final BlogMapper blogMapper;

    @Override
    public GenericRepository<Blog, Integer> getRepository() {
        return this.blogRepository;
    }

    @Override
    public GenericMapper<Blog, BlogResDTO, BlogReqDTO> getMapper() {
        return this.blogMapper;
    }

    @Override
    public BlogResDTO create(BlogReqDTO blogReqDTO){
        Blog blog = blogMapper.fromRequestDTO(blogReqDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        blog.setAuthor(user);

        Blog savedBlog = blogRepository.save(blog);
        return blogMapper.toResponseDTO(savedBlog);
    }
}
