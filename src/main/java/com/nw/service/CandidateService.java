package com.nw.service;

import com.nw.dto.candidate.ApplicationOfferFormRequest;
import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.candidate.InvitationDto;
import com.nw.dto.candidate.ProjectDto;
import com.nw.dto.recruiter.OfferDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {
    CandidateDto completeProfile(CandidateDto candidateDto);
    CandidateDto publishProject(ProjectDto projectDto);
    CandidateDto getCandidateById(Long candidateId);
    InvitationDto invitFriend(InvitationDto invitationDto);
    CandidateEntity applyOffer(ApplicationOfferFormRequest applicationOfferFormRequest);
    List<OfferDto> getAllOffers();
    OfferDto getOfferById(Long offerId);

}
