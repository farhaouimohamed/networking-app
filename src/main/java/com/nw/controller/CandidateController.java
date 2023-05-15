package com.nw.controller;

import com.nw.dto.candidate.ApplicationOfferFormRequest;
import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.candidate.InvitationDto;
import com.nw.dto.candidate.ProjectDto;
import com.nw.dto.recruiter.OfferDto;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.recruter.OfferEntity;
import com.nw.payload.ApiResponse;
import com.nw.service.AuthenticationService;
import com.nw.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/offers")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> getAllOffers(){
        try {
            List<OfferDto> offers = candidateService.getAllOffers();
            return new ResponseEntity<>(offers, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @GetMapping("/offers/{offer_id}")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> getOfferById(@PathVariable("offer_id") Long offerId){
        try {
            OfferDto offer = candidateService.getOfferById(offerId);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping(value = "/project/add", consumes = MediaType.ALL_VALUE)
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> publishProject(@Valid @ModelAttribute ProjectDto projectDto){
        try {
            CandidateDto candidate = candidateService.publishProject(projectDto);
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{candidate_id}")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> getCandidateById(@PathVariable("candidate_id") Long candidateId){
        try {
            CandidateDto candidate = candidateService.getCandidateById(candidateId);
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

    @PostMapping(value = "/offer-apply", consumes = MediaType.ALL_VALUE)
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public ResponseEntity<?> applyOffer(@Valid @ModelAttribute ApplicationOfferFormRequest applicationOfferFormRequest){
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
