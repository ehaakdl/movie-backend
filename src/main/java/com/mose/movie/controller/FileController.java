package com.mose.movie.controller;

import com.mose.movie.etc.define.*;
import com.mose.movie.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
public class FileController {
    private final FileService fileService;

    public FileController(@Qualifier("FileServiceImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(Urls.FILE_UPLOAD)
    public ResponseEntity<ResponseDTO> upload(@RequestPart(ParameterNm.FILE) MultipartFile file) {
//        fileService.upload(files);
        return null;
    }

    @PostMapping(Urls.FILE_DOWNLOAD)
    public List<String> download(HttpSession session, @RequestPart(ParameterNm.FILE) MultipartFile file) {

        return null;
    }

}
