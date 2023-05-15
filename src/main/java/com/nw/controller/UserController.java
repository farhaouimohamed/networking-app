package com.nw.controller;

import com.nw.dto.recruiter.OfferDto;
import com.nw.entity.recruter.OfferEntity;
import com.nw.payload.ApiResponse;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/offers")
    public ResponseEntity<?> getAllOffers(){
        try {
            List<OfferDto> offers = userService.getAllOffers();
            return new ResponseEntity<>(offers, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
