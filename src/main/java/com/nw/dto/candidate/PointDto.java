package com.nw.dto.candidate;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class PointDto {
    private Long id;
    private Integer xp;

    @JsonProperty("candidate_id")
    private Long candidateId;
}
