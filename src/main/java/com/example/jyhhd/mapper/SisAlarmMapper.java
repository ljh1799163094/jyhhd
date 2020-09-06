package com.example.jyhhd.mapper;

import com.example.jyhhd.entity.SisAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SisAlarmMapper {
    int deleteByPrimaryKey(String taTagNo);

    int insert(SisAlarm record);

    int insertSelective(SisAlarm record);

    SisAlarm selectByPrimaryKey(String taTagNo);

    int updateByPrimaryKeySelective(SisAlarm record);

    int updateByPrimaryKey(SisAlarm record);
}