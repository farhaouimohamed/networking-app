package com.nw.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nw.entity.recruter.OfferRegistrationEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OfferDto {
    private Long id;
    private String title;
    private String description;
    @JsonProperty("recruter_id")
    private Long recruterId;
    private List<OfferRegistrationDto> offerRegistrationDtos = new ArrayList<>();
}
