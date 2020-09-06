package com.example.jyhhd.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.jyhhd.entity.Table1;
import com.example.jyhhd.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;

public class Table1_Test {

    static String fileName = "G:\\色谱(09)月报表 .docx";

    public static void main(String[] args) throws IOException {
        testWord();
    }

    public static void testWord(){
        List<User> list = new ArrayList<>();
        try{
            //载入文档最好格式为.doc后缀
            //.docx后缀文件可能存在问题，可将.docx后缀文件另存为.doc
            FileInputStream in = new FileInputStream(fileName);//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);

            //迭代文档中的表格
            while (it.hasNext()) {
                List<Table1> table1s = new ArrayList<>();
                Table tb = it.next();
                //迭代行，默认从0开始
                for (int i = 2; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    Table1 table1 = new Table1();
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        if(j==0){
                            table1.setId(value);
                        }else if(j==1){
                            table1.setSbName(value);
                        }else if(j==2){
                            table1.setVoltageKind(value);
                        }else if(j==3){
                            if(!value.equals("")) {
                                value=value.replace("-","/");
                                Date cyrq = simpleDateFormat.parse(value);
                                table1.setCyrq(cyrq);
                            }

                        }else if(j==4){
                            if(!value.equals("")) {
                                value=value.replace("-","/");
                                Date syrq = simpleDateFormat.parse(value);
                                table1.setSyrq(syrq);
                            }
                        }else if(j==5){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setCh4(value1);
                            }
                        }else if(j==6){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setC2h6(value1);
                            }

                        }else if(j==7){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setC2h4(value1);
                            }

                        }else if(j==8){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setC2h2(value1);
                            }

                        }else if(j==9){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setZj(value1);
                            }

                        }else if(j==10){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setH2(value1);
                            }

                        }else if(j==11){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setCo(value1);
                            }

                        }else if(j==12){
                            if(!value.equals("")) {
                                double value1 = Double.parseDouble(value);
                                table1.setCo2(value1);
                            }

                        }else if(j==13){
                            table1.setJl(value);
                        }else if(j==14){
                            table1.setBz(value);
                        }

                    }
                    table1s.add(table1);
                }

		         for (Table1 t:table1s){
		             System.err.println(t.toString());
                 }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createExecl(ArrayList<User> list) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("点位名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("所属区");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("维护日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("维护措施");
        cell.setCellStyle(style);


        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            User user = (User) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(user.getName());
//            row.createCell((short) 1).setCellValue(user.getArea());
//            row.createCell((short) 2).setCellValue(user.getDate());
//            row.createCell((short) 3).setCellValue(user.getCuoshi());
            // row.createCell((short) 2).setCellValue((double) stu.getAge());
            // cell = row.createCell((short) 3);
            // cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
            // .getBirth()));
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream(fileName + ".xls");
            // 选中项目右键，点击Refresh，即可显示导出文件
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //方法二

    public static  void forverseTableCells(String sourceFile, String targetFile) throws Exception{
        XWPFDocument doc = new XWPFDocument(new FileInputStream(sourceFile));
        for(XWPFTable table : doc.getTables()) {//表格
            for(XWPFTableRow row : table.getRows()) {//行
                for(XWPFTableCell cell : row.getTableCells()) {//单元格 : 直接cell.setText()只会把文字加在原有的后面，删除不了文字
                    //addBreakInCell(cell);
                    System.err.print(cell.getText()+"====");
                }
            }
        }
        //FileOutputStream fos = new FileOutputStream(targetFile);
      //  doc.write(fos);
        //fos.close();
        System.out.println("结束");
    }

    private  void addBreakInCell(XWPFTableCell cell) {
        if(cell.getText() != null && cell.getText().contains("\n")) {
            for (XWPFParagraph p : cell.getParagraphs()) {
                for (XWPFRun run : p.getRuns()) {//XWPFRun对象定义具有一组公共属性的文本区域
                    if(run.getText(0)!= null && run.getText(0).contains("\n")) {
                        String[] lines = run.getText(0).split("\n");
                        if(lines.length > 0) {
                            run.setText(lines[0], 0); // set first line into XWPFRun
                            for(int i=1;i<lines.length;i++){
                                // add break and insert new text
                                run.addBreak();//中断
//				                    run.addCarriageReturn();//回车符，但是不起作用
                                run.setText(lines[i]);
                            }
                        }
                    }
                }
            }
        }
    }



}