package com.nw.entity.recruter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nw.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "recruter_id")
@Table(name = "user_recruiter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecruterEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "company_name") private String companyName;
    @Column(name = "adress") private String adress;
    @Column(name = "company_size") private Integer companySize;
    @Column(name = "activity_domain") private String activityDomain;
    @Column(name = "city") private String city;
    @Column(name = "foundation_date") private String foundationDate;
    @Column(name = "website") private String website;
    @Column(name = "head_office") private String headOffice;
    @Column(name = "services") private String services;
    @Column(name = "bio") private String bio;
    @Column(name = "details") private String details;
    @Column(name = "gouvernorat") private String gouvernorat;
    @Column(name = "section_title") private String sectionTitle;
    @Column(name = "section_description") private String sectionDescription;
    @Column(name = "is_sponsor") private boolean isSponsor;

    @OneToMany(mappedBy = "recruterEntity")
    private List<OfferEntity> offerEntity;

    @OneToMany(mappedBy = "recruterEntity")
    private List<TestRegistrationOfferEntity> testRegistrationOfferEntity = new ArrayList<>();

    public RecruterEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                           @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String confirmedPaswword) {
        super(username,email,password, confirmedPaswword);
    }
}
