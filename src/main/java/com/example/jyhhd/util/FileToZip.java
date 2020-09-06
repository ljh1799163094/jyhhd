package com.example.jyhhd.util;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件夹打成zip压缩包
 */
public class FileToZip {
    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径
     * @return
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath) throws Exception {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (!sourceFile.exists()) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
        } else {
                File zipFile = new File(zipFilePath);
                if (zipFile.exists()) {
                    System.out.println(zipFilePath + "压缩文件已经存在.");
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024 * 10];
                        for (File file : sourceFiles) {
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(file);
                            bis = new BufferedInputStream(fis, 1024 * 10);
                            int read = 0;
                            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                        flag = true;
                    }
                }

                //关闭流
              if (null != bis) bis.close();
              if (null != zos) zos.close();

        }
        return flag;
    }

    public static void main(String[] args) {

        try {
            fileToZip("G:\\test", "G:\\test.zip");
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
