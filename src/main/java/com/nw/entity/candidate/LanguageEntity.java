package com.nw.entity.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "candidate_language")
public class LanguageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id") private Long id;

    @Column(name="language_name")
    private String languageName;
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_candidate_id", referencedColumnName = "candidate_id")
    @JsonIgnore
    private CandidateEntity candidateEntity;
}
