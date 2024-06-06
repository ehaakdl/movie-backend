package com.movie.backend.exception;

import java.util.Map;

import com.movie.backend.utils.eThymeleafTemplateName;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailContentsGenerateFailException extends RuntimeException{
    private final eThymeleafTemplateName templateName;
    private final Map<String, String> model;
    
}
