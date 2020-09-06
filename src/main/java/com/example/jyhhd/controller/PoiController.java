package com.example.jyhhd.controller;

import com.example.jyhhd.entity.Result;
import com.example.jyhhd.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PoiController {

    @Autowired
    private PoiService poiService;

    @RequestMapping("/toUploadExcl")
    public String toUploadExcl(){
        return "uploadExcl";
    }

    @RequestMapping("/exclToDataBase")
    @ResponseBody
    public Result exclToDataBase(@RequestParam("file") MultipartFile file){
        Result result = new Result();
        result=poiService.exclToDataBase(file,result);
        return result;
    }

}
