package com.example.jyhhd.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class OfficeToPDF {

    @Value("${openoffice.address}")
    private String address;

    @Value("${openoffice.port}")
    private String port;


    public  void Office2Pdf(String srcPath, String desPath) throws IOException {
        // 源文件目录
        File inputFile = new File(srcPath);
        if (!inputFile.exists()) {
            System.out.println("源文件不存在！");
            return;
        }
        // 输出文件目录
        File outputFile = new File(desPath);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().exists();
        }
        // 调用openoffice服务线程
        //String command = "C:/Program Files (x86)/OpenOffice 4/program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
        //Process p = Runtime.getRuntime().exec(command);

        // 连接openoffice服务
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                address, Integer.parseInt(port));
        connection.connect();

        // 支持转换txt doc ppt xls文件到pdf
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);

        // 关闭连接
        connection.disconnect();

        // 关闭进程
        //p.destroy();
        System.out.println("转换完成！");
    }

}
