package com.example.jyhhd.service.impl;

import com.example.jyhhd.Result;
import com.example.jyhhd.service.SyhyjyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SyhyjyServiceImpl implements SyhyjyService {

    @Override
    public Result uploadFile(MultipartFile file, Result result) {
        return result;
    }
}
