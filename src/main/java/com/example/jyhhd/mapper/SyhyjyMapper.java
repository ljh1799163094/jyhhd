package com.example.jyhhd.mapper;

import com.example.jyhhd.entity.Table1;
import com.example.jyhhd.entity.Table2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SyhyjyMapper {

    //添加色谱
    void insertSubstationEquipment(@Param("list") List<Table1> table1s);

    //添加继电保护投入率信息
    void insertRelayProtectionInputrate(@Param("list") List<Table2> table2s);
}
