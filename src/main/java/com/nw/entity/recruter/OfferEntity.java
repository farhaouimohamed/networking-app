package com.nw.entity.recruter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate_offer")
public class OfferEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id") private Long id;

    @Column(name = "titre") private String title;
    @Column(name = "description") private String description;

    @OneToMany(mappedBy = "offerEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OfferRegistrationEntity> offerRegistrationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruter_id")
    @JsonIgnore
    private RecruterEntity recruterEntity;

}
