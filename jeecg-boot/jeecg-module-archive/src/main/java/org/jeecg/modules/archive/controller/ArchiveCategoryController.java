package org.jeecg.modules.archive.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.archive.entity.ArchiveCategory;
import org.jeecg.modules.archive.service.IArchiveCategoryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 档案分类
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
@Api(tags="档案分类")
@RestController
@RequestMapping("/archive/archiveCategory")
@Slf4j
public class ArchiveCategoryController extends JeecgController<ArchiveCategory, IArchiveCategoryService> {
	@Autowired
	private IArchiveCategoryService archiveCategoryService;

	/**
	 * 分页列表查询
	 *
	 * @param archiveCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "档案分类-分页列表查询")
	@ApiOperation(value="档案分类-分页列表查询", notes="档案分类-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<IPage<ArchiveCategory>> queryPageList(ArchiveCategory archiveCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//------------------------------------------------------------------------------------------------
		//是否开启系统管理模块的多租户数据权限【SAAS多租户模式】
		//QueryWrapper<ArchiveCategory> queryWrapper = tenantQueryWrapper(archiveCategory, req);
		//------------------------------------------------------------------------------------------------
		QueryWrapper<ArchiveCategory> queryWrapper = QueryGenerator.initQueryWrapper(archiveCategory, req.getParameterMap());
		//-l-l----------S-U-P-E-R------------S-T-A-R-T-------------
		//只查询pid为空的
		queryWrapper.isNull("pid");
		//-l-l----------S-U-P-E-R------------E-N-D---------------
		Page<ArchiveCategory> page = new Page<ArchiveCategory>(pageNo, pageSize);
		IPage<ArchiveCategory> pageList = archiveCategoryService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 获取子数据
	  * @param archiveCategory
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "档案分类-获取子数据")
	 @ApiOperation(value="档案分类-获取子数据", notes="档案分类-获取子数据")
	 @GetMapping(value = "/childList")
	 public Result<List<ArchiveCategory>> queryPageList(ArchiveCategory archiveCategory,HttpServletRequest req) {
		 QueryWrapper<ArchiveCategory> queryWrapper = QueryGenerator.initQueryWrapper(archiveCategory, req.getParameterMap());
		 List<ArchiveCategory> list = archiveCategoryService.list(queryWrapper);
		 return Result.OK(list);
	 }

	/**
	 *   添加
	 *
	 * @param archiveCategory
	 * @return
	 */
	@AutoLog(value = "档案分类-添加")
	@ApiOperation(value="档案分类-添加", notes="档案分类-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ArchiveCategory archiveCategory) {
		archiveCategoryService.addArchiveCategory(archiveCategory);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param archiveCategory
	 * @return
	 */
	@AutoLog(value = "档案分类-编辑")
	@ApiOperation(value="档案分类-编辑", notes="档案分类-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ArchiveCategory archiveCategory) {
		try {
			archiveCategoryService.updateArchiveCategory(archiveCategory);
		} catch (Exception e) {
			log.error("edit", e);
			return Result.error(e.getMessage());
		}
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案分类-通过id删除")
	@ApiOperation(value="档案分类-通过id删除", notes="档案分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		try {
			archiveCategoryService.deleteArchiveCategory(id);
		} catch (Exception e) {
			log.error("delete", e);
			return Result.error(e.getMessage());
		}
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "档案分类-批量删除")
	@ApiOperation(value="档案分类-批量删除", notes="档案分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.archiveCategoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "档案分类-通过id查询")
	@ApiOperation(value="档案分类-通过id查询", notes="档案分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ArchiveCategory> queryById(@RequestParam(name="id",required=true) String id) {
		ArchiveCategory archiveCategory = archiveCategoryService.getById(id);
		if(archiveCategory==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(archiveCategory);
	}

}
