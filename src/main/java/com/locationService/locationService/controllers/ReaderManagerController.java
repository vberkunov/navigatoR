package com.locationService.locationService.controllers;

import com.locationService.locationService.dto.ReaderManagerDTO;
import com.locationService.locationService.entity.ReaderManagement;
import com.locationService.locationService.service.implementation.ReaderManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReaderManagerController {

    @Autowired
    private ReaderManagerServiceImpl managerService;

    @GetMapping("/manager")

    public ResponseEntity<?> getAllManager(){

        List<ReaderManagement> manager = managerService.getAllReaderManager();

        return ResponseEntity.ok("Readers: ");
    }

    @PostMapping("/manager")
    public ResponseEntity<?> createNewManager( @RequestBody ReaderManagerDTO managerRequest){
        managerService.createNewManagerReader(managerRequest);
        return ResponseEntity.ok("Create reader: ");
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<?> getManagerById(@PathVariable("id") Long id){
        managerService.getReaderManagerById(id);
        return ResponseEntity.ok("Create reader: ");
    }

    @PutMapping("/manager/{id}")
    public ResponseEntity<?> updateManager(@PathVariable("id") Long id){
        managerService.updateReaderManagerById(id);
        return ResponseEntity.ok("Create reader: ");
    }

    @GetMapping("/manager/asset")
    public ResponseEntity<?> getAssetPosition(){
        managerService.getAssetPosition();
        return ResponseEntity.ok("Create reader: ");
    }
}
