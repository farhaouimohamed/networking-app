package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationOfferFormRequest {
    @JsonProperty("candidate_id")
    private Long candidateId;
    private String cv;
    @JsonProperty("cover_letter")
    private String coverLetter;
    @JsonProperty("portfolio_link")
    private String portfolioLink;
    @JsonProperty("offer_id")
    private Long offerId;
}
