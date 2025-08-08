package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 电子文件表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "electronic_file对象", description = "电子文件表")
@Data
@TableName("electronic_file")
public class ElectronicFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**关联的档案件ID (可以是实物或电子)*/
    @Excel(name = "关联的档案件ID", width = 15)
    @ApiModelProperty(value = "关联的档案件ID")
    private String archiveItemId;
    /**档案件类型 (physical or electronic)*/
    @Excel(name = "档案件类型", width = 15)
    @ApiModelProperty(value = "档案件类型")
    private String archiveItemType;
    /**文件名*/
    @Excel(name = "文件名", width = 15)
    @ApiModelProperty(value = "文件名")
    private String fileName;
    /**文件路径*/
    @Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    /**文件大小*/
    @Excel(name = "文件大小", width = 15)
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    /**文件类型 (MIME)*/
    @Excel(name = "文件类型", width = 15)
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    /**删除标志*/
    @TableLogic
    private Integer delFlag;
}
