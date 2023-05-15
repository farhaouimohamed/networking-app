package com.nw.service.impl;

import com.nw.dto.recruiter.OfferDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.exception.ResourceNotFoundException;
import com.nw.repository.recruter.OfferRepository;
import com.nw.repository.recruter.RecruterRepository;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private RecruterRepository recruterRepository;
    @Override
    public List<OfferDto> getAllOffers() {
        List<OfferDto> offerDtos = new ArrayList<>();
        List<OfferEntity> offers = offerRepository.findAll();
        offers.stream().forEach(offer -> {
            RecruterEntity recruterEntity = recruterRepository.findById(offer.getRecruterEntity().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + offer.getRecruterEntity().getId()));
            OfferDto offerDto = toOfferDto(offer);
            offerDto.setLogo(recruterEntity.getLogo());
            offerDto.setCompanyName(recruterEntity.getCompanyName());
            offerDtos.add(offerDto);
        });
        return offerDtos;
    }

    private OfferDto toOfferDto(OfferEntity offerEntity){
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offerEntity.getId());
        offerDto.setTitle(offerEntity.getTitle());
        offerDto.setDescription(offerEntity.getDescription());
        offerDto.setRecruterId(offerEntity.getRecruterEntity().getId());
        offerDto.setLocalisation(offerEntity.getLocalisation());
        offerDto.setSalaire(offerEntity.getSalaire());
        offerDto.setNatureDeTravail(offerEntity.getNatureDeTravail());
        return offerDto;
    }
}
