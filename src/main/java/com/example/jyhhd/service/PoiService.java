package com.example.jyhhd.service;

import com.example.jyhhd.entity.Result;
import org.springframework.web.multipart.MultipartFile;

public interface PoiService {

    Result exclToDataBase(MultipartFile file,Result result);
}
