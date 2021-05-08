package com.locationService.locationService.repository;

import com.locationService.locationService.entity.RFIDReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RFIDReaderRepository extends JpaRepository<RFIDReader, Long> {
}
