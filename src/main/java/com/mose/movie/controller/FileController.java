package com.mose.movie.controller;

import com.mose.movie.service.File.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class FileController {
    private final FileService fileService;

    public FileController(@Qualifier("FileServiceImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDTO> upload(@RequestPart("file") MultipartFile file) {
//        fileService.upload(files);
        return null;
    }

    @PostMapping("/download")
    public List<String> upload(@RequestPart("file") MultipartFile file) {

        return null;
    }

}
