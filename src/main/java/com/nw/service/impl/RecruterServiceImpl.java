package com.nw.service.impl;

import com.nw.dto.recruiter.OfferDto;
import com.nw.dto.recruiter.OfferRegistrationDto;
import com.nw.dto.recruiter.RecruiterDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.OfferRegistrationEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.exception.ResourceNotFoundException;
import com.nw.repository.recruter.OfferRepository;
import com.nw.repository.recruter.RecruterRepository;
import com.nw.service.RecruterService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruterServiceImpl implements RecruterService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private RecruterRepository recruterRepository;

    @Override
    public List<OfferDto> getListOffers(Long recruterId) {
        List<OfferDto> offerDtos = new ArrayList<>();
        List<OfferEntity> offers = offerRepository.findByRecruterEntityId(recruterId);
        offers.stream().forEach(offer -> {
            OfferDto offerDto = new OfferDto();
            offerDto.setId(offer.getId());
            offerDto.setTitle(offer.getTitle());
            offerDto.setDescription(offer.getDescription());
            offerDto.setRecruterId(offer.getRecruterEntity().getId());
            offer.getOfferRegistrationEntity().stream().forEach(registration -> {
                OfferRegistrationDto offerRegistrationDto = new OfferRegistrationDto();
                offerRegistrationDto.setOfferId(registration.getOfferEntity().getId());
                offerRegistrationDto.setId(registration.getId());
                offerRegistrationDto.setCandidateId(registration.getCandidateEntity().getId());
                offerRegistrationDto.setCv(registration.getCv());
                offerRegistrationDto.setPortfolioLink(registration.getPortfolioLink());
                offerRegistrationDto.setCoverLetter(registration.getCoverLetter());
                offerRegistrationDto.setFirstName(registration.getCandidateEntity().getFirstName());
                offerRegistrationDto.setLastName(registration.getCandidateEntity().getLastName());
                offerDto.getOfferRegistrationDtos().add(offerRegistrationDto);
            });
            offerDtos.add(offerDto);
        });
        return offerDtos;
    }

    @Override
    public OfferEntity getOfferById(Long offerId) {
        return null;
    }

    @Override
    public OfferEntity publishOffer(OfferDto offerDto) {
        RecruterEntity recruterEntity = recruterRepository.findById(offerDto.getRecruterId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + offerDto.getRecruterId()));
        OfferEntity offerEntity = toOfferEntity(offerDto);
        offerEntity.setRecruterEntity(recruterEntity);
        return offerRepository.save(offerEntity);
    }

    @Override
    public RecruterEntity completeProfile(RecruiterDto recruiterDto) {
        RecruterEntity recruterEntity = recruterRepository.findById(recruiterDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + recruiterDto.getId()));
        toRecruiterEntity(recruterEntity, recruiterDto);
        return recruterRepository.save(recruterEntity);
    }

    private OfferEntity toOfferEntity(OfferDto offerDto){
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(offerDto.getId());
        offerEntity.setTitle(offerDto.getTitle());
        offerEntity.setDescription(offerDto.getDescription());
        return offerEntity;
    }

    private void toRecruiterEntity(RecruterEntity recruterEntity, RecruiterDto recruiterDto){
        recruterEntity.setHeadOffice(recruiterDto.getHeadOffice());
        recruterEntity.setServices(recruiterDto.getServices());
        recruterEntity.setWebsite(recruiterDto.getWebsite());
        recruterEntity.setBio(recruiterDto.getBio());
        recruterEntity.setFoundationDate(recruiterDto.getFoundationDate());
    }
}
