package com.example.jyhhd.controller;

import com.example.jyhhd.Result;
import com.example.jyhhd.service.SyhyjyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 试验化验检验
 */
@RestController
@RequestMapping("/syhyjy")
public class SyhyjyController {


    @Autowired
    private SyhyjyService syhyjyService;

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传技术资料")
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        Result result = new Result();
        try {
            result = syhyjyService.uploadFile(file, result);
        }catch (Exception e){
            result.setCode(-200);
            result.setMessage("上传失败");
        }
        return result;
    }
}
