package com.nw.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class OfferRegistrationDto {

    private Long id;
    @JsonProperty("candidate_id")
    private Long candidateId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("offer_id")
    private Long offerId;
    private String cv;
    @JsonProperty("cover_letter")
    private String coverLetter;
    @JsonProperty("portfolio_link")
    private String portfolioLink;
}
