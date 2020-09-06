package com.example.jyhhd.test;

import com.example.jyhhd.util.OfficeToPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class OfficeToPdf_test {

    @Autowired
    private OfficeToPDF officeToPdf;

    @RequestMapping("/exclTopdf")
    @ResponseBody
    public String exclTopdf(){
        String srcPath="F:\\test\\2.xls";
        String desPath="F:\\test\\2.pdf";
        try {
            officeToPdf.Office2Pdf(srcPath, desPath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "你好";
    }

}
