package com.nw.repository.recruter;

import com.nw.entity.recruter.RecruterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruterRepository extends JpaRepository<RecruterEntity, Long> {
}
