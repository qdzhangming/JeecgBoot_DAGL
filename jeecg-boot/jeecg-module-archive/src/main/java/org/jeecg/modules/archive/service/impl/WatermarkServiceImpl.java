package org.jeecg.modules.archive.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.jeecg.modules.archive.service.IWatermarkService;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class WatermarkServiceImpl implements IWatermarkService {

    @Override
    public void addWatermark(InputStream originalPdfInputStream, OutputStream watermarkedPdfOutputStream, String watermarkText) throws Exception {
        try (PDDocument document = PDDocument.load(originalPdfInputStream)) {
            for (PDPage page : document.getPages()) {
                try (PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                    float width = page.getMediaBox().getWidth();
                    float height = page.getMediaBox().getHeight();

                    PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
                    gs.setNonStrokingAlphaConstant(0.2f); // Opacity
                    gs.setStrokingAlphaConstant(0.2f);
                    cs.setGraphicsStateParameters(gs);

                    cs.beginText();
                    cs.setFont(PDType1Font.HELVETICA_BOLD, 50);
                    cs.setNonStrokingColor(Color.LIGHT_GRAY);

                    // Set rotation and position
                    cs.setTextMatrix(new org.apache.pdfbox.util.Matrix(0.707f, -0.707f, 0.707f, 0.707f, width / 4, height / 2));
                    cs.showText(watermarkText);

                    cs.endText();
                }
            }
            document.save(watermarkedPdfOutputStream);
        }
    }
}
