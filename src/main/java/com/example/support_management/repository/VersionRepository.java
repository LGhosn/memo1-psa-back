package com.example.support_management.repository;

import com.example.support_management.model.Version;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Integer> {
    Optional<Version> findById(@NonNull Integer id);
}
