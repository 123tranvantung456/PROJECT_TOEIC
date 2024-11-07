package com.javaweb.toeic.test.test1;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class WordFileService {

    public String readDocFile(MultipartFile file) throws IOException {
        return null;
    }

    public String readDocxFile(MultipartFile file) throws IOException {
        try (XWPFDocument docx = new XWPFDocument(file.getInputStream())) {
            StringBuilder content = new StringBuilder();
            for (IBodyElement element : docx.getBodyElements()) {
                if (element instanceof XWPFParagraph) {
                    XWPFParagraph paragraph = (XWPFParagraph) element;
                    for (XWPFRun run : paragraph.getRuns()) {
                        String runText = run.text();

                        if (run.isBold()) {
                            runText = "<b>" + runText + "</b>";
                        }

                        if (run.isStrikeThrough()) {
                            runText = "<span style='text-decoration: line-through;'>" + runText + "</span>";
                        }

                        content.append(runText);
                    }
                    content.append("\n");
                } else if (element instanceof XWPFTable) {
                    XWPFTable table = (XWPFTable) element;
                    content.append("<table border='1'>");
                    for (XWPFTableRow row : table.getRows()) {
                        content.append("<tr>");
                        for (XWPFTableCell cell : row.getTableCells()) {

                            // Kiểm tra ô hợp nhất hàng hoặc cột
                            String cellText = cell.getText().replaceAll("\\r|\\n", "").trim();
                            int colSpan = cell.getCTTc().getTcPr().getGridSpan() != null ? cell.getCTTc().getTcPr().getGridSpan().getVal().intValue() : 1;
                            int rowSpan = cell.getCTTc().getTcPr().getVMerge() != null ? 2 : 1; // Simple check for rowspan

                            content.append("<td");
                            if (colSpan > 1) {
                                content.append(" colspan='").append(colSpan).append("'");
                            }
                            if (rowSpan > 1) {
                                content.append(" rowspan='").append(rowSpan).append("'");
                            }
                            content.append(">");
                            content.append(cellText);
                            content.append("</td>");
                        }
                        content.append("</tr>");
                    }
                    content.append("</table>\n");
                }
            }

            return content.toString();
        }
    }
}
