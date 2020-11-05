package com.example.jyhhd.service.impl.pms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jyhhd.Result;
import com.example.jyhhd.entity.pms.CzpEntity;
import com.example.jyhhd.entity.pms.GzpEntity;
import com.example.jyhhd.entity.pms.ResultEntity;
import com.example.jyhhd.util.HttpClientTest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Test {

    /**
     * 获取操作票信息
     * @return
     */
    public static void getCzp(){
        try {
            //String url="http://10.78.187.66:7001/Liems/webservice/getRmoperList";
            String url="http://192.168.43.40:7001/Liems/webservice/getRmoperList";
            Map<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            map.put("begTime", "2018-12-01 00:00:00");
            map.put("endTime","2018-12-02 00:00:00");
            map.put("orgNo","3");
            String body = HttpClientTest.sendPostDataByJson(url, JSON.toJSONString(map), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(body);
            if(jsonObject.get("flg").equals("success")){
                List<CzpEntity> rows = JSONArray.parseArray(jsonObject.get("rows").toString(), CzpEntity.class);
                for (CzpEntity czpEntity:rows){
                    System.err.println("操作票：===========：："+czpEntity.toString());
                }
            }else {
                System.err.println("操作票：===========：：调取失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取操作票信息
     * @return
     */
//    public static void getGzp(){
//        try {
//            String url="http://192.168.43.40:7001/Liems/webservice/getRmttkList";
//            //String url="http://10.78.187.66:7001/Liems/webservice/getRmttkList";
//            Map<String, Object> map = new HashMap<String, Object>();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            map.put("begTime", simpleDateFormat.parse("2019-12-01 00:00:00"));
//            map.put("endTime", simpleDateFormat.parse("2019-12-01 12:00:00"));
//            map.put("orgNo",3);
//            String body = HttpClientTest.sendPostDataByJson(url, JSON.toJSONString(map), "utf-8");
//            JSONObject jsonObject = JSONObject.parseObject(body);
//            if(jsonObject.get("flg").equals("success")){
//                List<GzpEntity> rows = JSONArray.parseArray(jsonObject.get("rows").toString(), GzpEntity.class);
//                for (GzpEntity gzpEntity:rows){
//                    System.err.println("工作票：===========：："+gzpEntity.toString());
//                }
//            }else {
//                System.err.println("工作票：===========：：调取失败");
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void getGzp(){
        try {
            String url="http://192.168.43.40:7001/Liems/webservice/getRmttkList";
            String body = HttpClientTest.sendGetData(url, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(body);
            if(jsonObject.get("flg").equals("success")){
                List<GzpEntity> rows = JSONArray.parseArray(jsonObject.get("rows").toString(), GzpEntity.class);
                for (GzpEntity gzpEntity:rows){
                    System.err.println("工作票：===========：："+gzpEntity.toString());
                }
            }else {
                System.err.println("工作票：===========：：调取失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        getGzp();

    }
}
