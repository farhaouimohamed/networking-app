package com.nw.entity.recruter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate_test_offer_registration")
public class TestRegistrationOfferEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id") private Long id;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "path_test_recruiter")
    private String pathTestRecruiter;
    @Column(name = "path_test_candidate")
    private String pathTestCandidate;
    @Column(name = "is_done")
    private boolean isDone;

    @OneToMany(mappedBy = "testRegistrationOfferEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "testRegistrationOfferEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferRegistrationEntity> offerRegistrationEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruter_id")
    @JsonIgnore
    private RecruterEntity recruterEntity;
}
