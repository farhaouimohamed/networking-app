package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class SkillDto {
    private Long id;
    private String software;
    private String programming;

    @JsonProperty("candidate_id")
    private Integer candidateId;
}
