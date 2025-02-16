package org.filrouge.gymcommunity.controller;

import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.blog.BlogResDTO;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.filrouge.gymcommunity.service.CrudService;
import org.filrouge.gymcommunity.service.services.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog")
public class BlogController extends GenericController<BlogResDTO, BlogReqDTO, Blog, Integer>{
    public BlogController(BlogService blogService) {
        super(blogService, Blog.class);
    }
}
