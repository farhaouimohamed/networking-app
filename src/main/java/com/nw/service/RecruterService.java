package com.nw.service;

import com.nw.dto.recruiter.OfferDto;
import com.nw.dto.recruiter.OfferRegistrationDto;
import com.nw.dto.recruiter.RecruiterDto;
import com.nw.dto.recruiter.TestCandidateRequest;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.RecruterEntity;

import java.util.List;

public interface RecruterService {
    List<OfferDto> getListOffers(Long recruterId);
    OfferDto getOfferById(Long offerId, Long recruiterId);
    OfferEntity publishOffer(OfferDto offerDto);
    RecruterEntity addTechnicalTest(TestCandidateRequest testCandidateRequest);
    RecruterEntity completeProfile(RecruiterDto recruiterDto);
    RecruterEntity getRecruiterById(Long recruiterId);
    OfferRegistrationDto getOfferRegistrationById(Long offerId, Long recruiterId, Long registrationId);
    RecruterEntity sendTestToCandidate(Long registrationId, Long testId, Long recruiterId);


}
