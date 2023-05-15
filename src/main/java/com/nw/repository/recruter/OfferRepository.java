package com.nw.repository.recruter;

import com.nw.entity.recruter.OfferEntity;
import com.nw.entity.recruter.OfferRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findByRecruterEntityId(Long recuiterId);
    OfferEntity findByRecruterEntityIdAndId(Long recuiterId, Long id);

    @Query("SELECT o.offerRegistrationEntity FROM OfferEntity o WHERE o.id = :offerId")
    List<OfferRegistrationEntity> findOfferRegistrationsByOfferId(@Param("offerId") Long offerId);
}
