package org.jeecg.modules.archive.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ArchiveBorrowing;
import org.jeecg.modules.archive.service.IArchiveBorrowingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 借阅记录
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="借阅记录")
@RestController
@RequestMapping("/archive/archiveBorrowing")
public class ArchiveBorrowingController {
	@Autowired
	private IArchiveBorrowingService archiveBorrowingService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "借阅记录-分页列表查询")
	@ApiOperation(value="借阅记录-分页列表查询", notes="借阅记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ArchiveBorrowing>> queryPageList(ArchiveBorrowing archiveBorrowing,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ArchiveBorrowing> queryWrapper = QueryGenerator.initQueryWrapper(archiveBorrowing, req.getParameterMap());
		Page<ArchiveBorrowing> page = new Page<ArchiveBorrowing>(pageNo, pageSize);
		IPage<ArchiveBorrowing> pageList = archiveBorrowingService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@Data
	public static class BorrowRequest {
		private ArchiveBorrowing borrowingRecord;
		private List<String> physicalItemIds;
	}

	@AutoLog(value = "执行借阅")
	@ApiOperation(value="执行借阅", notes="执行借阅")
	@PostMapping(value = "/borrow")
	public Result<?> borrow(@RequestBody BorrowRequest request) {
		try {
			archiveBorrowingService.borrowPhysicalItems(request.getBorrowingRecord(), request.getPhysicalItemIds());
			return Result.OK("借阅成功!");
		} catch (Exception e) {
			log.error("Borrowing failed", e);
			return Result.error("借阅失败: " + e.getMessage());
		}
	}

	@AutoLog(value = "执行归还")
	@ApiOperation(value="执行归还", notes="执行归还")
	@PostMapping(value = "/return/{id}")
	public Result<?> returnItems(@PathVariable("id") String id) {
		try {
			archiveBorrowingService.returnPhysicalItems(id);
			return Result.OK("归还成功!");
		} catch (Exception e) {
			log.error("Return failed", e);
			return Result.error("归还失败: " + e.getMessage());
		}
	}

	/**
	 * 通过id查询
	 */
	@AutoLog(value = "借阅记录-通过id查询")
	@ApiOperation(value="借阅记录-通过id查询", notes="借阅记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ArchiveBorrowing> queryById(@RequestParam(name="id",required=true) String id) {
		ArchiveBorrowing archiveBorrowing = archiveBorrowingService.getById(id);
		return Result.OK(archiveBorrowing);
	}

}
