package org.filrouge.gymcommunity.mapper;

import org.filrouge.gymcommunity.dto.vote.VoteReqDTO;
import org.filrouge.gymcommunity.dto.vote.VoteResDTO;
import org.filrouge.gymcommunity.model.entity.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper extends GenericMapper<Vote, VoteResDTO, VoteReqDTO>{
}
