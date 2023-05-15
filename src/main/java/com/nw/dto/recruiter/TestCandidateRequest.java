package com.nw.dto.recruiter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestCandidateRequest {
    private Long id;
    private Long recruiterId;
    private String testName;
    List<QuestionTestDto> questionEntities = new ArrayList<>();
}
