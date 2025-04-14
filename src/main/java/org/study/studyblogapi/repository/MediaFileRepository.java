package org.study.studyblogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.study.studyblogapi.model.entity.MediaFile;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.model.enums.UsageType;

import java.util.Optional;


public interface MediaFileRepository extends JpaRepository<MediaFile,Long> {
 Optional<MediaFile> findByUserAndUsageType(User user, UsageType usageType);

}
