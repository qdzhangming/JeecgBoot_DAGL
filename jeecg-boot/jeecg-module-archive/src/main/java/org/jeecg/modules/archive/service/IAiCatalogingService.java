package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.vo.ExtractedMetadata;
import java.io.InputStream;

/**
 * Service for interacting with an AI model to perform automatic cataloging.
 */
public interface IAiCatalogingService {

    /**
     * Extracts metadata from the content of a given file.
     * @param fileInputStream The input stream of the file to be analyzed.
     * @return An object containing the extracted metadata and a flag indicating a potential duplicate.
     * @throws Exception if text extraction or AI interaction fails.
     */
    ExtractedMetadata extractMetadataFromFile(InputStream fileInputStream) throws Exception;

}
