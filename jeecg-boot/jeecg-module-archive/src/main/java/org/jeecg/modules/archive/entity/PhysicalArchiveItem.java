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
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 实物档案件
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "physical_archive_item对象", description = "实物档案件")
@Data
@TableName("physical_archive_item")
public class PhysicalArchiveItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**介质类型*/
    @Excel(name = "介质类型", width = 15, dicCode = "media_type")
    @Dict(dicCode = "media_type")
    @ApiModelProperty(value = "介质类型")
    private String mediaType;
    /**页数*/
    @Excel(name = "页数", width = 15)
    @ApiModelProperty(value = "页数")
    private Integer pageCount;
    /**存放位置*/
    @Excel(name = "存放位置", width = 15, dictTable = "archive_storage", dicText = "name", dicCode = "id")
    @Dict(dictTable = "archive_storage", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "存放位置")
    private String storageId;
    /**状态*/
    @Excel(name = "状态", width = 15, dicCode = "physical_item_status")
    @Dict(dicCode = "physical_item_status")
    @ApiModelProperty(value = "状态")
    private String status;
    /**删除标志*/
    @TableLogic
    private Integer delFlag;
}
