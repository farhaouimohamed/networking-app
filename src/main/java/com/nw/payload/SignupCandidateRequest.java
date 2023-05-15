package com.nw.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupCandidateRequest {
    private Long candidateId;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank @Size(max = 50) @Email
    private String email;
    @NotBlank @Size(min = 6, max = 40)
    private String password;
    @NotBlank @Size(min = 6, max = 40)
    private String confirmedPassword;
    private String status;
    private Integer phoneNumber;
    private String city;
    private String address;
    private MultipartFile image;
}
