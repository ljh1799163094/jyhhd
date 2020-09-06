package com.example.jyhhd.mapper;

import com.example.jyhhd.entity.SisAlarmCRITERIA;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SisAlarmCRITERIAMapper {
    int deleteByPrimaryKey(String guid);

    int insert(SisAlarmCRITERIA record);

    int insertSelective(SisAlarmCRITERIA record);

    SisAlarmCRITERIA selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SisAlarmCRITERIA record);

    int updateByPrimaryKey(SisAlarmCRITERIA record);
}