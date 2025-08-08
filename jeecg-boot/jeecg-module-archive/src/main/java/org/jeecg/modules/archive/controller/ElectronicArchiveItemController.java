package org.jeecg.modules.archive.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ElectronicArchiveItem;
import org.jeecg.modules.archive.service.IElectronicArchiveItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 电子档案件
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="电子档案件")
@RestController
@RequestMapping("/archive/electronicItem")
public class ElectronicArchiveItemController {
	@Autowired
	private IElectronicArchiveItemService electronicArchiveItemService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "电子档案件-分页列表查询")
	@ApiOperation(value="电子档案件-分页列表查询", notes="电子档案件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ElectronicArchiveItem>> queryPageList(ElectronicArchiveItem electronicArchiveItem,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ElectronicArchiveItem> queryWrapper = QueryGenerator.initQueryWrapper(electronicArchiveItem, req.getParameterMap());
		Page<ElectronicArchiveItem> page = new Page<ElectronicArchiveItem>(pageNo, pageSize);
		IPage<ElectronicArchiveItem> pageList = electronicArchiveItemService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 */
	@AutoLog(value = "电子档案件-添加")
	@ApiOperation(value="电子档案件-添加", notes="电子档案件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElectronicArchiveItem electronicArchiveItem) {
		electronicArchiveItemService.save(electronicArchiveItem);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 */
	@AutoLog(value = "电子档案件-编辑")
	@ApiOperation(value="电子档案件-编辑", notes="电子档案件-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ElectronicArchiveItem electronicArchiveItem) {
		electronicArchiveItemService.updateById(electronicArchiveItem);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 */
	@AutoLog(value = "电子档案件-通过id删除")
	@ApiOperation(value="电子档案件-通过id删除", notes="电子档案件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		electronicArchiveItemService.removeById(id);
		return Result.OK("删除成功!");
	}

}
