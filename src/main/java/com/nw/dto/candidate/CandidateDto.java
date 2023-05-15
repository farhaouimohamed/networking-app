package com.nw.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class CandidateDto {

    private Long id;
    private String adress;
    private String logo;
    @JsonProperty("phone_number")
    private Integer phoneNumber;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    private String username;
    @JsonProperty("about_me")
    private String aboutMe;
    private String details;
    private String city;
    private String gouvernorat;
    private String profession;

    private List<ExperienceDto> experiences;
    private List<BackgroundDto> backgrounds;
    private List<ProjectDto> projects;
    private List<SkillDto> skills;
    private List<TrainingDto> trainings;
    private List<LanguageDto> languages;
    private PointDto points;

    public void initCandidateDto(){
        if (this.experiences == null)  this.experiences = new ArrayList<>();
        if (this.backgrounds == null) this.backgrounds = new ArrayList<>();
        if (this.projects == null) this.projects = new ArrayList<>();
        if (this.skills == null) this.skills = new ArrayList<>();
        if (this.trainings == null) this.trainings = new ArrayList<>();
        if (this.languages == null) this.languages = new ArrayList<>();
        if (this.points == null) this.points = new PointDto();
    }
}
