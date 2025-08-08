package org.jeecg.modules.archive.service;

import java.io.InputStream;
import java.util.List;

/**
 * Service for indexing and searching archive file content.
 */
public interface IArchiveIndexService {

    /**
     * Indexes the content of a file.
     * @param metadataId The ID of the metadata record associated with the file.
     * @param fileInputStream The input stream of the file to be indexed.
     * @throws Exception
     */
    void indexFile(String metadataId, InputStream fileInputStream) throws Exception;

    /**
     * Deletes a document from the index.
     * @param metadataId The ID of the metadata record to delete.
     */
    void deleteFromIndex(String metadataId);

    /**
     * Performs a full-text search across all indexed files.
     * @param queryText The text to search for.
     * @return A list of metadata IDs that match the query.
     */
    List<String> search(String queryText);

}
