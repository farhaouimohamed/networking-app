package com.nw.entity.candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "candidate_experience")
public class ExperienceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id") private Long id;

    private String company;
    private String city;
    private String position;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "final_date")
    private Date finalDate;
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_candidate_id", referencedColumnName = "candidate_id")
    @JsonIgnore
    private CandidateEntity candidateEntity;
}