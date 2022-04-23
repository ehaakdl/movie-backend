package com.mose.movie.controller;

import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/test")
    public List<String> upload(@RequestPart("file") List<MultipartFile> files) throws Exception {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            log.info("파일이름: {}", file.getOriginalFilename());
            String originalfileName = file.getOriginalFilename();
            File dest = new File("./test/" + originalfileName);
            file.transferTo(dest);
        }
        return list;
    }
}
