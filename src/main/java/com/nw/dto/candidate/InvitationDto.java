package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvitationDto {
    @JsonProperty("email_friend")
    private String emailFriend;
    @JsonProperty("candidate_id")
    private Long candidateId;
}
