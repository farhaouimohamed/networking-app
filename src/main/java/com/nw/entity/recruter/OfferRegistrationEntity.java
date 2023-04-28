package com.nw.entity.recruter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nw.entity.candidate.CandidateEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate_offer_registration")
public class OfferRegistrationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id") private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private CandidateEntity candidateEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private OfferEntity offerEntity;

    private String cv;
    @Column(name = "cover_letter")
    private String coverLetter;
    @Column(name = "portfolio_link")
    private String portfolioLink;

}
