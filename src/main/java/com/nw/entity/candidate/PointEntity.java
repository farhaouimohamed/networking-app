package com.nw.entity.candidate;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "candidate_point")
public class PointEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id") private Long id;

    private Integer xp;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id", unique = true)
    @JsonBackReference
    private CandidateEntity candidateEntity;
}
