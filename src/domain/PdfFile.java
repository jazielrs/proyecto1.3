/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Cristopher.za
 */
public class PdfFile {

    private static PDDocument document;
    private static PDPageContentStream content;
    private static PDPage page;
    private static File file;

    public static void filePdf(String date, String name) throws IOException {
//         String pdfDate = date.replace("\n", "").replace("\r", "");
//         String pdfName = name.replace("\n", "").replace("\r", "");
//        try {
//          
//            file = new File(pdfName+".pdf");
//            document.load(file);
//            content = new PDPageContentStream(document, document.getPage(0));
//            content.beginText();
//            content.setFont(PDType1Font.TIMES_BOLD, 12);
//            content.newLineAtOffset(20, page.getMediaBox().getHeight() - 52);
//            content.showText(pdfDate);
//            content.endText();
//            content.close();
//            document.save(pdfName+".pdf");
//            
//        } catch (FileNotFoundException e) {
//            document = new PDDocument();
//            System.out.println("PDF created");
//            page = new PDPage(PDRectangle.A6);
//            document.addPage(page);
//            content = new PDPageContentStream(document, page);
//            content.beginText();
//            content.setFont(PDType1Font.TIMES_BOLD, 12);
//            content.newLineAtOffset(20, page.getMediaBox().getHeight() - 52);
//            content.showText(pdfDate);
//            content.endText();
//            content.close();
//            document.save(pdfName+".pdf");
//        }
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER_BOLD, 6);
            contentStream.newLineAtOffset(20, page.getMediaBox().getHeight() - 52);
            String pdfDate = date.replace("\n", "").replace("\r", "");
            contentStream.showText(pdfDate);
            contentStream.endText();
        }
        String pdfName = name.replace("\n", "").replace("\r", "");
        document.save(pdfName + ".pdf");
    }
}
