package org.jeecg.modules.archive.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ElectronicFile;
import org.jeecg.modules.archive.service.IElectronicFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 电子文件表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="电子文件表")
@RestController
@RequestMapping("/archive/electronicFile")
public class ElectronicFileController {
	@Autowired
	private IElectronicFileService electronicFileService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "电子文件表-分页列表查询")
	@ApiOperation(value="电子文件表-分页列表查询", notes="电子文件表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ElectronicFile>> queryPageList(ElectronicFile electronicFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ElectronicFile> queryWrapper = QueryGenerator.initQueryWrapper(electronicFile, req.getParameterMap());
		Page<ElectronicFile> page = new Page<ElectronicFile>(pageNo, pageSize);
		IPage<ElectronicFile> pageList = electronicFileService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 */
	@AutoLog(value = "电子文件表-添加")
	@ApiOperation(value="电子文件表-添加", notes="电子文件表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElectronicFile electronicFile) {
		electronicFileService.save(electronicFile);
		return Result.OK("添加成功！");
	}

	@AutoLog(value = "电子文件下载（带水印）")
	@ApiOperation(value="电子文件下载（带水印）", notes="电子文件下载（带水印）")
	@GetMapping(value = "/downloadWithWatermark/{id}")
	public void downloadWithWatermark(@PathVariable("id") String id, HttpServletResponse response) {
		try {
			electronicFileService.downloadFileWithWatermark(id, response);
		} catch (Exception e) {
			log.error("Download with watermark failed", e);
			// You might want to write an error to the response as well
		}
	}

	/**
	 * 编辑
	 */
	@AutoLog(value = "电子文件表-编辑")
	@ApiOperation(value="电子文件表-编辑", notes="电子文件表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ElectronicFile electronicFile) {
		electronicFileService.updateById(electronicFile);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 */
	@AutoLog(value = "电子文件表-通过id删除")
	@ApiOperation(value="电子文件表-通过id删除", notes="电子文件表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		electronicFileService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 */
	@AutoLog(value = "电子文件表-批量删除")
	@ApiOperation(value="电子文件表-批量删除", notes="电子文件表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.electronicFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

}
