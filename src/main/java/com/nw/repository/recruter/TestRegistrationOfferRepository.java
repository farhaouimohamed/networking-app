package com.nw.repository.recruter;

import com.nw.entity.recruter.TestRegistrationOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRegistrationOfferRepository extends JpaRepository<TestRegistrationOfferEntity, Long> {
}
