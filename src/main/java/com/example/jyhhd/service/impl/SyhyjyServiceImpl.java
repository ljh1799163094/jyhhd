package com.example.jyhhd.service.impl;

import com.example.jyhhd.Result;
import com.example.jyhhd.entity.Table1;
import com.example.jyhhd.entity.Table2;
import com.example.jyhhd.mapper.SyhyjyMapper;
import com.example.jyhhd.service.SyhyjyService;
import com.example.jyhhd.util.Table1_Test;
import com.example.jyhhd.util.Table2_Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

@Service
public class SyhyjyServiceImpl implements SyhyjyService {

    @Autowired
    private SyhyjyMapper syhyjyMapper;

    @Override
    public Result uploadFile(MultipartFile file, Result result) throws Exception {
        if(file == null){
            result.setCode(-200);
            result.setMessage("文件不能为空");
            return result;
        }
        String originalFilename = file.getOriginalFilename();
        if(originalFilename.contains("色谱")){
            List<Table1> table1s = Table1_Test.testWord((FileInputStream) file.getInputStream(),"");
            if (table1s.size()>0) {
                syhyjyMapper.insertSubstationEquipment(table1s);
            }
        }else if(originalFilename.contains("继电保护投入率报表")){
            List<Table2> table2s = Table2_Test.testWord((FileInputStream) file.getInputStream(),"");
            if(table2s.size()>0){
                syhyjyMapper.insertRelayProtectionInputrate(table2s);
            }
        }
        result.setCode(200);
        result.setMessage("上传成功");
        return result;
    }
}
