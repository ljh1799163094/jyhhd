package com.example.jyhhd.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Word {
    public static final String SOURCE_FILE = "C:\\Users\\Lenovo\\Desktop\\受监设备中心整理文案\\新建文件夹\\a.doc";
    public static final String OUTPUT_FILE = "C:\\Users\\Lenovo\\Desktop\\受监设备中心整理文案\\新建文件夹\\b.doc";

    public static void main(String[] args) throws Exception {
        HWPFDocument doc = openDocument(SOURCE_FILE);
        if (doc != null) {
            doc = replaceText(doc, "orgName", "李四");
            saveDocument(doc, OUTPUT_FILE);
        }
    }

    /**
     *
     * @param doc  doc对象
     * @param findText  需要替换的文字
     * @param replaceText 替换后的文字
     * @return
     */
    public static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r = doc.getRange();
        for (int i = 0; i < r.numSections(); ++i) {
            Section s = r.getSection(i);
            for (int j = 0; j < s.numParagraphs(); j++) {
                Paragraph p = s.getParagraph(j);
                for (int k = 0; k < p.numCharacterRuns(); k++) {
                    CharacterRun run = p.getCharacterRun(k);
                    String text = run.text();
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
        return doc;
    }

    /**
     *
     * @param file 文件路径
     * @return
     * @throws Exception
     */
    public static HWPFDocument openDocument(String file) throws Exception {
//        URL res =Word.class.getClassLoader().getResource(file);
////        HWPFDocument document = null;
////        if (res != null) {
////            document = new HWPFDocument(new POIFSFileSystem(
////                    new File(res.getPath())));
////        }
        HWPFDocument document = null;
            document = new HWPFDocument(new POIFSFileSystem(
                    new File(file)));

        return document;
    }

    /**
     *
     * @param doc  doc对象
     * @param file 文件路径
     */
    public static void saveDocument(HWPFDocument doc, String file) {
        try  {
            FileOutputStream out = new FileOutputStream(file);
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
