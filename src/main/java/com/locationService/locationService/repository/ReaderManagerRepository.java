package com.locationService.locationService.repository;

import com.locationService.locationService.entity.ReaderManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderManagerRepository extends JpaRepository<ReaderManagement, Long> {
}
