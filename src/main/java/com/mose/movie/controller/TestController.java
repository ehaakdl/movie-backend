package com.mose.movie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TestController {
    @Value("${movie.path.upload}")
    private String uploadPath;

    @PostMapping("/test")
    public List<String> upload(@RequestPart("file") List<MultipartFile> files) throws Exception {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            log.info("파일이름: {}", file.getOriginalFilename());
            String originalName = file.getOriginalFilename();
            File dest = new File(uploadPath + originalName);
            file.transferTo(dest);
        }
        return list;
    }
}
