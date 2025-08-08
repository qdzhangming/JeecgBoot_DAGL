package org.jeecg.modules.archive.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.PhysicalArchiveItem;
import org.jeecg.modules.archive.service.IPhysicalArchiveItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 实物档案件
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="实物档案件")
@RestController
@RequestMapping("/archive/physicalItem")
public class PhysicalArchiveItemController {
	@Autowired
	private IPhysicalArchiveItemService physicalArchiveItemService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "实物档案件-分页列表查询")
	@ApiOperation(value="实物档案件-分页列表查询", notes="实物档案件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PhysicalArchiveItem>> queryPageList(PhysicalArchiveItem physicalArchiveItem,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PhysicalArchiveItem> queryWrapper = QueryGenerator.initQueryWrapper(physicalArchiveItem, req.getParameterMap());
		Page<PhysicalArchiveItem> page = new Page<PhysicalArchiveItem>(pageNo, pageSize);
		IPage<PhysicalArchiveItem> pageList = physicalArchiveItemService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 */
	@AutoLog(value = "实物档案件-添加")
	@ApiOperation(value="实物档案件-添加", notes="实物档案件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PhysicalArchiveItem physicalArchiveItem) {
		physicalArchiveItemService.save(physicalArchiveItem);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 */
	@AutoLog(value = "实物档案件-编辑")
	@ApiOperation(value="实物档案件-编辑", notes="实物档案件-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody PhysicalArchiveItem physicalArchiveItem) {
		physicalArchiveItemService.updateById(physicalArchiveItem);
		return Result.OK("编辑成功!");
	}

	/**
	 * 批量上传扫描件
	 */
	@AutoLog(value = "实物档案件-批量上传扫描件")
	@ApiOperation(value="实物档案件-批量上传扫描件", notes="实物档案件-批量上传扫描件")
	@PostMapping(value = "/uploadScans/{id}")
	public Result<?> uploadScans(@PathVariable("id") String id, @RequestParam("files") List<MultipartFile> files) {
		try {
			physicalArchiveItemService.uploadScans(id, files);
			return Result.OK("文件上传成功");
		} catch (Exception e) {
			log.error("Upload scans failed", e);
			return Result.error("文件上传失败: " + e.getMessage());
		}
	}

}
