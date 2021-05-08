package com.locationService.locationService.service.implementation;

import com.impinj.octane.OctaneSdkException;
import com.locationService.locationService.dto.ReaderManagerDTO;
import com.locationService.locationService.entity.Asset;
import com.locationService.locationService.entity.RFIDReader;
import com.locationService.locationService.entity.ReaderManagement;
import com.locationService.locationService.entity.Tag;
import com.locationService.locationService.repository.RFIDReaderRepository;
import com.locationService.locationService.repository.ReaderManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderManagerServiceImpl {

    private final ReaderManagerRepository managerRepository;
    private final RFIDReaderRepository readerRepository;
    private ReaderOperationImpl readerOperation;

    public ReaderManagerServiceImpl(ReaderManagerRepository repository, RFIDReaderRepository readerRepository, ReaderOperationImpl readerOperation) {
        this.managerRepository = repository;
        this.readerRepository = readerRepository;
        this.readerOperation = readerOperation;

    }


    public List<ReaderManagement> getAllReaderManager() {
        return null;
    }

    public void createNewManagerReader(ReaderManagerDTO managerRequest)  {
        RFIDReader reader = readerRepository.findById(managerRequest.getReader_id()).orElse(null);
        Tag tag = Tag.builder()
                .epc(managerRequest.getEpc())
                .build();
        Asset asset = Asset.builder()

                .description(managerRequest.getDescription())
                .tag(tag)
                .build();

        ReaderManagement manager = ReaderManagement.builder()
                .reader(reader)
                .asset(asset)
                .build();
        managerRepository.save(manager);
        try {
            readerOperation.start();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }

    }

    public void getReaderManagerById(Long id) {
    }

    public void updateReaderManagerById(Long id) {
    }

    public void getAssetPosition() {

    }
}
