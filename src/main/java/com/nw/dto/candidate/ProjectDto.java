package com.nw.dto.candidate;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
public class ProjectDto {
    private Long id;
    private String title;
    private String content;
    private String image;
    @JsonProperty("candidate_id")
    private Long candidateId;
}
