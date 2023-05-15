package com.nw.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuestionTestDto {
    private Long id;
    @JsonProperty("question")
    private String question;
    @JsonProperty("option1")
    private String option1;
    @JsonProperty("option2")
    private String option2;
    @JsonProperty("option3")
    private String option3;
    @JsonProperty("option4")
    private String option4;
}
