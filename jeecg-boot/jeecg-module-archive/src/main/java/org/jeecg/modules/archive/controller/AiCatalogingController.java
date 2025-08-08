package org.jeecg.modules.archive.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.service.IAiCatalogingService;
import org.jeecg.modules.archive.vo.ExtractedMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Slf4j
@Api(tags="AI自动编目")
@RestController
@RequestMapping("/archive/ai")
public class AiCatalogingController {

    @Autowired
    private IAiCatalogingService aiCatalogingService;

    @AutoLog(value = "上传文件并提取元数据")
    @ApiOperation(value="上传文件并提取元数据", notes="上传文件并提取元数据")
    @PostMapping("/analyzeUpload")
    public Result<ExtractedMetadata> analyzeUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("Uploaded file is empty.");
        }
        try {
            ExtractedMetadata metadata = aiCatalogingService.extractMetadataFromFile(file.getInputStream());
            return Result.OK(metadata);
        } catch (Exception e) {
            log.error("Failed to analyze file with AI", e);
            return Result.error("Analysis failed: " + e.getMessage());
        }
    }
}
