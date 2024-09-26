package com.example.vietisbaitapbuoi2.services;

import com.example.vietisbaitapbuoi2.entities.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    ResponseEntity<String> handleCsvUpload(MultipartFile file);
    ResponseEntity<List<Entity>> getListEntity();
}
