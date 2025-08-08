package org.jeecg.modules.archive.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.archive.entity.ArchiveTask;
import org.jeecg.modules.archive.service.IArchiveTaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 档案业务任务表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="档案业务任务表")
@RestController
@RequestMapping("/archive/task")
public class ArchiveTaskController {
	@Autowired
	private IArchiveTaskService archiveTaskService;

	/**
	 * 分页列表查询
	 */
	@AutoLog(value = "档案业务任务表-分页列表查询")
	@ApiOperation(value="档案业务任务表-分页列表查询", notes="档案业务任务表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ArchiveTask>> queryPageList(ArchiveTask archiveTask,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ArchiveTask> queryWrapper = QueryGenerator.initQueryWrapper(archiveTask, req.getParameterMap());
		Page<ArchiveTask> page = new Page<ArchiveTask>(pageNo, pageSize);
		IPage<ArchiveTask> pageList = archiveTaskService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@AutoLog(value = "查询我的待办任务")
	@ApiOperation(value="查询我的待办任务", notes="查询我的待办任务")
	@GetMapping(value = "/myTasks")
	public Result<IPage<ArchiveTask>> queryMyTasks(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<ArchiveTask> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("assignee_id", user.getUsername());
		queryWrapper.eq("status", "PENDING");
		Page<ArchiveTask> page = new Page<>(pageNo, pageSize);
		IPage<ArchiveTask> pageList = archiveTaskService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@Data
	public static class CreateTaskRequest {
		private List<String> ids;
		private String assigneeId;
		private String notes;
	}

	@AutoLog(value = "创建销毁任务")
	@ApiOperation(value="创建销毁任务", notes="创建销毁任务")
	@PostMapping(value = "/createDestructionTasks")
	public Result<?> createDestructionTasks(@RequestBody CreateTaskRequest request) {
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		archiveTaskService.createDestructionTasks(request.getIds(), user.getUsername(), request.getAssigneeId(), request.getNotes());
		return Result.OK("销毁任务创建成功");
	}

	@Data
	public static class ProcessTaskRequest {
		private String notes;
	}

	@AutoLog(value = "批准任务")
	@ApiOperation(value="批准任务", notes="批准任务")
	@PostMapping(value = "/approve/{id}")
	public Result<?> approveTask(@PathVariable("id") String id, @RequestBody ProcessTaskRequest request) {
		try {
			archiveTaskService.approveTask(id, request.getNotes());
			return Result.OK("任务已批准");
		} catch (Exception e) {
			return Result.error("操作失败: " + e.getMessage());
		}
	}

	@AutoLog(value = "拒绝任务")
	@ApiOperation(value="拒绝任务", notes="拒绝任务")
	@PostMapping(value = "/reject/{id}")
	public Result<?> rejectTask(@PathVariable("id") String id, @RequestBody ProcessTaskRequest request) {
		try {
			archiveTaskService.rejectTask(id, request.getNotes());
			return Result.OK("任务已拒绝");
		} catch (Exception e) {
			return Result.error("操作失败: " + e.getMessage());
		}
	}

}
