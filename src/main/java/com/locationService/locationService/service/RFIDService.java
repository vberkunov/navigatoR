package com.locationService.locationService.service;

import com.locationService.locationService.dto.RFIDReaderDTO;
import com.locationService.locationService.entity.RFIDReader;

import java.util.List;

public interface RFIDService {

    RFIDReader createNewReader(RFIDReaderDTO readerRequest);
    RFIDReader getReaderById(Long id);
    void updateReaderById(Long id);
    List<RFIDReader> getAllReaders();
}
