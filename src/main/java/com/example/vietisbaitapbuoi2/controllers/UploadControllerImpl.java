package com.example.vietisbaitapbuoi2.controllers;

import com.example.vietisbaitapbuoi2.entities.Entity;
import com.example.vietisbaitapbuoi2.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class UploadControllerImpl implements UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleCsvUpload(@RequestParam("file") MultipartFile file) {
        return uploadService.handleCsvUpload(file);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Entity>> getListEntity(){
        return uploadService.getListEntity();
    }
}