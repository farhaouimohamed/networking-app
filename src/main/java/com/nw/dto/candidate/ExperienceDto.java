package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
public class ExperienceDto {
    private Long id;
    private String company;
    private String city;
    private String position;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty( "final_date")
    private Date finalDate;
    private String details;
    @JsonProperty("candidate_id")
    private Integer candidateId;
}