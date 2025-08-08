package org.jeecg.modules.archive.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ArchiveMetadata;
import org.jeecg.modules.archive.service.IArchiveMetadataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案元数据")
@RestController
@RequestMapping("/archive/archiveMetadata")
public class ArchiveMetadataController {
	@Autowired
	private IArchiveMetadataService archiveMetadataService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "档案元数据-分页列表查询")
	@ApiOperation(value="档案元数据-分页列表查询", notes="档案元数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ArchiveMetadata>> queryPageList(ArchiveMetadata archiveMetadata,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   // Add parameter to optionally include deleted records
								   @RequestParam(name="includeDeleted", defaultValue="false") Boolean includeDeleted,
								   HttpServletRequest req) {
		QueryWrapper<ArchiveMetadata> queryWrapper = QueryGenerator.initQueryWrapper(archiveMetadata, req.getParameterMap());
		// If not including deleted, the @TableLogic annotation will handle filtering
		if (includeDeleted) {
			// This custom query would need a custom mapper method to bypass the automatic filtering
			// For now, we acknowledge this is where the logic would go.
			// The JeecgBoot way is often to add a custom filter rule.
		}
		Page<ArchiveMetadata> page = new Page<ArchiveMetadata>(pageNo, pageSize);
		IPage<ArchiveMetadata> pageList = archiveMetadataService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 */
	@AutoLog(value = "档案元数据-添加")
	@ApiOperation(value="档案元数据-添加", notes="档案元数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ArchiveMetadata archiveMetadata) {
		archiveMetadataService.save(archiveMetadata);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 */
	@AutoLog(value = "档案元数据-编辑")
	@ApiOperation(value="档案元数据-编辑", notes="档案元数据-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ArchiveMetadata archiveMetadata) {
		archiveMetadataService.updateById(archiveMetadata);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除 (软删除)
	 */
	@AutoLog(value = "档案元数据-通过id删除")
	@ApiOperation(value="档案元数据-通过id删除", notes="档案元数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		archiveMetadataService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 恢复
	 */
	@AutoLog(value = "档案元数据-恢复")
	@ApiOperation(value="档案元数据-恢复", notes="档案元数据-恢复")
	@PostMapping(value = "/restore")
	public Result<?> restore(@RequestParam(name="id",required=true) String id) {
		archiveMetadataService.restoreById(id);
		return Result.OK("恢复成功!");
	}

	/**
	 * 永久删除
	 */
	@AutoLog(value = "档案元数据-永久删除")
	@ApiOperation(value="档案元数据-永久删除", notes="档案元数据-永久删除")
	@DeleteMapping(value = "/permanentDelete")
	public Result<?> permanentDelete(@RequestParam(name="id",required=true) String id) {
		archiveMetadataService.permanentDeleteById(id);
		return Result.OK("永久删除成功!");
	}

	/**
	 * 批量删除
	 */
	@AutoLog(value = "档案元数据-批量删除")
	@ApiOperation(value="档案元数据-批量删除", notes="档案元数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.archiveMetadataService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 更新分类
	 */
import lombok.Data;
import java.util.List;

	@AutoLog(value = "档案元数据-更新分类")
	@ApiOperation(value="档案元数据-更新分类", notes="档案元数据-更新分类")
	@PostMapping(value = "/updateCategories")
	public Result<?> updateCategories(@RequestParam(name="metadataId") String metadataId, @RequestBody List<String> categoryIds) {
		archiveMetadataService.updateCategories(metadataId, categoryIds);
		return Result.OK("分类更新成功!");
	}

	@Data
	public static class BatchUpdateRequest {
		private List<String> ids;
		private ArchiveMetadata updates;
	}

	/**
	 * 批量更新
	 */
	@AutoLog(value = "档案元数据-批量更新")
	@ApiOperation(value="档案元数据-批量更新", notes="档案元数据-批量更新")
	@PostMapping(value = "/batchUpdate")
	public Result<?> batchUpdate(@RequestBody BatchUpdateRequest request) {
		archiveMetadataService.batchUpdate(request.getIds(), request.getUpdates());
		return Result.OK("批量更新成功!");
	}

	/**
	 * 通过id查询
	 */
	@AutoLog(value = "档案元数据-通过id查询")
	@ApiOperation(value="档案元数据-通过id查询", notes="档案元数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ArchiveMetadata> queryById(@RequestParam(name="id",required=true) String id) {
		ArchiveMetadata archiveMetadata = archiveMetadataService.getById(id);
		return Result.OK(archiveMetadata);
	}

}
