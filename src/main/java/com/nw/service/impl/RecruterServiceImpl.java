package com.nw.service.impl;

import com.nw.dto.candidate.CandidateDto;
import com.nw.dto.recruiter.*;
import com.nw.entity.candidate.CandidateEntity;
import com.nw.entity.candidate.ExperienceEntity;
import com.nw.entity.recruter.*;
import com.nw.exception.ResourceNotFoundException;
import com.nw.mapper.RecruiterMapper;
import com.nw.repository.recruter.*;
import com.nw.service.RecruterService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruterServiceImpl implements RecruterService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private RecruterRepository recruterRepository;
    @Autowired
    private RecruiterMapper recruiterMapper;
    @Autowired
    private TestRegistrationOfferRepository testRegistrationOfferRepository;
    @Autowired
    private OfferRegistrationRepository offerRegistrationRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<OfferDto> getListOffers(Long recruterId) {
        List<OfferDto> offerDtos = new ArrayList<>();
        List<OfferEntity> offers = offerRepository.findByRecruterEntityId(recruterId);
        offers.stream().forEach(offer -> {
            OfferDto offerDto = toOfferDto(offer);
            offer.getOfferRegistrationEntity().stream().forEach(registration -> {
                offerDto.getOfferRegistrationDtos().add(toOfferRegistrationDto(registration));
            });
            offerDtos.add(offerDto);
        });
        return offerDtos;
    }

    @Override
    public OfferDto getOfferById(Long offerId, Long recruiterId) {
        Optional<OfferEntity> optionalOffer = Optional.ofNullable(offerRepository.findByRecruterEntityIdAndId(recruiterId, offerId));
        OfferDto offerDto;
        if (optionalOffer.isPresent()) {
            OfferEntity offerEntity = optionalOffer.get();
            offerDto = toOfferDto(offerEntity);
            offerEntity.getOfferRegistrationEntity().stream().forEach(registration -> {
                offerDto.getOfferRegistrationDtos().add(toOfferRegistrationDto(registration));
            });
        } else {
            throw new ResourceNotFoundException("Offre non trouvée pour le recruteur spécifié");
        }
        return offerDto;
    }
    @Override
    public OfferEntity publishOffer(OfferDto offerDto) {
        RecruterEntity recruterEntity = recruterRepository.findById(offerDto.getRecruterId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + offerDto.getRecruterId()));
        OfferEntity offerEntity = toOfferEntity(offerDto);
        offerEntity.setPublishDate(new Date(System.currentTimeMillis()));
        offerEntity.setRecruterEntity(recruterEntity);
        return offerRepository.save(offerEntity);
    }

    @Override
    @Transactional
    public RecruterEntity addTechnicalTest(TestCandidateRequest testCandidateRequest) {
        RecruterEntity recruterEntity = recruterRepository.findById(testCandidateRequest.getRecruiterId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + testCandidateRequest.getRecruiterId()));
        TestRegistrationOfferEntity testRegistrationOfferEntity;
        if (testCandidateRequest.getId() == null){
            testRegistrationOfferEntity = new TestRegistrationOfferEntity();
        } else {
            testRegistrationOfferEntity = testRegistrationOfferRepository.findById(testCandidateRequest.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Test registration offer not found with id " + testCandidateRequest.getId()));
        }
        updateQuestions(testCandidateRequest, testRegistrationOfferEntity);
        testRegistrationOfferEntity.setTestName(testCandidateRequest.getTestName());
        testRegistrationOfferEntity.setRecruterEntity(recruterEntity);
        recruterEntity.getTestRegistrationOfferEntity().removeIf(
                existingTest -> existingTest.getId().equals(testRegistrationOfferEntity.getId()));
        recruterEntity.getTestRegistrationOfferEntity().add(testRegistrationOfferEntity);
        testRegistrationOfferRepository.save(testRegistrationOfferEntity);
        return recruterEntity;
    }

    private void updateQuestions(TestCandidateRequest testCandidateRequest, TestRegistrationOfferEntity testRegistrationOfferEntity){
        List<QuestionEntity> questions = testCandidateRequest.getQuestionEntities()
                .stream()
                .map(questionDto -> {
                    QuestionEntity questionEntity;
                    if (questionDto.getId() != null) {
                        questionEntity = questionRepository.findById(questionDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionDto.getId()));
                    } else {
                        questionEntity = new QuestionEntity();
                    }
                    recruiterMapper.toQuestionTestEntity(questionEntity, questionDto);
                    questionEntity.setTestRegistrationOfferEntity(testRegistrationOfferEntity);
                    return questionEntity;
                })
                .collect(Collectors.toList());
        testRegistrationOfferEntity.getQuestionEntities().removeIf(existingQuestion -> questions.stream()
                .anyMatch(newQuestion -> newQuestion.getId() != null && newQuestion.getId().equals(existingQuestion.getId())));
        testRegistrationOfferEntity.getQuestionEntities().addAll(questions);
    }

    @Override
    public RecruterEntity completeProfile(RecruiterDto recruiterDto) {
        RecruterEntity recruterEntity = recruterRepository.findById(recruiterDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + recruiterDto.getId()));
        toRecruiterEntity(recruterEntity, recruiterDto);
        return recruterRepository.save(recruterEntity);
    }

    @Override
    public RecruterEntity getRecruiterById(Long recruiterId) {
        RecruterEntity recruterEntity = recruterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + recruiterId));
        return recruterEntity;
    }

    @Override
    public OfferRegistrationDto getOfferRegistrationById(Long offerId, Long recruiterId, Long registrationId) {
        Optional<OfferEntity> optionalOffer = Optional.ofNullable(offerRepository.findByRecruterEntityIdAndId(recruiterId, offerId));
        OfferRegistrationDto offerRegistrationDto;
        if (optionalOffer.isPresent()) {
            OfferRegistrationEntity offerRegistrationEntity = optionalOffer.get().getOfferRegistrationEntity().stream()
                    .filter(obj -> obj.getId() == registrationId).findFirst().orElse(null);
            offerRegistrationDto = toOfferRegistrationDto(offerRegistrationEntity);
        } else {
            throw new ResourceNotFoundException("Offre non trouvée pour le recruteur spécifié");
        }
        return offerRegistrationDto;
    }

    @Override
    public RecruterEntity sendTestToCandidate(Long registrationId, Long testId, Long recruiterId) {
        RecruterEntity recruterEntity = recruterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found with id " + recruiterId));
        TestRegistrationOfferEntity testRegistrationOffer = testRegistrationOfferRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id " + testId));
        OfferRegistrationEntity offerRegistration = offerRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer Registration not found with id " + registrationId));
        offerRegistration.setTestRegistrationOfferEntity(testRegistrationOffer);
        offerRegistration.setAccessibleTest(true);
        offerRegistration.setStatus("Test envoyé");
        testRegistrationOffer.getOfferRegistrationEntities().add(offerRegistration);
        recruterEntity.getTestRegistrationOfferEntity().removeIf(
                existingTest -> existingTest.getId().equals(testRegistrationOffer.getId()));
        recruterEntity.getTestRegistrationOfferEntity().add(testRegistrationOffer);
        testRegistrationOfferRepository.save(testRegistrationOffer);
        return recruterEntity;
    }

    private OfferEntity toOfferEntity(OfferDto offerDto){
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(offerDto.getId());
        offerEntity.setTitle(offerDto.getTitle());
        offerEntity.setDescription(offerDto.getDescription());
        offerEntity.setSalaire(offerDto.getSalaire());
        offerEntity.setLocalisation(offerDto.getLocalisation());
        offerEntity.setNatureDeTravail(offerDto.getNatureDeTravail());
        offerEntity.setCategorie(offerDto.getCategorie());
        return offerEntity;
    }

    private void toRecruiterEntity(RecruterEntity recruterEntity, RecruiterDto recruiterDto){
        recruterEntity.setHeadOffice(recruiterDto.getHeadOffice());
        recruterEntity.setServices(recruiterDto.getServices());
        recruterEntity.setWebsite(recruiterDto.getWebsite());
        recruterEntity.setBio(recruiterDto.getBio());
        recruterEntity.setFoundationDate(recruiterDto.getFoundationDate());
        recruterEntity.setSponsor(recruiterDto.isSponsor());
        recruterEntity.setGouvernorat(recruiterDto.getGouvernorat());
        recruterEntity.setCity(recruiterDto.getCity());
        recruterEntity.setDetails(recruiterDto.getDetails());
        recruterEntity.setSectionDescription(recruiterDto.getSectionDescription());
        recruterEntity.setSectionTitle(recruiterDto.getSectionTitle());
    }

    private OfferDto toOfferDto(OfferEntity offerEntity){
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offerEntity.getId());
        offerDto.setTitle(offerEntity.getTitle());
        offerDto.setDescription(offerEntity.getDescription());
        offerDto.setRecruterId(offerEntity.getRecruterEntity().getId());
        offerDto.setCategorie(offerEntity.getCategorie());
        return offerDto;
    }
    private OfferRegistrationDto toOfferRegistrationDto(OfferRegistrationEntity offerRegistrationEntity){
        OfferRegistrationDto offerRegistrationDto = new OfferRegistrationDto();
        offerRegistrationDto.setOfferId(offerRegistrationEntity.getOfferEntity().getId());
        offerRegistrationDto.setId(offerRegistrationEntity.getId());
        offerRegistrationDto.setCandidateId(offerRegistrationEntity.getCandidateEntity().getId());
        offerRegistrationDto.setPortfolioLink(offerRegistrationEntity.getPortfolioLink());
        offerRegistrationDto.setFirstName(offerRegistrationEntity.getCandidateEntity().getFirstName());
        offerRegistrationDto.setLastName(offerRegistrationEntity.getCandidateEntity().getLastName());
        offerRegistrationDto.setMail(offerRegistrationEntity.getCandidateEntity().getEmail());
        offerRegistrationDto.setCv(offerRegistrationEntity.getCv());
        offerRegistrationDto.setCoverLetter(offerRegistrationEntity.getCoverLetter());
        offerRegistrationDto.setEliminated(offerRegistrationEntity.isEliminated());
        offerRegistrationDto.setStatus(offerRegistrationEntity.getStatus());
        offerRegistrationDto.setAccessibleTest(offerRegistrationDto.isAccessibleTest());
        return offerRegistrationDto;
    }
}
