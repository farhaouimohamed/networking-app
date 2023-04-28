package com.nw.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RecruiterDto {
    private Long id;
    @JsonProperty("foundation_date")
    private Date foundationDate;
    private String website;
    @JsonProperty("head_office")
    private String headOffice;
    private String services;
    private String bio;
}
