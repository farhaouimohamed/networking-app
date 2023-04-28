package com.nw.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupClubRequest {

    @NotBlank
    @JsonProperty("club_name")
    private String clubName;

    @NotBlank @Size(max = 50) @Email
    private String email;

    @JsonProperty("username_representative")
    private String usernameRepresentative;

    private String university;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

    @JsonProperty("club_activity")
    private String clubActivity;

    @NotBlank @Size(min = 6, max = 40)
    private String password;

    @NotBlank @Size(min = 6, max = 40)
    @JsonProperty("confirmed_password")
    private String confirmedPassword;
}
