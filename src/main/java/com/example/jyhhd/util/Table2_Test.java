package com.example.jyhhd.util;

import com.example.jyhhd.entity.Table2;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Table2_Test {

    static String fileName="G:\\继电保护投入率报表(2020年08月）.doc";//

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(fileName);//载入文档
        testWord(in,"");
    }

    public static List<Table2> testWord(FileInputStream in,String userId){
        List<Table2> table2s = new ArrayList<>();
        try{
            //FileInputStream in = new FileInputStream(fileName);//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);
            //迭代文档中的表格
            while (it.hasNext()) {
                Table tb = it.next();

                //迭代行，默认从0开始
                for (int i = 1; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    Table2 table2 = new Table2();
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);//取得单元格
                        //取得单元格的内容
                        String value="";
                        for(int k=0;k<td.numParagraphs();k++){
                            Paragraph para =td.getParagraph(k);
                            String s = para.text();
                            if(!StringUtils.isEmpty(s)) {
                                value+=s.substring(0, s.length() - 1);
                            }
                            value=value.trim();
                        }
                        if(j==0){
                            String id = UUID.randomUUID().toString().replace("-", "");
                            table2.setId(id);
                            table2.setXh(value);
                        }else if(j==1){
                            table2.setLineName(value);
                        }else if(j==2){
                            if(!StringUtils.isEmpty(value)){
                                table2.setJdbhsjts(Integer.parseInt(value));
                            }else {
                                table2.setJdbhsjts(0);
                            }

                        }else if(j==3){
                            if(!StringUtils.isEmpty(value)){
                                table2.setSjtrts(Integer.parseInt(value));
                            }else {
                                table2.setSjtrts(0);
                            }
                        }else if(j==4){
                            table2.setTrl(value);
                        }else if(j==5){
                            table2.setNode(value);
                        }
                        table2.setCreateat(new Date());
                        table2.setCreateby(userId);
                    }
                    table2s.add(table2);
                }

		         for (Table2 t:table2s){
		             System.err.println(t.toString());
                 }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return table2s;
    }



}