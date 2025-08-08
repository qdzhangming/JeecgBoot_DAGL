package org.jeecg.modules.archive.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.service.IArchiveIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Slf4j
@Api(tags="全文检索")
@RestController
@RequestMapping("/archive/fulltext")
public class FulltextSearchController {

    @Autowired
    private IArchiveIndexService archiveIndexService;

    @AutoLog(value = "执行全文检索")
    @ApiOperation(value="执行全文检索", notes="执行全文检索")
    @GetMapping("/search")
    public Result<List<String>> search(@RequestParam("q") String query) {
        if (query == null || query.trim().isEmpty()) {
            return Result.error("Query parameter 'q' is required.");
        }
        try {
            List<String> results = archiveIndexService.search(query);
            return Result.OK(results);
        } catch (Exception e) {
            log.error("Full-text search failed", e);
            return Result.error("Search failed: " + e.getMessage());
        }
    }
}
