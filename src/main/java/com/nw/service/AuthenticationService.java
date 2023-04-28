package com.nw.service;

import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.user.ClubRepresentativeEntity;
import com.nw.entity.user.OwnerEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.payload.*;

public interface AuthenticationService {
    CandidateEntity signUpCandidate(SignupCandidateRequest signupCandidateRequest);
    RecruterEntity signUpRecruter(SignupRecruterRequest signupRecruterRequest);
    OwnerEntity signUpOwner(SignupOwnerRequest signupOwnerRequest);
    ClubRepresentativeEntity signUpClub(SignupClubRequest signupClubRequest);
}
