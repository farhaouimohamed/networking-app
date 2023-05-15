package com.nw.controller;

import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.recruiter.OfferDto;
import com.nw.dto.recruiter.OfferRegistrationDto;
import com.nw.dto.recruiter.RecruiterDto;
import com.nw.dto.recruiter.TestCandidateRequest;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.payload.ApiResponse;
import com.nw.service.RecruterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recruiter")
public class RecruterController {

    @Autowired
    RecruterService recruterService;

    @PutMapping
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> completeProfile(@RequestBody RecruiterDto recruiterDto){
        try {
            RecruterEntity recruiter = recruterService.completeProfile(recruiterDto);
            return new ResponseEntity<>(recruiter, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{recruiter_id}")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> getRecruiterById(@PathVariable("recruiter_id") Long recruiterId){
        try {
            RecruterEntity recruiter = recruterService.getRecruiterById(recruiterId);
            return new ResponseEntity<>(recruiter, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @PostMapping("/offer/publish")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> publishOffer(@RequestBody OfferDto offerDto){
        try {
            OfferEntity recruiter = recruterService.publishOffer(offerDto);
            return new ResponseEntity<>(recruiter, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/test/add")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> addTest(@RequestBody TestCandidateRequest testCandidateRequest){
        try {
            RecruterEntity recruiter = recruterService.addTechnicalTest(testCandidateRequest);
            return new ResponseEntity<>(recruiter, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/test/access")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> sendTestToCandidate(@RequestParam("registration_id") Long registrationId,
                                                 @RequestParam("test_id") Long testId,
                                                 @RequestParam("recruiter_id") Long recruiterId){
        try {
            RecruterEntity recruiter = recruterService.sendTestToCandidate(registrationId, testId, recruiterId);
            return new ResponseEntity<>(recruiter, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/offer")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> getAllOffers(@RequestParam(name = "recruiter_id") Long recruiterId){
        try {
            List<OfferDto> offers = recruterService.getListOffers(recruiterId);
            return new ResponseEntity<>(offers, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/offer/{id}")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> getOfferById(@PathVariable Long id, @RequestParam(name = "recruiter_id") Long recruiterId){
        try {
            OfferDto offer = recruterService.getOfferById(id, recruiterId);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/offer/{offer_id}/registration/{registration_id}")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> getRegistrationById(@PathVariable(name = "offer_id") Long id,
                                                 @RequestParam(name = "recruiter_id") Long recruiterId,
                                                 @PathVariable(name = "registration_id") Long registrationId){
        try {
            OfferRegistrationDto registration = recruterService.getOfferRegistrationById(id, recruiterId, registrationId);
            return new ResponseEntity<>(registration, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/offer/{offer_id}/registration/{registration_id}/accept_candidate")
    @PreAuthorize("hasRole('ROLE_RECRUTER')")
    public ResponseEntity<?> acceptCandidate(@RequestParam(name = "technical_test") MultipartFile technicalTest,
                                             @RequestParam(name = "recruiter_id") Long recruiterId){
        try {
            //OfferEntity recruiter = recruterService.acceptCandidate(offerDto);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
