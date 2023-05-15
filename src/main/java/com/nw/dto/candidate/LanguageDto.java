package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LanguageDto {
    private Long id;
    @JsonProperty("language_name")
    private String languageName;
    @JsonProperty( "level")
    private String level;

    @JsonProperty("candidate_id")
    private Integer candidateId;
}
