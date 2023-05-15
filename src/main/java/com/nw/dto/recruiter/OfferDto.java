package com.nw.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nw.entity.recruter.OfferRegistrationEntity;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OfferDto {
    private Long id;
    private String categorie;
    private String title;
    private String description;
    private String salaire;
    private String localisation;
    @JsonProperty("nature_de_travail")
    private String natureDeTravail;
    @JsonProperty("recruter_id")
    private Long recruterId;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("company_name")
    private String companyName;
    private String profile;
    @JsonProperty("publication_date")
    private Date publicationDate;
    @JsonProperty("activity_domain")
    private String activityDomain;
    private List<OfferRegistrationDto> offerRegistrationDtos = new ArrayList<>();
}
