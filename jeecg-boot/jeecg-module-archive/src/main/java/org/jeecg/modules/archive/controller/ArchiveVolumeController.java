package org.jeecg.modules.archive.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ArchiveVolume;
import org.jeecg.modules.archive.entity.VolumeItemRelation;
import org.jeecg.modules.archive.service.IArchiveVolumeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 档案卷
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案卷")
@RestController
@RequestMapping("/archive/archiveVolume")
public class ArchiveVolumeController {
	@Autowired
	private IArchiveVolumeService archiveVolumeService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "档案卷-分页列表查询")
	@ApiOperation(value="档案卷-分页列表查询", notes="档案卷-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ArchiveVolume>> queryPageList(ArchiveVolume archiveVolume,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ArchiveVolume> queryWrapper = QueryGenerator.initQueryWrapper(archiveVolume, req.getParameterMap());
		Page<ArchiveVolume> page = new Page<ArchiveVolume>(pageNo, pageSize);
		IPage<ArchiveVolume> pageList = archiveVolumeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 */
	@AutoLog(value = "档案卷-添加")
	@ApiOperation(value="档案卷-添加", notes="档案卷-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ArchiveVolume archiveVolume) {
		archiveVolumeService.save(archiveVolume);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 */
	@AutoLog(value = "档案卷-编辑")
	@ApiOperation(value="档案卷-编辑", notes="档案卷-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ArchiveVolume archiveVolume) {
		archiveVolumeService.updateById(archiveVolume);
		return Result.OK("编辑成功!");
	}

	/**
	 * 组卷
	 */
	@AutoLog(value = "档案卷-组卷")
	@ApiOperation(value="档案卷-组卷", notes="档案卷-组卷")
	@PostMapping(value = "/groupItems")
	public Result<?> groupItems(@RequestParam(name="volumeId") String volumeId, @RequestBody List<VolumeItemRelation> items) {
		archiveVolumeService.groupItemsIntoVolume(volumeId, items);
		return Result.OK("组卷成功!");
	}

	/**
	 * 解卷
	 */
	@AutoLog(value = "档案卷-解卷")
	@ApiOperation(value="档案卷-解卷", notes="档案卷-解卷")
	@DeleteMapping(value = "/unwrapItem")
	public Result<?> unwrapItem(@RequestParam(name="volumeId") String volumeId, @RequestParam(name="relationId") String relationId) {
		archiveVolumeService.unwrapItemFromVolume(volumeId, relationId);
		return Result.OK("解卷成功!");
	}

}
