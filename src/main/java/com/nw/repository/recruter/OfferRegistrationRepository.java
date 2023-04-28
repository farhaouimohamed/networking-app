package com.nw.repository.recruter;

import com.nw.entity.recruter.OfferRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRegistrationRepository extends JpaRepository<OfferRegistrationEntity, Long> {
}
