package com.nw.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRecruterRequest {
    private Long recruiterId;
    private String companyName;
    @NotBlank @Size(max = 50) @Email
    private String email;
    private String usernameRepresentative;
    private String address;
    private String city;
    private String companyActivity;
    private Integer companySize;
    private String activityDomain;
    @NotBlank @Size(min = 6, max = 40)
    private String password;
    @NotBlank @Size(min = 6, max = 40)
    private String confirmedPassword;
    private MultipartFile logo;
}
