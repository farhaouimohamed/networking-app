package com.nw.service;

import com.nw.dto.candidate.ApplicationOfferFormRequest;
import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.candidate.InvitationDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;

public interface CandidateService {
    CandidateDto completeProfile(CandidateDto candidateDto);
    InvitationDto invitFriend(InvitationDto invitationDto);
    CandidateEntity applyOffer(ApplicationOfferFormRequest applicationOfferFormRequest);
}
