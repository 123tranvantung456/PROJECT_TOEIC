package com.javaweb.toeic.test.test1;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocxReader {

    public List<ImageData> extractDocxImages(MultipartFile file) throws IOException {
        List<ImageData> images = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             XWPFDocument document = new XWPFDocument(inputStream)) {

            // Duyệt qua các đoạn văn và phần để lấy ảnh theo thứ tự
            for (IBodyElement bodyElement : document.getBodyElements()) {
                if (bodyElement instanceof XWPFParagraph) {
                    XWPFParagraph paragraph = (XWPFParagraph) bodyElement;
                    for (XWPFRun run : paragraph.getRuns()) {
                        for (XWPFPicture picture : run.getEmbeddedPictures()) {
                            XWPFPictureData pictureData = picture.getPictureData();
                            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                                baos.write(pictureData.getData());
                                String ext = pictureData.suggestFileExtension(); // Lấy phần đuôi của ảnh
                                images.add(new ImageData(baos.toByteArray(), ext));
                            }
                        }
                    }
                }
            }
        }

        return images;
    }

}
