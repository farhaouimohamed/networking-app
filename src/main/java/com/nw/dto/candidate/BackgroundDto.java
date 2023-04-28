package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
public class BackgroundDto {

    private Long id;
    private String university;
    private String city;
    private String diploma;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
    private String details;
    @JsonProperty("candidate_id")
    private Long candidateId;
}
