package com.example.jyhhd.service;

import com.example.jyhhd.Result;
import org.springframework.web.multipart.MultipartFile;

public interface SyhyjyService {

    Result uploadFile(MultipartFile file, Result result) throws Exception;
}
