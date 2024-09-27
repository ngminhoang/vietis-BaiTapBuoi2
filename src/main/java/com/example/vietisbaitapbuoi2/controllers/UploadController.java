package com.example.vietisbaitapbuoi2.controllers;

import com.example.vietisbaitapbuoi2.entities.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadController{
    ResponseEntity<String> handleCsvUpload(MultipartFile file);
    ResponseEntity<byte[]>handleCsvExport();
    ResponseEntity<List<Entity>> getListEntity();
}
