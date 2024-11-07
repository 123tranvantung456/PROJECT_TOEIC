package com.javaweb.toeic.test.test1;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordFileService1 {

    public String readDocFile(MultipartFile file) throws IOException {
        try (HWPFDocument doc = new HWPFDocument(file.getInputStream())) {
            WordExtractor extractor = new WordExtractor(doc);
            return extractor.getText();
        }
    }

    public String readDocxText(MultipartFile file) throws IOException {
        try (XWPFDocument docx = new XWPFDocument(file.getInputStream())) {
            StringBuilder content = new StringBuilder();
            List<XWPFParagraph> paragraphs = docx.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                content.append(paragraph.getText()).append("\n");
            }
            return content.toString();
        }
    }

    public List<byte[]> extractDocxImages(MultipartFile file) throws IOException {
        try (XWPFDocument docx = new XWPFDocument(file.getInputStream())) {
            List<byte[]> images = new ArrayList<>();
            List<XWPFPictureData> pictures = docx.getAllPictures();
            for (XWPFPictureData picture : pictures) {
                images.add(picture.getData());
            }
            return images;
        }
    }
}
