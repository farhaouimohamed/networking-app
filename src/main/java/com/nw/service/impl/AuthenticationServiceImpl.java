package com.nw.service.impl;

import com.nw.entity.candidate.PointEntity;
import com.nw.entity.user.RoleEntity;
import com.nw.entity.user.UserEntity;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.user.enums.CandidateStatutEnum;
import com.nw.entity.user.ClubRepresentativeEntity;
import com.nw.entity.user.OwnerEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.entity.user.enums.ERole;
import com.nw.exception.ResourceNotFoundException;
import com.nw.payload.*;
import com.nw.repository.candidate.CandidateRepository;
import com.nw.repository.recruter.RecruterRepository;
import com.nw.repository.user.*;
import com.nw.security.jwt.JwtUtils;
import com.nw.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private RecruterRepository recruterRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ClubRepresentativeRepository clubRepresentativeRepository;
    @Override
    public CandidateEntity signUpCandidate(SignupCandidateRequest signupCandidateRequest) {
        verifyUsernameAndEmail(signupCandidateRequest.getUsername(), signupCandidateRequest.getEmail());
        UserEntity user = UserEntity.builder().username(signupCandidateRequest.getUsername())
                .email(signupCandidateRequest.getEmail()).password(encoder.encode(signupCandidateRequest.getPassword()))
                .confirmedPassword(encoder.encode(signupCandidateRequest.getConfirmedPassword())).build();
        CandidateEntity candidate = new CandidateEntity(user.getUsername(),user.getEmail(),user.getPassword(), user.getConfirmedPassword());
        PointEntity points = PointEntity.builder().xp(10).candidateEntity(candidate).build();
        candidate.setPhoneNumber(signupCandidateRequest.getPhoneNumber());
        candidate.setAdress(signupCandidateRequest.getAddress());
        candidate.setStatut(CandidateStatutEnum.valueOf(signupCandidateRequest.getStatus()));
        candidate.setPoints(points);
        RoleEntity candidateRole = roleRepository.findByName(ERole.ROLE_CANDIDATE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        candidate.setRole(candidateRole);
        return candidateRepository.save(candidate);
    }

    @Override
    public RecruterEntity signUpRecruter(SignupRecruterRequest signupRecruterRequest) {
        verifyUsernameAndEmail(signupRecruterRequest.getUsernameRepresentative(), signupRecruterRequest.getEmail());
        UserEntity user = UserEntity.builder().username(signupRecruterRequest.getUsernameRepresentative())
                .email(signupRecruterRequest.getEmail()).password(encoder.encode(signupRecruterRequest.getPassword()))
                .confirmedPassword(encoder.encode(signupRecruterRequest.getConfirmedPassword())).build();
        RecruterEntity recruiter = new RecruterEntity(user.getUsername(), user.getEmail(), user.getPassword(), user.getConfirmedPassword());
        recruiter.setAdress(signupRecruterRequest.getAddress());
        recruiter.setCompanyName(signupRecruterRequest.getCompanyName());
        recruiter.setActivityDomain(signupRecruterRequest.getActivityDomain());
        recruiter.setCompanySize(signupRecruterRequest.getCompanySize());
        recruiter.setCity(signupRecruterRequest.getCity());
        RoleEntity recruiterRole = roleRepository.findByName(ERole.ROLE_RECRUTER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        recruiter.setRole(recruiterRole);
        return recruterRepository.save(recruiter);
    }

    @Override
    public OwnerEntity signUpOwner(SignupOwnerRequest signupOwnerRequest) {
        verifyUsernameAndEmail(signupOwnerRequest.getUsername(), signupOwnerRequest.getEmail());
        UserEntity user = UserEntity.builder().username(signupOwnerRequest.getUsername())
                .email(signupOwnerRequest.getEmail()).password(encoder.encode(signupOwnerRequest.getPassword()))
                .confirmedPassword(encoder.encode(signupOwnerRequest.getConfirmedPassword())).build();
        OwnerEntity owner = new OwnerEntity(user.getUsername(), user.getEmail(), user.getPassword(), user.getConfirmedPassword());
        owner.setPhoneNumber(signupOwnerRequest.getPhoneNumber());
        owner.setSpaceType(signupOwnerRequest.getSpaceType());
        owner.setSpaceName(signupOwnerRequest.getSpaceName());
        owner.setCity(signupOwnerRequest.getCity());
        RoleEntity ownerRole = roleRepository.findByName(ERole.ROLE_OWNER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        owner.setRole(ownerRole);
        return ownerRepository.save(owner);
    }

    @Override
    public ClubRepresentativeEntity signUpClub(SignupClubRequest signupClubRequest) {
        verifyUsernameAndEmail(signupClubRequest.getUsernameRepresentative(), signupClubRequest.getEmail());
        UserEntity user = UserEntity.builder().username(signupClubRequest.getUsernameRepresentative())
                .email(signupClubRequest.getEmail()).password(encoder.encode(signupClubRequest.getPassword()))
                .confirmedPassword(encoder.encode(signupClubRequest.getConfirmedPassword())).build();
        ClubRepresentativeEntity clubRepresentative = new ClubRepresentativeEntity(user.getUsername(), user.getEmail(), user.getPassword(), user.getConfirmedPassword());
        clubRepresentative.setClubName(signupClubRequest.getClubName());
        clubRepresentative.setPhoneNumber(signupClubRequest.getPhoneNumber());
        clubRepresentative.setClubActivity(signupClubRequest.getClubActivity());
        clubRepresentative.setUniversity(signupClubRequest.getUniversity());
        RoleEntity clubRole = roleRepository.findByName(ERole.ROLE_CLUB)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        clubRepresentative.setRole(clubRole);
        return clubRepresentativeRepository.save(clubRepresentative);
    }


    private void verifyUsernameAndEmail(String username, String email){
        if (userRepository.existsByUsername(username)) {
            throw new ResourceNotFoundException("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
    }
}
