package org.jeecg.modules.archive.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.jeecg.modules.archive.entity.ArchiveMetadata;
import org.jeecg.modules.archive.service.IAiCatalogingService;
import org.jeecg.modules.archive.service.IArchiveMetadataService;
import org.jeecg.modules.archive.vo.ExtractedMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.io.InputStream;

@Service
public class AiCatalogingServiceImpl implements IAiCatalogingService {

    @Autowired
    private IArchiveMetadataService metadataService;

    @Override
    public ExtractedMetadata extractMetadataFromFile(InputStream fileInputStream) throws Exception {
        // 1. Extract text content using Apache Tika
        BodyContentHandler handler = new BodyContentHandler(-1); // -1 for no limit
        Metadata metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();
        parser.parse(fileInputStream, handler, metadata);
        String fileContent = handler.toString();

        // 2. Construct the prompt for the LLM
        String prompt = "You are an archiving assistant. Read the following document text and extract the title, author, and creation date. " +
                        "Return the result as a single, minified JSON object with the keys 'title', 'author', and 'date'. " +
                        "If a value is not found, use an empty string. Text: " + fileContent.substring(0, Math.min(fileContent.length(), 4000)); // Limit context size

        // 3. Simulate HTTP call to LLM API and get response
        // In a real application, you would use an HTTP client here.
        // String llmResponse = http.post("https://api.llm.com/v1/chat", prompt);
        String llmResponse = "{\"title\":\"关于调整开发计划的报告\",\"author\":\"张三\",\"date\":\"2025-08-07\"}"; // Mocked response

        // 4. Parse the JSON response
        JSONObject jsonResponse = JSON.parseObject(llmResponse);
        ExtractedMetadata extracted = new ExtractedMetadata();
        extracted.setTitle(jsonResponse.getString("title"));
        extracted.setAuthor(jsonResponse.getString("author"));
        extracted.setCreationDate(jsonResponse.getString("date"));

        // 5. Perform duplicate checking
        if (extracted.getTitle() != null && !extracted.getTitle().isEmpty()) {
            QueryWrapper<ArchiveMetadata> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("title", extracted.getTitle());
            long count = metadataService.count(queryWrapper);
            if (count > 0) {
                extracted.setPotentialDuplicate(true);
                extracted.setDuplicateReason("A record with the same title already exists.");
            }
        }

        return extracted;
    }
}
