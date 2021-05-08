package com.locationService.locationService.service.implementation;

import com.impinj.octane.OctaneSdkException;
import com.locationService.locationService.dto.RFIDReaderDTO;
import com.locationService.locationService.entity.RFIDReader;
import com.locationService.locationService.entity.addition.ReaderCondition;
import com.locationService.locationService.repository.RFIDReaderRepository;
import com.locationService.locationService.service.RFIDService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RFIDServiceImpl implements RFIDService {

    private static final String host = "62.80.183.113";
    private static final int port = 5084;
    private final RFIDReaderRepository readerRepository;
    private final ReaderOperationImpl readerOperation;

    public RFIDServiceImpl(RFIDReaderRepository readerRepository, ReaderOperationImpl readerOperation) {
        this.readerRepository = readerRepository;
        this.readerOperation = readerOperation;
    }
    public RFIDReader createNewReader(RFIDReaderDTO readerRequest)  {

        RFIDReader reader = RFIDReader.builder()
                .ip(readerRequest.getIp())
                .port(readerRequest.getPort())
                .login(readerRequest.getLogin())
                .password(readerRequest.getPassword())
                .started(false)
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .dateModified(new Timestamp(System.currentTimeMillis()))
                .errorMessage("")
                .condition(ReaderCondition.Connecting)
                .build();
        String response = readerOperation.connect(host, port);
        if(response.equals("Connected")){
            reader.setCondition(ReaderCondition.Connected);
        }else{
            reader.setErrorMessage(response);
            reader.setCondition(ReaderCondition.NotConnected);
        }
        readerRepository.save(reader);

        return reader;
    }

    public RFIDReader getReaderById(Long id) {

        return readerRepository.findById(id).orElse(null);
    }

    public void updateReaderById(Long id) {

    }

    public List<RFIDReader> getAllReaders() {
        List<RFIDReader> readers = readerRepository.findAll();
        return readers;
    }
}
