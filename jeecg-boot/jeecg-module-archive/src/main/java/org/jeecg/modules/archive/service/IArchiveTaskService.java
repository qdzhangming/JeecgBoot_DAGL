package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ArchiveTask;
import org.jeecg.common.system.base.service.JeecgService;
import java.util.List;

/**
 * @Description: 档案业务任务表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface IArchiveTaskService extends JeecgService<ArchiveTask> {

    /**
     * 为一批档案元数据创建销毁任务
     * @param metadataIds 要销毁的元数据ID列表
     * @param creatorId 发起人ID
     * @param assigneeId 处理人ID
     * @param notes 发起备注
     */
    void createDestructionTasks(List<String> metadataIds, String creatorId, String assigneeId, String notes);

    /**
     * 审批同意一个任务
     * @param taskId 任务ID
     * @param completionNotes 处理备注
     * @throws Exception
     */
    void approveTask(String taskId, String completionNotes) throws Exception;

    /**
     * 拒绝一个任务
     * @param taskId 任务ID
     * @param completionNotes 处理备注
     * @throws Exception
     */
    void rejectTask(String taskId, String completionNotes) throws Exception;

}
