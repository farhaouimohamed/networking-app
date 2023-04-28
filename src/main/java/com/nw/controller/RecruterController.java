package com.nw.controller;

import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.recruiter.OfferDto;
import com.nw.dto.recruiter.RecruiterDto;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.RecruterEntity;
import com.nw.payload.ApiResponse;
import com.nw.service.RecruterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
