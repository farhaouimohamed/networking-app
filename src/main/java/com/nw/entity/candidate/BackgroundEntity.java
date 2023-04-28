package com.nw.entity.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nw.entity.user.enums.CandidateStatutEnum;
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
@Table(name = "candidate_background")
public class BackgroundEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "background_id") private Long id;

    private String university;
    private String city;
    private String diploma;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_candidate_id", referencedColumnName = "candidate_id")
    @JsonIgnore
    private CandidateEntity candidateEntity;
}
