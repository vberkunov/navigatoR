package com.locationService.locationService.controllers;


import com.locationService.locationService.dto.RFIDReaderDTO;
import com.locationService.locationService.entity.RFIDReader;
import com.locationService.locationService.service.implementation.RFIDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RFIDReaderController {

    @Autowired
    private RFIDServiceImpl rfidReaderService;

    @GetMapping("/rfidreader")

    public ResponseEntity<?> getAllReaders(){

        List<RFIDReader> readers = rfidReaderService.getAllReaders();

        return ResponseEntity.ok(readers);
    }

    @PostMapping("/rfidreader" )
    public ResponseEntity<?> createNewReader( @RequestBody RFIDReaderDTO readerRequest){
        RFIDReader reader = rfidReaderService.createNewReader(readerRequest);
        return ResponseEntity.ok(reader);
    }

    @GetMapping("/rfidreader/{id}")
    public ResponseEntity<?> getReaderById(@PathVariable("id") Long id){
        rfidReaderService.getReaderById(id);
        return ResponseEntity.ok("Create reader: ");
    }

    @PutMapping("/rfidreader/{id}")
    public ResponseEntity<?> updateReader(@PathVariable("id") Long id){
        rfidReaderService.updateReaderById(id);
        return ResponseEntity.ok("Create reader: ");
    }
}
