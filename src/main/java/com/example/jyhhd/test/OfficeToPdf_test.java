package com.example.jyhhd.test;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jyhhd.entity.Table3;
import com.example.jyhhd.util.OfficeToPDF;
import com.google.common.collect.Table;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.poi.ss.formula.functions.T;
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

    /**
     * 通过反射获取类对象信息
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getClass(Class<T> clazz) {
        Table3 table3 = new Table3();
        String name = clazz.getName();
        System.err.println(name);
        table3.setNode("121");
        table3.setJg("sds");
        table3.setXm("sds");
        table3.setXh("sds");
        String s = JSONObject.toJSONString(table3);
        return JSON.parseObject(s,clazz);
    }
    public static void main(String[] args) {
        try {
            Table3 aClass = getClass(Table3.class);
            System.err.println("=========" + aClass.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
