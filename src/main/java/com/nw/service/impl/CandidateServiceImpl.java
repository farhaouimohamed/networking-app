package com.nw.service.impl;

import com.nw.dto.candidate.ApplicationOfferFormRequest;
import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.candidate.InvitationDto;
import com.nw.entity.candidate.*;
import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.OfferRegistrationEntity;
import com.nw.entity.user.UserEntity;
import com.nw.exception.ResourceNotFoundException;
import com.nw.mapper.CandidateMapper;
import com.nw.repository.candidate.*;
import com.nw.repository.recruter.OfferRegistrationRepository;
import com.nw.repository.recruter.OfferRepository;
import com.nw.repository.user.UserRepository;
import com.nw.service.CandidateService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private BackgroundRepository backgroundRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private OfferRegistrationRepository offerRegistrationRepository;
    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    @Transactional
    public CandidateDto completeProfile(CandidateDto candidateDto) {
        CandidateEntity candidateEntity = candidateRepository.findById(candidateDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + candidateDto.getId()));
        candidateMapper.toCandidateEntity(candidateEntity, candidateDto);
        updateExperiences(candidateDto, candidateEntity);
        updateBackgrounds(candidateDto, candidateEntity);
        updateProjects(candidateDto, candidateEntity);
        updateSkills(candidateDto, candidateEntity);
        updateTrainings(candidateDto, candidateEntity);
        if (candidateEntity.getPoints().getXp() == 10){
            candidateEntity.getPoints().setXp(candidateEntity.getPoints().getXp() + 15);
        }
        CandidateEntity savedCandidate = candidateRepository.save(candidateEntity);
        return candidateMapper.toCandidateDto(candidateRepository.findById(savedCandidate.getId()).get());
    }

    @Override
    @Transactional
    public InvitationDto invitFriend(InvitationDto invitationDto) {
        verifyUsernameAndEmail(invitationDto.getEmailFriend());
        UserEntity user = UserEntity.builder().email(invitationDto.getEmailFriend()).build();
        CandidateEntity candidate = new CandidateEntity();
        candidate.setEmail(user.getEmail());
        candidate.setInvited(true);
        CandidateEntity invitedBy = candidateRepository.findById(invitationDto.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + invitationDto.getCandidateId()));
        candidate.setInvitedBy(invitedBy);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(invitationDto.getEmailFriend());
        message.setSubject("Test Subject");
        message.setText("Test Body");
        emailSender.send(message);

        candidateRepository.save(candidate);

        return null;
    }

    @Override
    @Transactional
    public CandidateEntity applyOffer(ApplicationOfferFormRequest applicationOfferFormRequest) {
        CandidateEntity candidateEntity = candidateRepository.findById(applicationOfferFormRequest.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id " + applicationOfferFormRequest.getCandidateId()));
        OfferEntity offerEntity = offerRepository.findById(applicationOfferFormRequest.getOfferId())
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + applicationOfferFormRequest.getOfferId()));
        OfferRegistrationEntity offerRegistrationEntity = new OfferRegistrationEntity();
        offerRegistrationEntity.setOfferEntity(offerEntity);
        offerRegistrationEntity.setCandidateEntity(candidateEntity);
        offerRegistrationEntity.setCv(applicationOfferFormRequest.getCv());
        offerRegistrationEntity.setCoverLetter(applicationOfferFormRequest.getCoverLetter());
        offerRegistrationEntity.setPortfolioLink(applicationOfferFormRequest.getPortfolioLink());
        candidateEntity.getOfferRegistrationEntity().add(offerRegistrationEntity);
        offerRegistrationRepository.save(offerRegistrationEntity);
        return candidateEntity;
    }

    private void verifyUsernameAndEmail(String email){
        if (userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
    }

    private void updateExperiences(CandidateDto candidateDto, CandidateEntity candidateEntity){
        List<ExperienceEntity> experiences = candidateDto.getExperiences()
                .stream()
                .map(experienceDto -> {
                    ExperienceEntity experienceEntity;
                    if (experienceDto.getId() != null) {
                        experienceEntity = experienceRepository.findById(experienceDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Experience not found with id: " + experienceDto.getId()));
                    } else {
                        experienceEntity = new ExperienceEntity();
                    }
                    candidateMapper.toExperienceEntity(experienceEntity, experienceDto);
                    return experienceEntity;
                })
                .collect(Collectors.toList());
        candidateEntity.getExperiences().removeIf(existingExperience -> experiences.stream()
                .anyMatch(newExperience -> newExperience.getId() != null && newExperience.getId().equals(existingExperience.getId())));
        candidateEntity.getExperiences().addAll(experiences);
    }

    private void updateBackgrounds(CandidateDto candidateDto, CandidateEntity candidateEntity){
        List<BackgroundEntity> backgrounds = candidateDto.getBackgrounds()
                .stream()
                .map(backgroundDto -> {
                    BackgroundEntity backgroundEntity;
                    if (backgroundDto.getId() != null) {
                        backgroundEntity = backgroundRepository.findById(backgroundDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Background not found with id: " + backgroundDto.getId()));
                    } else {
                        backgroundEntity = new BackgroundEntity();
                    }
                    candidateMapper.toBackgroundEntity(backgroundEntity, backgroundDto);
                    return backgroundEntity;
                })
                .collect(Collectors.toList());
        candidateEntity.getBackgrounds().removeIf(existingBackground -> backgrounds.stream()
                .anyMatch(newBackgroung -> newBackgroung.getId() != null && newBackgroung.getId().equals(existingBackground.getId())));
        candidateEntity.getBackgrounds().addAll(backgrounds);
    }

    private void updateProjects(CandidateDto candidateDto, CandidateEntity candidateEntity){
        List<ProjectEntity> projects = candidateDto.getProjects()
                .stream()
                .map(projectDto -> {
                    ProjectEntity projectEntity;
                    if (projectDto.getId() != null) {
                        projectEntity = projectRepository.findById(projectDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectDto.getId()));
                    } else {
                        projectEntity = new ProjectEntity();
                    }
                    candidateMapper.toProjectEntity(projectEntity, projectDto);
                    return projectEntity;
                })
                .collect(Collectors.toList());
        candidateEntity.getProjects().removeIf(existingProject -> projects.stream()
                .anyMatch(newProject -> newProject.getId() != null && newProject.getId().equals(existingProject.getId())));
        candidateEntity.getProjects().addAll(projects);
    }

    private void updateSkills(CandidateDto candidateDto, CandidateEntity candidateEntity){
        List<SkillEntity> skills = candidateDto.getSkills()
                .stream()
                .map(skillDto -> {
                    SkillEntity skillEntity;
                    if (skillDto.getId() != null) {
                        skillEntity = skillRepository.findById(skillDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillDto.getId()));
                    } else {
                        skillEntity = new SkillEntity();
                    }
                    candidateMapper.toSkillEntity(skillEntity, skillDto);
                    return skillEntity;
                })
                .collect(Collectors.toList());
        candidateEntity.getSkills().removeIf(existingSkill -> skills.stream()
                .anyMatch(newSkill -> newSkill.getId() != null && newSkill.getId().equals(existingSkill.getId())));
        candidateEntity.getSkills().addAll(skills);
    }

    private void updateTrainings(CandidateDto candidateDto, CandidateEntity candidateEntity){
        List<TrainingEntity> trainings = candidateDto.getTrainings()
                .stream()
                .map(trainingDto -> {
                    TrainingEntity trainingEntity;
                    if (trainingDto.getId() != null) {
                        trainingEntity = trainingRepository.findById(trainingDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Training not found with id: " + trainingDto.getId()));
                    } else {
                        trainingEntity = new TrainingEntity();
                    }
                    candidateMapper.toTrainingEntity(trainingEntity, trainingDto);
                    return trainingEntity;
                })
                .collect(Collectors.toList());
        candidateEntity.getTrainings().removeIf(existingTraining -> trainings.stream()
                .anyMatch(newTraining -> newTraining.getId() != null && newTraining.getId().equals(existingTraining.getId())));
        candidateEntity.getTrainings().addAll(trainings);
    }
}
