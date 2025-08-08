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
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "archive_metadata对象", description = "档案元数据")
@Data
@TableName("archive_metadata")
public class ArchiveMetadata implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**档号*/
    @Excel(name = "档号", width = 15)
    @ApiModelProperty(value = "档号")
    private String archiveNumber;
    /**题名*/
    @Excel(name = "题名", width = 15)
    @ApiModelProperty(value = "题名")
    private String title;
    /**责任者*/
    @Excel(name = "责任者", width = 15)
    @ApiModelProperty(value = "责任者")
    private String responsiblePerson;
    /**文件形成时间*/
    @Excel(name = "文件形成时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "文件形成时间")
    private Date documentDate;
    /**密级*/
    @Excel(name = "密级", width = 15, dicCode = "security_level")
    @Dict(dicCode = "security_level")
    @ApiModelProperty(value = "密级")
    private String securityLevel;
    /**保管期限*/
    @Excel(name = "保管期限", width = 15, dicCode = "retention_period")
    @Dict(dicCode = "retention_period")
    @ApiModelProperty(value = "保管期限")
    private String retentionPeriod;
    /**全宗号*/
    @Excel(name = "全宗号", width = 15)
    @ApiModelProperty(value = "全宗号")
    private String fondsNumber;
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
    /**删除标志*/
    @TableLogic
    private Integer delFlag;
}
