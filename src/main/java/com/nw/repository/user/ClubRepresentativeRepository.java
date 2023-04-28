package com.nw.repository.user;

import com.nw.entity.user.ClubRepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepresentativeRepository extends JpaRepository<ClubRepresentativeEntity, Long> {
}
