package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.blog.BlogReqDTO;
import org.filrouge.gymcommunity.dto.blog.BlogResDTO;
import org.filrouge.gymcommunity.model.entity.Blog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogMapper extends GenericMapper<Blog, BlogResDTO, BlogReqDTO>{
}
