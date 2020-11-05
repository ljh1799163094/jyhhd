package com.example.jyhhd.util;

/**
 * @ProjectName: demo
 * @Package: com.example.demo.utils
 * @ClassName: HttpClientTest
 * @Author: sxtc
 * @Description: HttpClientTest
 * @Date: 2020/4/27 16:37
 * @LatestUpdate:
 * @Version: 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jyhhd.entity.Submit;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;
import sun.security.provider.MD5;

import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * http客户端
 *
 *
 * @author：刘子腾
 * @date：2019年3月20日21:44:06
 */
public class HttpClientTest {

    /**
     * post请求传输json数据
     *
     * @param url url地址
     * @param json json数据
     * @param encoding 编码方式
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String json, String encoding) throws ClientProtocolException, IOException {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        System.err.println(response.getStatusLine().getStatusCode()+"===========status===");
        if (response.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        return result;
    }

    /**
     * get请求传输数据
     *
     * @param url
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendGetData(String url, String encoding) throws Exception {
        String result = "";
        // 创建httpclient对象
         CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get方式请求对象
         URIBuilder uriBuilder = new URIBuilder(url);
//        List<NameValuePair> list = new LinkedList<>();
//        BasicNameValuePair param1 = new BasicNameValuePair("begTime", "2019-12-01 00:00:00");
//        BasicNameValuePair param2 = new BasicNameValuePair("endTime", "2019-12-01 12:00:00");
//        BasicNameValuePair param3 = new BasicNameValuePair("regNo", "3");
//        list.add(param1);
//        list.add(param2);
//        list.add(param3);
//        uriBuilder.setParameters(list);
        uriBuilder.setParameter("begTime", "2019-12-01 00:00:00");
        uriBuilder.setParameter("endTime", "2019-12-01 12:00:00");
        uriBuilder.setParameter("regNo", "3");
        // 根据带参数的URI对象构建GET请求对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        //HttpGet httpGet = new HttpGet(url);//  //application/json
        httpGet.addHeader("Content-type", "application/x-www-form-urlencoded");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 获取结果实体
        int statusCode = response.getStatusLine().getStatusCode();
        System.err.println("code====="+statusCode);
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(response.getEntity(), encoding);
        }
        // 释放链接
        response.close();
        return result;
    }

    public static void main(String[] args) {
      //用户名对应下文apId，用户密码对应下文secretKey
        try {
            String url = "http://112.35.1.155:1992/sms/norsubmit";
            Submit submit = new Submit();
            String mac = submit.getMac();
            String encryption = encryption(mac);
            Base64.Encoder encoder = Base64.getEncoder();
            submit.setMac(encryption);
            String s = JSONObject.toJSONString(submit);
            String encode = encoder.encodeToString(s.getBytes());
            sendPostDataByJson(url, encode, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * MD5  32位加密
     * @param OrderNo
     * @return
     */
    public static  String encryption(String OrderNo) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(OrderNo.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5.toLowerCase();
    }


}

