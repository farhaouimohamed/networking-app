package com.nw.service;

import com.nw.dto.recruiter.OfferDto;
import com.nw.entity.recruter.OfferEntity;

import java.util.List;

public interface UserService {
    List<OfferDto> getAllOffers();
}
