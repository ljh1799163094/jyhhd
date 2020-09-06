package com.example.jyhhd.util;


import com.example.jyhhd.entity.SSJDKK;
//import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Poi {


    /**
     * poi  数据导入数据库
     * @param
     */
    public static List<SSJDKK> exclToDataBase(MultipartFile file) throws IOException{

            List<SSJDKK> list = new ArrayList<>();
            String fileName = file.getOriginalFilename();//获取文件名
            InputStream inputStream = file.getInputStream();//获取文件流
            Workbook workbook = null;
            try {
                //判断什么类型文件
                if (fileName.endsWith(".xls")) {
                    workbook = new HSSFWorkbook(inputStream);
                } else if (fileName.endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (workbook == null) {
                return null;
            } else {
                //获取所有的工作表的的数量
                //int numOfSheet = workbook.getNumberOfSheets();
                //遍历表
               // for (int i = 0; i < numOfSheet; i++) {
                    //获取一个sheet也就是一个工作本。
                    Sheet sheet = workbook.getSheetAt(0);
                   // if (sheet == null) continue;
                    //获取一个sheet有多少Row
                    int lastRowNum = sheet.getLastRowNum();
                   // if (lastRowNum == 0) continue;
                    Row row;
                    for (int j = 1; j < lastRowNum; j++) {
                        SSJDKK ssjdkk = new SSJDKK();
                        row = sheet.getRow(j);
                        if (row == null) {
                            break;
                        }
                        //获取一个Row有多少Cell
                        boolean flag = true;
                        short lastCellNum = row.getLastCellNum();
                        for (int k = 0; k <= lastCellNum; k++) {
                            String dcName ="";
                            Cell cell0 = row.getCell(0);
                            if(cell0 != null) {
                                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                dcName = row.getCell(0).getStringCellValue().trim();
                                if(cell0.getCellType()!=Cell.CELL_TYPE_BLANK && !dcName.equals("")) {
                                    flag = false;
                                }
                            }
                            String zy = "";
                            if(row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                zy = row.getCell(1).getStringCellValue().trim();
                                if(row.getCell(1).getCellType()!=Cell.CELL_TYPE_BLANK && !zy.equals("")) {
                                    flag = false;
                                }
                            }
                            String jdxm = "";
                            if(row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                jdxm = row.getCell(2).getStringCellValue().trim();
                                if(row.getCell(2).getCellType()!=Cell.CELL_TYPE_BLANK && !jdxm.equals("")) {
                                    flag = false;
                                }
                            }
                            String jz = "";
                            if(row.getCell(3) != null) {
                                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                jz = row.getCell(3).getStringCellValue().trim();
                                if(row.getCell(3).getCellType()!=Cell.CELL_TYPE_BLANK && !jz.equals("")) {
                                    flag = false;
                                }
                            }
                            String kks = "";
                            if(row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                kks = row.getCell(4).getStringCellValue().trim();
                                if(row.getCell(4).getCellType()!=Cell.CELL_TYPE_BLANK && !kks.equals("")) {
                                    flag = false;
                                }
                            }
                            String elcName = "";
                            if(row.getCell(5) != null) {
                                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                elcName = row.getCell(5).getStringCellValue().trim();
                                if(row.getCell(5).getCellType()!=Cell.CELL_TYPE_BLANK && !elcName.equals("")) {
                                    flag = false;
                                }
                            }
                            String cdmc = "";
                            if(row.getCell(6) != null) {
                                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                cdmc = row.getCell(6).getStringCellValue().trim();
                                if(row.getCell(6).getCellType()!=Cell.CELL_TYPE_BLANK && !cdmc.equals("")) {
                                    flag = false;
                                }
                            }
                           /* String cdmc_old = "";
                            if(row.getCell(7) != null) {
                                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                cdmc_old = row.getCell(7).getStringCellValue().trim();
                                if(row.getCell(7).getCellType()!=Cell.CELL_TYPE_BLANK && !cdmc_old.equals("")) {
                                    flag = false;
                                }
                            }*/
                            String cdms = "";
                            if(row.getCell(7) != null) {
                                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                cdms = row.getCell(7).getStringCellValue().trim();
                                if(row.getCell(7).getCellType()!=Cell.CELL_TYPE_BLANK && !cdms.equals("")) {
                                    flag = false;
                                }
                            }
                            String dw = "";
                            if(row.getCell(8) != null) {
                                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                                dw = row.getCell(8).getStringCellValue().trim();
                                if(row.getCell(8).getCellType()!=Cell.CELL_TYPE_BLANK && !dw.equals("")) {
                                    flag = false;
                                }
                            }
                            String cdbds = "";
                            if(row.getCell(9) != null) {
                                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                                cdbds = row.getCell(9).getStringCellValue().trim();
                                if(row.getCell(9).getCellType()!=Cell.CELL_TYPE_BLANK && !cdbds.equals("")) {
                                    flag = false;
                                }
                            }
                            String cxcxjfz = "";
                            if(row.getCell(10) != null) {
                                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                                cxcxjfz = row.getCell(10).getStringCellValue().trim();
                                if(row.getCell(10).getCellType() != Cell.CELL_TYPE_BLANK && !cxcxjfz.equals("")) {
                                    flag = false;
                                }

                            }
                            String gjmc = "";
                            if(row.getCell(11) != null) {

                                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                                gjmc = row.getCell(11).getStringCellValue().trim();
                                if(row.getCell(11).getCellType()!=Cell.CELL_TYPE_BLANK && !gjmc.equals("")) {
                                    flag = false;
                                }
                            }

                            String gjxz="";
                            if(row.getCell(12) != null) {

                                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                                gjxz = row.getCell(12).getStringCellValue().trim();
                                if(row.getCell(12).getCellType()!=Cell.CELL_TYPE_BLANK && !gjxz.equals("")) {
                                    flag = false;
                                }

                            }
                            String gjbds = "";
                            if(row.getCell(13) != null) {
                                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                                gjbds = row.getCell(13).getStringCellValue().trim();
                                if(row.getCell(13).getCellType()!=Cell.CELL_TYPE_BLANK && !gjbds.equals("")) {
                                    flag = false;
                                }
                            }
                            String gjms ="";
                            if(row.getCell(14) != null) {
                                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                                gjms = row.getCell(14).getStringCellValue().trim();
                                if(row.getCell(14).getCellType()!=Cell.CELL_TYPE_BLANK&& !gjms.equals("")) {
                                    flag = false;
                                }
                            }
                            String gjdj = "";
                            if(row.getCell(15) != null) {
                                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                                gjdj = row.getCell(15).getStringCellValue().trim();
                                if(row.getCell(15).getCellType()!=Cell.CELL_TYPE_BLANK&& !gjdj.equals("")) {
                                    flag = false;
                                }
                            }
                            ssjdkk.setDcName(dcName);
                            ssjdkk.setZy(zy);
                            ssjdkk.setJdxm(jdxm);
                            ssjdkk.setJz(jz);
                            ssjdkk.setKks(kks);
                            ssjdkk.setElcName(elcName);
                            ssjdkk.setCdmc(cdmc);
                            ssjdkk.setCdbds(cdbds);
                            //ssjdkk.setCdmc_old(cdmc_old);
                            ssjdkk.setCdms(cdms);
                            ssjdkk.setDw(dw);
                            ssjdkk.setCdbds(cdbds);
                            ssjdkk.setCxcxjfz(cxcxjfz);
                            ssjdkk.setGjmc(gjmc);
                            ssjdkk.setGjxz(gjxz);
                            ssjdkk.setGjbds(gjbds);
                            ssjdkk.setGjms(gjms);
                            ssjdkk.setGjdj(gjdj);

                        }
                        if(flag){
                            break;
                        }
                        list.add(ssjdkk);

                    }
               // }
            }

        return list;
    }

}
