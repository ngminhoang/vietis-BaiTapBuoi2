package com.example.vietisbaitapbuoi2.services;

import com.example.vietisbaitapbuoi2.entities.Entity;
import com.example.vietisbaitapbuoi2.repositories.EntityRepository;
import com.example.vietisbaitapbuoi2.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private EntityRepository entityRepository;

    public ResponseEntity<String> handleCsvUpload(MultipartFile file) {
        try {
            List<Entity> users = CsvUtil.importCsv(file, Entity.class);
            entityRepository.saveAll(users);
            return ResponseEntity.ok("Successfully uploaded: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Entity>> getListEntity() {
        List<Entity> entities = entityRepository.findAll();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
