package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 档案卷
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "archive_volume对象", description = "档案卷")
@Data
@TableName("archive_volume")
public class ArchiveVolume implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**父级ID (用于支持子卷)*/
    @Excel(name = "父级ID", width = 15)
    @ApiModelProperty(value = "父级ID")
    private String parentId;
    /**案卷类型 (physical, logical)*/
    @Excel(name = "案卷类型", width = 15, dicCode="volume_type")
    @Dict(dicCode="volume_type")
    @ApiModelProperty(value = "案卷类型")
    private String volumeType;
    /**案卷编号*/
    @Excel(name = "案卷编号", width = 15)
    @ApiModelProperty(value = "案卷编号")
    private String volumeNumber;
    /**题名*/
    @Excel(name = "题名", width = 15)
    @ApiModelProperty(value = "题名")
    private String title;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**删除标志*/
    @TableLogic
    private Integer delFlag;
}
