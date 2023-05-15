package com.nw.mapper;

import com.nw.dto.candidate.SkillDto;
import com.nw.dto.recruiter.QuestionTestDto;
import com.nw.dto.recruiter.TestCandidateRequest;
import com.nw.entity.candidate.SkillEntity;
import com.nw.entity.recruter.QuestionEntity;
import com.nw.entity.recruter.TestRegistrationOfferEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecruiterMapper {

    @Autowired
    private ModelMapper modelMapper;

    public void toTestRegistrationOfferEntity(TestRegistrationOfferEntity testRegistrationOfferEntity,
                                              TestCandidateRequest testCandidateRequest){


    }

    public void toQuestionTestEntity(QuestionEntity questionEntity, QuestionTestDto questionTestDto){
        questionEntity.setQuestion(questionTestDto.getQuestion());
        questionEntity.setOption1(questionTestDto.getOption1());
        questionEntity.setOption2(questionTestDto.getOption2());
        questionEntity.setOption3(questionTestDto.getOption3());
        questionEntity.setOption4(questionTestDto.getOption4());
    }


    private void toQuestionTestDto(QuestionEntity questionEntity, QuestionTestDto questionTestDto){
        questionTestDto.setQuestion(questionEntity.getQuestion());
        questionTestDto.setOption1(questionEntity.getOption1());
        questionTestDto.setOption2(questionEntity.getOption2());
        questionTestDto.setOption3(questionEntity.getOption3());
        questionTestDto.setOption4(questionEntity.getOption4());
    }
}
