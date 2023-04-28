package com.nw.controller;

import com.nw.dto.candidate.ApplicationOfferFormRequest;
import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.candidate.InvitationDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;
import com.nw.payload.ApiResponse;
import com.nw.service.AuthenticationService;
import com.nw.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CandidateService candidateService;

    @PutMapping
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> completeProfile(@RequestBody CandidateDto candidateDto){
        try {
            CandidateDto candidate = candidateService.completeProfile(candidateDto);
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/invit")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> invitFriend(@RequestBody InvitationDto invitationDto){
        try {
            InvitationDto invitation = candidateService.invitFriend(invitationDto);
            return new ResponseEntity<>(invitation, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/offer-apply")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> applyOffer(@RequestBody ApplicationOfferFormRequest applicationOfferFormRequest){
        try {
            CandidateEntity offer = candidateService.applyOffer(applicationOfferFormRequest);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
