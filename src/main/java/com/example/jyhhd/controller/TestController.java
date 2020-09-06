package com.example.jyhhd.controller;

import com.example.jyhhd.Result;
import com.example.jyhhd.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Result uploadFile(@RequestParam("files") MultipartFile[] files){
        Result result = new Result();
        result=testService.uploadFile(files,result);
        return result;
    }

    @GetMapping("toupload")
    public String toupload(){
        return "upload";
    }

    @GetMapping("updateFile")
    public String updateFile(){
        return "updateFile";
    }

    @GetMapping("towebsocket")
    public String towebsocket(){
        return "websocket";
    }

    @GetMapping("/toUploadBpmn")
    public String toUploadBpmn(){
        return "uploadBpmn";
    }

    /**
     * 下载文件到浏览器
     * @param request
     * @param response
     * @throws
     */
    @RequestMapping("/downFile")
    @ResponseBody
    public static void downFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //  文件存在才下载
        File file = new File("F:\\test\\7.jpg");
        String filename = file.getName();
        OutputStream out = null;
            FileInputStream in = null;
            try {
                // 1.读取要下载的内容
                in = new FileInputStream(file);

                // 2. 告诉浏览器下载的方式以及一些设置
                // 解决文件名乱码问题，获取浏览器类型，转换对应文件名编码格式，IE要求文件名必须是utf-8, firefo要求是iso-8859-1编码
                String agent = request.getHeader("user-agent");
                if (agent.contains("FireFox")) {
                    filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
                } else {
                    filename = URLEncoder.encode(filename, "UTF-8");
                }
                // 设置下载文件的mineType，告诉浏览器下载文件类型
                String mineType = request.getServletContext().getMimeType(filename);
                response.setContentType(mineType);
                // 设置一个响应头，无论是否被浏览器解析，都下载
                response.setHeader("Content-disposition", "attachment; filename=" + filename);
                // 将要下载的文件内容通过输出流写到浏览器
                out = response.getOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }

    }

}
