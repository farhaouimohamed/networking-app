package com.nw.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupOwnerRequest {
    @NotBlank
    private String username;

    @NotBlank @Size(max = 50) @Email
    private String email;

    @JsonProperty("space_name")
    private String spaceName;

    @JsonProperty("space_type")
    private String spaceType;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

    private String city;

    @NotBlank @Size(min = 6, max = 40)
    private String password;

    @NotBlank @Size(min = 6, max = 40)
    @JsonProperty("confirmed_password")
    private String confirmedPassword;
}
