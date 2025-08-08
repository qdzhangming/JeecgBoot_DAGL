package org.jeecg.modules.archive.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Service for handling PDF watermarking.
 */
public interface IWatermarkService {

    /**
     * Adds a text watermark to a PDF document.
     *
     * @param originalPdfInputStream The input stream of the original PDF file.
     * @param watermarkedPdfOutputStream The output stream to write the watermarked PDF to.
     * @param watermarkText The text to be used as a watermark.
     * @throws Exception if any error occurs during the process.
     */
    void addWatermark(InputStream originalPdfInputStream, OutputStream watermarkedPdfOutputStream, String watermarkText) throws Exception;

}
