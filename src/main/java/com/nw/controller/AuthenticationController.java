package com.nw.controller;

import com.nw.entity.user.UserEntity;
import com.nw.payload.*;
import com.nw.security.jwt.JwtUtils;
import com.nw.security.services.UserDetailsImpl;
import com.nw.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup_candidate")
    public ResponseEntity<?> signUpCandidate(@Valid @RequestBody SignupCandidateRequest signupCandidateRequest) {
        try {
            UserEntity user = authenticationService.signUpCandidate(signupCandidateRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/signup_recruiter")
    public ResponseEntity<?> signUpRecruter(@Valid @RequestBody SignupRecruterRequest signupRecruterRequest) {
        try {
            UserEntity user = authenticationService.signUpRecruter(signupRecruterRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/signup_club")
    public ResponseEntity<?> signUpClub(@Valid @RequestBody SignupClubRequest signupClubRequest) {
        try {
            UserEntity user = authenticationService.signUpClub(signupClubRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/signup_owner")
    public ResponseEntity<?> signUpOwner(@Valid @RequestBody SignupOwnerRequest signupOwnerRequest) {
        try {
            UserEntity user = authenticationService.signUpOwner(signupOwnerRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

}
