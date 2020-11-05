package com.example.jyhhd.service;

import com.example.jyhhd.Result;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.FieldsDocumentPart;
import org.apache.poi.hwpf.usermodel.Field;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public interface TestService {

    Result uploadFile(MultipartFile[] files,Result result);

    public static void CreatWordByModel(String  tmpFile,
                                        Map<String, String> contentMap, String exportFile) throws Exception{

        InputStream in = null;
        in = new FileInputStream(new File(tmpFile));
        OutputStream outputStream = new FileOutputStream(exportFile);
        HWPFDocument document = null;
        document = new HWPFDocument(in);
        // 读取文本内容
        Range bodyRange = document.getRange();
        System.out.println(bodyRange.toString());
        System.out.println(bodyRange.text());
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write((OutputStream)byteArrayOutputStream);

            outputStream.write(byteArrayOutputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            outputStream.close();
        }
    }

    public static void main(String[] args)  {
        String srcFile="C:\\Users\\Lenovo\\Desktop\\受监设备中心整理文案\\新建文件夹\\月报模板.doc";
        String tarFile="C:\\Users\\Lenovo\\Desktop\\受监设备中心整理文案\\新建文件夹\\月报模板1.doc";
        Map<String, String> map = new HashMap<>();
        map.put("orgName","第一");
        try {
            //CreatWordByModel(srcFile, map, tarFile);
            readwriteWord(srcFile,map,tarFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readwriteWord(String filePath, Map<String,String> map,String tarPath){
        //读取word模板
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        HWPFDocument hdt = null;
        try {
            hdt = new HWPFDocument(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Fields fields = hdt.getFields();
        Iterator<Field> it = fields.getFields(FieldsDocumentPart.MAIN).iterator();
        while(it.hasNext()){
            System.out.println(it.next().getType());
        }

        //读取word文本内容
        Range range = hdt.getRange();
        System.out.println(range.text());
        //替换文本内容
        for (Map.Entry<String,String> entry: map.entrySet()) {
            range.replaceText("$" + entry.getKey() + "$",entry.getValue());
        }
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tarPath,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            hdt.write(ostream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出字节流
        try {
            out.write(ostream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
