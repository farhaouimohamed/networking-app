package com.nw.entity.candidate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nw.entity.recruter.OfferRegistrationEntity;
import com.nw.entity.user.UserEntity;
import com.nw.entity.user.enums.CandidateStatutEnum;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "candidate_id")
@Table(name = "user_candidate")
public class CandidateEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING) private CandidateStatutEnum statut;
    @Column(name = "adress") private String adress;
    @Column(name = "logo") private String logo;
    @Column(name = "phone_number") private Integer phoneNumber;
    @Column(name = "about_me") private String aboutMe;
    @Column(name = "details") private String details;
    @Column(name = "is_invited") private boolean isInvited;
    @Column(name = "city") private String city;
    @Column(name = "gouvernorat") private String gouvernorat;
    @Column(name = "profession") private String profession;
    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<ExperienceEntity> experiences;

    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<BackgroundEntity> backgrounds;

    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<SkillEntity> skills;

    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<TrainingEntity> trainings;

    @OneToMany(mappedBy = "candidateEntity", cascade = CascadeType.ALL)
    private List<LanguageEntity> languages;

    @OneToOne(mappedBy = "candidateEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private PointEntity points;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by_id")
    private CandidateEntity invitedBy;

    @OneToMany(mappedBy = "candidateEntity")
    private List<OfferRegistrationEntity> offerRegistrationEntity;

    public CandidateEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                                @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String confirmedPaswword) {
        super(username,email,password, confirmedPaswword);
    }

    public void initCandidate(){
        if (this.experiences == null)  this.experiences = new ArrayList<>();
        if (this.backgrounds == null) this.backgrounds = new ArrayList<>();
        if (this.projects == null) this.projects = new ArrayList<>();
        if (this.skills == null) this.skills = new ArrayList<>();
        if (this.trainings == null) this.trainings = new ArrayList<>();
        if (this.languages == null) this.languages = new ArrayList<>();
        if (this.points == null) this.points = new PointEntity();
    }
}
