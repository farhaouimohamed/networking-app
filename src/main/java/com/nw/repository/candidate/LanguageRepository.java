package com.nw.repository.candidate;

import com.nw.entity.candidate.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository  extends JpaRepository<LanguageEntity, Long> {
}
