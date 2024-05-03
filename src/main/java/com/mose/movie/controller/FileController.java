package com.mose.movie.controller;

import com.mose.movie.dto.ResponseDTO;
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
    @PostMapping("/test")
    public String test() {

        return "test";
    }

}
