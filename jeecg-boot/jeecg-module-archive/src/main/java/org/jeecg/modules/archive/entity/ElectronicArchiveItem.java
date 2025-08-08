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
 * @Description: 电子档案件
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "electronic_archive_item对象", description = "电子档案件")
@Data
@TableName("electronic_archive_item")
public class ElectronicArchiveItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**版本号*/
    @Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private String version;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;
    /**删除标志*/
    @TableLogic
    private Integer delFlag;
}
