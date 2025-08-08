package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 档案业务任务表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "archive_task对象", description = "档案业务任务表")
@Data
@TableName("archive_task")
public class ArchiveTask implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**关联实体ID*/
    @Excel(name = "关联实体ID", width = 15)
    @ApiModelProperty(value = "关联实体ID")
    private String relatedEntityId;
    /**关联实体类型*/
    @Excel(name = "关联实体类型", width = 15, dicCode = "entity_type")
    @Dict(dicCode = "entity_type")
    @ApiModelProperty(value = "关联实体类型")
    private String relatedEntityType;
    /**任务类型*/
    @Excel(name = "任务类型", width = 15, dicCode = "task_type")
    @Dict(dicCode = "task_type")
    @ApiModelProperty(value = "任务类型")
    private String taskType;
    /**任务状态*/
    @Excel(name = "任务状态", width = 15, dicCode = "task_status")
    @Dict(dicCode = "task_status")
    @ApiModelProperty(value = "任务状态")
    private String status;
    /**任务发起人ID*/
    @Excel(name = "任务发起人", width = 15, dictTable="sys_user", dicText="realname", dicCode="username")
    @Dict(dictTable="sys_user", dicText="realname", dicCode="username")
    @ApiModelProperty(value = "任务发起人ID")
    private String creatorId;
    /**任务处理人ID*/
    @Excel(name = "任务处理人", width = 15, dictTable="sys_user", dicText="realname", dicCode="username")
    @Dict(dictTable="sys_user", dicText="realname", dicCode="username")
    @ApiModelProperty(value = "任务处理人ID")
    private String assigneeId;
    /**发起备注*/
    @Excel(name = "发起备注", width = 15)
    @ApiModelProperty(value = "发起备注")
    private String creationNotes;
    /**处理备注*/
    @Excel(name = "处理备注", width = 15)
    @ApiModelProperty(value = "处理备注")
    private String completionNotes;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
}
