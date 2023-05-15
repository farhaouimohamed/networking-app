package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplicationOfferFormRequest {
    private Long candidateId;
    private MultipartFile cv;
    private MultipartFile coverLetter;
    private String portfolioLink;
    private Long offerId;
}
