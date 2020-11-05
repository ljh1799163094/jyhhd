package com.example.jyhhd.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Submit {
    @JSONField(ordinal = 1)
    private String ecName;//企业名称
    @JSONField(ordinal = 2)
    private String apId;
    @JSONField(ordinal = 3)
    private String mobiles;//对方手机号
    @JSONField(ordinal = 4)
    private String content;//短信内容
    @JSONField(ordinal = 5)
    private String secretKey;
    @JSONField(ordinal = 6)
    private String sign;//签名
    @JSONField(ordinal = 7)
    private String addSerial;//默认为""
    @JSONField(ordinal = 8)
    private String mac;//md5加密后的拼接段

    public String getEcName() {
        if(this.ecName==null){
            this.ecName="内蒙古霍林河露天煤业股份有限公司，";
        }
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getApId() {
        if(this.apId==null){
            this.apId="sisnmg";
        }
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getSecretKey() {
        if(this.secretKey==null){
            this.secretKey="10Years5Kuayue";
        }
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getMobiles() {
        if(this.mobiles==null){
            this.mobiles="15210898025";
        }
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getContent() {
        if(this.content==null){
            this.content="hhhhhh哈哈哈哈哈哈哈哈哈";
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSign() {
        if (this.sign==null){
            this.sign="vVDuLUDjU";
        }
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAddSerial() {
        if(this.addSerial==null){
            this.addSerial="";
        }
        return addSerial;
    }

    public void setAddSerial(String addSerial) {
        this.addSerial = addSerial;
    }

    public String getMac() {
        if(this.mac==null){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.getEcName());
        stringBuffer.append(this.getApId());
        stringBuffer.append(this.getSecretKey());
        stringBuffer.append(this.getMobiles());
        stringBuffer.append(this.getContent());
        stringBuffer.append(this.getSign());
        stringBuffer.append(this.getAddSerial());
        this.mac=stringBuffer.toString();
        }

        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
