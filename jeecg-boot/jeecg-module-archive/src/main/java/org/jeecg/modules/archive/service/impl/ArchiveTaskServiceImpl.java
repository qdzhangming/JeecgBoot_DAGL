package org.jeecg.modules.archive.service.impl;

import org.jeecg.modules.archive.entity.ArchiveTask;
import org.jeecg.modules.archive.mapper.ArchiveTaskMapper;
import org.jeecg.modules.archive.service.IArchiveMetadataService;
import org.jeecg.modules.archive.service.IArchiveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 档案业务任务表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Service
public class ArchiveTaskServiceImpl extends JeecgServiceImpl<ArchiveTaskMapper, ArchiveTask> implements IArchiveTaskService {

    @Autowired
    private IArchiveMetadataService metadataService;

    private static final String TASK_TYPE_DESTRUCTION = "DESTRUCTION";
    private static final String ENTITY_TYPE_METADATA = "METADATA";
    private static final String TASK_STATUS_PENDING = "PENDING";
    private static final String TASK_STATUS_COMPLETED = "COMPLETED";
    private static final String TASK_STATUS_REJECTED = "REJECTED";

    @Override
    @Transactional
    public void createDestructionTasks(List<String> metadataIds, String creatorId, String assigneeId, String notes) {
        for (String metadataId : metadataIds) {
            ArchiveTask task = new ArchiveTask();
            task.setRelatedEntityId(metadataId);
            task.setRelatedEntityType(ENTITY_TYPE_METADATA);
            task.setTaskType(TASK_TYPE_DESTRUCTION);
            task.setStatus(TASK_STATUS_PENDING);
            task.setCreatorId(creatorId);
            task.setAssigneeId(assigneeId);
            task.setCreationNotes(notes);
            this.save(task);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveTask(String taskId, String completionNotes) throws Exception {
        ArchiveTask task = this.getById(taskId);
        if (task == null) {
            throw new Exception("Task not found.");
        }
        if (!TASK_STATUS_PENDING.equals(task.getStatus())) {
            throw new Exception("Task is not in a pending state.");
        }

        // --- Business Logic Trigger ---
        if (TASK_TYPE_DESTRUCTION.equals(task.getTaskType())) {
            if (ENTITY_TYPE_METADATA.equals(task.getRelatedEntityType())) {
                // Soft-delete the related metadata record
                metadataService.removeById(task.getRelatedEntityId());
            } else {
                // Handle other entity types like VOLUME here...
            }
        } else {
            // Handle other task types like APPRAISAL here...
        }
        // --- End Business Logic ---

        task.setStatus(TASK_STATUS_COMPLETED);
        task.setCompletionNotes(completionNotes);
        this.updateById(task);
    }

    @Override
    @Transactional
    public void rejectTask(String taskId, String completionNotes) throws Exception {
        ArchiveTask task = this.getById(taskId);
        if (task == null) {
            throw new Exception("Task not found.");
        }
        if (!TASK_STATUS_PENDING.equals(task.getStatus())) {
            throw new Exception("Task is not in a pending state.");
        }

        task.setStatus(TASK_STATUS_REJECTED);
        task.setCompletionNotes(completionNotes);
        this.updateById(task);
    }
}
