package com.example.jyhhd.util;

import com.example.jyhhd.entity.Table3;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table3_Test {

    static String fileName="G:\\坑口发电公司继电及励磁监督月报表（电科院、中科院）.doc";

    public static void main(String[] args) throws IOException {
        testWord();
    }

    public static void testWord(){
        try{
            FileInputStream in = new FileInputStream(fileName);//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);

            //迭代文档中的表格
            while (it.hasNext()) {
                List<Table3> table3s = new ArrayList<>();
                Table tb = it.next();

                //迭代行，默认从0开始
                for (int i = 1; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    Table3 table3 = new Table3();
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
                            table3.setXh(value);
                        }else if(j==1){
                            table3.setXm(value);
                        }else if(j==2){
                            table3.setJg(value);
                        }else if(j==3){
                            table3.setNode(value);
                        }

                    }
                    table3s.add(table3);
                }

		         for (Table3 t:table3s){
		             System.err.println(t.toString());
                 }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}