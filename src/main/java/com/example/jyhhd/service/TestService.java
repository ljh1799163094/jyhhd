package com.example.jyhhd.service;

import com.example.jyhhd.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestService {

    Result uploadFile(MultipartFile[] files,Result result);
}
