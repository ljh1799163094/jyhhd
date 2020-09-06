package com.example.jyhhd.service.impl;

import com.example.jyhhd.Result;
import com.example.jyhhd.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class TestServiceImpl implements TestService {
    final String FileParentPath="E://files/";
    @Override
    public Result uploadFile(MultipartFile[] files,Result result) {
        try {
            if (files == null || files.length == 0) {
                result.setCode(-200);
                result.setMessage("文件不能为空");
                return result;
            }
            File parentFile = new File(FileParentPath);
            if(!parentFile.exists()){
                parentFile.mkdir();
            }
            for (MultipartFile multipartFile : files) {
                String originalFilename = multipartFile.getOriginalFilename();

                multipartFile.transferTo(new File(FileParentPath.concat(originalFilename)));
            }
            result.setCode(200);
            result.setMessage("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("操作失败");
            result.setCode(-200);
        }
        return result;
    }
}
