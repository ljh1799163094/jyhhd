package com.example.jyhhd.service.impl;

import com.example.jyhhd.entity.Result;
import com.example.jyhhd.entity.SSJDKK;
import com.example.jyhhd.entity.SisAlarm;
import com.example.jyhhd.entity.SisAlarmCRITERIA;
import com.example.jyhhd.mapper.SisAlarmCRITERIAMapper;
import com.example.jyhhd.mapper.SisAlarmMapper;
import com.example.jyhhd.service.PoiService;
import com.example.jyhhd.util.Poi;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PoiServiceImpl implements PoiService {

    @Resource
    private SisAlarmMapper sisAlarmMapper;

    @Resource
    private SisAlarmCRITERIAMapper sisAlarmCRITERIAMapper;

    @Override
    public Result exclToDataBase(MultipartFile file, Result result) {
        if(file == null){
            result.setMessage("文件不能为空");
            result.setCode(-200);
            return result;
        }
        try {
            List<SSJDKK> ssjdkks = Poi.exclToDataBase(file);
            if(ssjdkks != null) {
                for (SSJDKK ssjdkk : ssjdkks) {
                    //生成测点主键
                    String uuid = UUID.randomUUID().toString();
                    SisAlarm sisAlarm = new SisAlarm();
                    sisAlarm.setCreateTime(new Date());
                    sisAlarm.setTaTagNo(uuid);
                    sisAlarm.setSkillId(ssjdkk.getZy());//专业
                    sisAlarm.setOrgId(ssjdkk.getDcName());//电厂
                    sisAlarm.setTaTagNam(ssjdkk.getCdmc());//c测点名称
                    sisAlarm.setTaTagDscr(ssjdkk.getCdms());//测点描述
                    sisAlarm.setExpression(ssjdkk.getCdbds());//测点表达式
                    sisAlarm.setContinuedTime(ssjdkk.getCxcxjfz());//持续告警分钟
                    sisAlarm.setIsEnable("0");
                    sisAlarmMapper.insertSelective(sisAlarm);
                    SisAlarmCRITERIA sisAlarmCRITERIA = new SisAlarmCRITERIA();
                    sisAlarmCRITERIA.setGuid(UUID.randomUUID().toString());//主键
                    sisAlarmCRITERIA.setSisAlarmId(uuid);//存放测点ID
                    sisAlarmCRITERIA.setAlarmDesc(ssjdkk.getGjms());//告警描述
                    sisAlarmCRITERIA.setAlarmName(ssjdkk.getGjmc());//告警名称
                    sisAlarmCRITERIA.setAlarmValue(ssjdkk.getGjxz());//告警限值
                    sisAlarmCRITERIA.setAlarmExpression(ssjdkk.getGjbds());//告警表达式
                    sisAlarmCRITERIA.setAlarmGrade(ssjdkk.getGjdj());//告警等级
                    sisAlarmCRITERIAMapper.insertSelective(sisAlarmCRITERIA);

                }
            }
            result.setMessage("操作成功");
            result.setCode(200);
            result.setData(ssjdkks);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("操作失败");
        }
        return result;
    }


}
