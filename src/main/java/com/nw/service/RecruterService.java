package com.nw.service;

import com.nw.dto.recruiter.OfferDto;
import com.nw.dto.recruiter.RecruiterDto;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.RecruterEntity;

import java.util.List;

public interface RecruterService {
    List<OfferDto> getListOffers(Long recruterId);
    OfferEntity getOfferById(Long offerId);
    OfferEntity publishOffer(OfferDto offerDto);
    RecruterEntity completeProfile(RecruiterDto recruiterDto);
}
