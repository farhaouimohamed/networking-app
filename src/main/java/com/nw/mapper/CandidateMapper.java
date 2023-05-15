package com.nw.mapper;

import com.nw.dto.candidate.*;
import com.nw.entity.candidate.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateMapper {

    @Autowired
    private ModelMapper modelMapper;

    public void toCandidateEntity(CandidateEntity candidateEntity, CandidateDto candidateDto){
        candidateEntity.setId(candidateDto.getId());
        candidateEntity.setAdress(candidateDto.getAdress());
        candidateEntity.setLastName(candidateDto.getLastName());
        candidateEntity.setFirstName(candidateDto.getFirstName());
        candidateEntity.setLogo(candidateDto.getLogo());
        candidateEntity.setAboutMe(candidateDto.getAboutMe());
        candidateEntity.setDetails(candidateDto.getDetails());
        candidateEntity.setProfession(candidateDto.getProfession());
        candidateEntity.setGouvernorat(candidateDto.getGouvernorat());
        candidateEntity.setCity(candidateDto.getCity());
    }

    public CandidateDto toCandidateDto(CandidateEntity candidateEntity){
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.initCandidateDto();
        candidateDto.setAdress(candidateEntity.getAdress());
        candidateDto.setLogo(candidateEntity.getLogo());
        candidateDto.setFirstName(candidateEntity.getFirstName());
        candidateDto.setLastName(candidateEntity.getLastName());
        candidateDto.setId(candidateEntity.getId());
        candidateDto.setPhoneNumber(candidateEntity.getPhoneNumber());
        candidateDto.setEmail(candidateEntity.getEmail());
        candidateDto.setUsername(candidateEntity.getUsername());
        candidateDto.setAboutMe(candidateEntity.getAboutMe());
        candidateDto.setDetails(candidateEntity.getDetails());
        candidateDto.setGouvernorat(candidateDto.getGouvernorat());
        candidateDto.setProfession(candidateDto.getProfession());
        candidateDto.setCity(candidateEntity.getCity());
        List<ExperienceDto> experiences = candidateEntity.getExperiences()
                .stream().map(this::toExperienceDto).collect(Collectors.toList());
        candidateDto.getExperiences().addAll(experiences);

        List<BackgroundDto> backgrounds = candidateEntity.getBackgrounds()
                .stream().map(this::toBackgroundDto)
                .collect(Collectors.toList());
        candidateDto.getBackgrounds().addAll(backgrounds);

        List<ProjectDto> projects = candidateEntity.getProjects()
                .stream().map(this::toProjectDto)
                .collect(Collectors.toList());
        candidateDto.getProjects().addAll(projects);

        List<SkillDto> skills = candidateEntity.getSkills()
                .stream().map(this::toSkillDto)
                .collect(Collectors.toList());
        candidateDto.getSkills().addAll(skills);

        List<LanguageDto> languages = candidateEntity.getLanguages()
                .stream().map(this::toLanguageDto)
                .collect(Collectors.toList());
        candidateDto.getLanguages().addAll(languages);

        List<TrainingDto> trainings = candidateEntity.getTrainings()
                .stream().map(this::toTrainingDto)
                .collect(Collectors.toList());
        candidateDto.getTrainings().addAll(trainings);
        candidateDto.getPoints().setId(candidateEntity.getPoints().getId());
        candidateDto.getPoints().setXp(candidateEntity.getPoints().getXp());
        candidateDto.getPoints().setCandidateId(candidateEntity.getPoints().getCandidateEntity().getId());
        return candidateDto;
    }

    public ExperienceDto toExperienceDto(ExperienceEntity experienceEntity) {
        modelMapper.typeMap(ExperienceEntity.class, ExperienceDto.class)
                .addMapping(ExperienceEntity::getId, ExperienceDto::setId)
                .addMapping(ExperienceEntity::getCompany, ExperienceDto::setCompany)
                .addMapping(ExperienceEntity::getCity, ExperienceDto::setCity)
                .addMapping(ExperienceEntity::getStartDate, ExperienceDto::setStartDate)
                .addMapping(ExperienceEntity::getFinalDate, ExperienceDto::setFinalDate)
                .addMapping(ExperienceEntity::getPosition, ExperienceDto::setPosition)
                .addMapping(ExperienceEntity::getDetails, ExperienceDto::setDetails)
                .addMapping(experience -> experience.getCandidateEntity().getId(), ExperienceDto::setCandidateId);
        return modelMapper.map(experienceEntity, ExperienceDto.class);
    }
    public void toExperienceEntity(ExperienceEntity experienceEntity, ExperienceDto experienceDto){
        modelMapper.typeMap(ExperienceDto.class, ExperienceEntity.class)
                .addMapping(ExperienceDto::getCandidateId, (exE, id) -> exE.getCandidateEntity().setId((Long) id))
                .addMapping(ExperienceDto::getCity, ExperienceEntity::setCity)
                .addMapping(ExperienceDto::getCompany, ExperienceEntity::setCompany)
                .addMapping(ExperienceDto::getStartDate, ExperienceEntity::setStartDate)
                .addMapping(ExperienceDto::getFinalDate, ExperienceEntity::setFinalDate)
                .addMapping(ExperienceDto::getPosition, ExperienceEntity::setPosition)
                .addMapping(ExperienceDto::getDetails, ExperienceEntity::setDetails);
        modelMapper.map(experienceDto, experienceEntity);
    }

    private TrainingDto toTrainingDto(TrainingEntity trainingEntity) {
        modelMapper.typeMap(TrainingEntity.class, TrainingDto.class)
                .addMapping(TrainingEntity::getId, TrainingDto::setId)
                .addMapping(TrainingEntity::getCenter, TrainingDto::setCenter)
                .addMapping(TrainingEntity::getEndDate, TrainingDto::setEndDate)
                .addMapping(TrainingEntity::getStartDate, TrainingDto::setStartDate)
                .addMapping(TrainingEntity::getDiploma, TrainingDto::setDiploma)
                .addMapping(training -> training.getCandidateEntity().getId(), TrainingDto::setCandidateId);
        return modelMapper.map(trainingEntity, TrainingDto.class);
    }
    public void toTrainingEntity(TrainingEntity trainingEntity, TrainingDto trainingDto){
        modelMapper.typeMap(TrainingDto.class, TrainingEntity.class)
                .addMapping(TrainingDto::getCandidateId, (training, id) -> training.getCandidateEntity().setId((Long) id))
                .addMapping(TrainingDto::getId, TrainingEntity::setId)
                .addMapping(TrainingDto::getCenter, TrainingEntity::setCenter)
                .addMapping(TrainingDto::getEndDate, TrainingEntity::setEndDate)
                .addMapping(TrainingDto::getStartDate, TrainingEntity::setStartDate)
                .addMapping(TrainingDto::getDiploma, TrainingEntity::setDiploma);
        modelMapper.map(trainingDto, trainingEntity);
    }

    private ProjectDto toProjectDto(ProjectEntity projectEntity) {
        modelMapper.typeMap(ProjectEntity.class, ProjectDto.class)
                .addMapping(ProjectEntity::getId, ProjectDto::setId)
                .addMapping(ProjectEntity::getContent, ProjectDto::setContent)
                .addMapping(ProjectEntity::getTitle, ProjectDto::setTitle)
                .addMapping(ProjectEntity::getImage, ProjectDto::setPathImage)
                .addMapping(project -> project.getCandidateEntity().getId(), ProjectDto::setCandidateId);
        return modelMapper.map(projectEntity, ProjectDto.class);
    }
    public void toProjectEntity(ProjectEntity projectEntity, ProjectDto projectDto){
        modelMapper.typeMap(ProjectDto.class, ProjectEntity.class)
                .addMapping(ProjectDto::getCandidateId, (project, id) -> project.getCandidateEntity().setId((Long) id))
                .addMapping(ProjectDto::getId, ProjectEntity::setId)
                .addMapping(ProjectDto::getContent, ProjectEntity::setContent)
                .addMapping(ProjectDto::getTitle, ProjectEntity::setTitle);
        modelMapper.map(projectDto, projectEntity);
    }

    private SkillDto toSkillDto(SkillEntity skillEntity) {
        modelMapper.typeMap(SkillEntity.class, SkillDto.class)
                .addMapping(SkillEntity::getId, SkillDto::setId)
                .addMapping(SkillEntity::getSkill, SkillDto::setSkill)
                .addMapping(SkillEntity::getProgramming, SkillDto::setProgramming)
                .addMapping(skill -> skill.getCandidateEntity().getId(), SkillDto::setCandidateId);
        return modelMapper.map(skillEntity, SkillDto.class);
    }
    public void toSkillEntity(SkillEntity skillEntity, SkillDto skillDto){
        modelMapper.typeMap(SkillDto.class, SkillEntity.class)
                .addMapping(SkillDto::getCandidateId, (skill, id) -> skill.getCandidateEntity().setId((Long) id))
                .addMapping(SkillDto::getSkill, SkillEntity::setSkill)
                .addMapping(SkillDto::getProgramming, SkillEntity::setProgramming)
                .addMapping(SkillDto::getId, SkillEntity::setId);
        modelMapper.map(skillDto, skillEntity);
    }

    private BackgroundDto toBackgroundDto(BackgroundEntity backgroundEntity) {
        modelMapper.typeMap(BackgroundEntity.class, BackgroundDto.class)
                .addMapping(BackgroundEntity::getId, BackgroundDto::setId)
                .addMapping(BackgroundEntity::getDiploma, BackgroundDto::setDiploma)
                .addMapping(BackgroundEntity::getStartDate, BackgroundDto::setStartDate)
                .addMapping(BackgroundEntity::getUniversity, BackgroundDto::setUniversity)
                .addMapping(BackgroundEntity::getEndDate, BackgroundDto::setEndDate)
                .addMapping(background -> background.getCandidateEntity().getId(), BackgroundDto::setCandidateId);
        return modelMapper.map(backgroundEntity, BackgroundDto.class);
    }
    public void toBackgroundEntity(BackgroundEntity backgroundEntity, BackgroundDto backgroundDto){
        modelMapper.typeMap(BackgroundDto.class, BackgroundEntity.class)
                .addMapping(BackgroundDto::getId, BackgroundEntity::setId)
                .addMapping(BackgroundDto::getCandidateId, (experienceEntity, id) -> experienceEntity.getCandidateEntity().setId((Long) id))
                .addMapping(BackgroundDto::getDiploma, BackgroundEntity::setDiploma)
                .addMapping(BackgroundDto::getStartDate, BackgroundEntity::setStartDate)
                .addMapping(BackgroundDto::getUniversity, BackgroundEntity::setUniversity)
                .addMapping(BackgroundDto::getEndDate, BackgroundEntity::setEndDate);
        modelMapper.map(backgroundDto, backgroundEntity);
    }

    private LanguageDto toLanguageDto(LanguageEntity languageEntity) {
        modelMapper.typeMap(LanguageEntity.class, LanguageDto.class)
                .addMapping(LanguageEntity::getId, LanguageDto::setId)
                .addMapping(LanguageEntity::getLanguageName, LanguageDto::setLanguageName)
                .addMapping(LanguageEntity::getLevel, LanguageDto::setLevel)
                .addMapping(language -> language.getCandidateEntity().getId(), LanguageDto::setCandidateId);
        return modelMapper.map(languageEntity, LanguageDto.class);
    }
    public void toLanguageEntity(LanguageEntity languageEntity, LanguageDto languageDto){
        modelMapper.typeMap(LanguageDto.class, LanguageEntity.class)
                .addMapping(LanguageDto::getId, LanguageEntity::setId)
                .addMapping(LanguageDto::getCandidateId, (languauge, id) -> languauge.getCandidateEntity().setId((Long) id))
                .addMapping(LanguageDto::getLanguageName, LanguageEntity::setLanguageName)
                .addMapping(LanguageDto::getLevel, LanguageEntity::setLevel);
        modelMapper.map(languageDto, languageEntity);
    }

}
