package com.nw.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRecruterRequest {
    @JsonProperty("company_name")
    private String companyName;

    @NotBlank @Size(max = 50) @Email
    private String email;

    @JsonProperty("username_representative")
    private String usernameRepresentative;

    private String address;

    private String city;

    @JsonProperty("company_activity")
    private String companyActivity;

    @JsonProperty("company_size")
    private Integer companySize;

    @JsonProperty("activity_domain")
    private String activityDomain;

    @NotBlank @Size(min = 6, max = 40)
    private String password;

    @NotBlank @Size(min = 6, max = 40)
    @JsonProperty("confirmed_password")
    private String confirmedPassword;

}
