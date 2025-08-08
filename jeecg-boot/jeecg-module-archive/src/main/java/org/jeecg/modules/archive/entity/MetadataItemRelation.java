package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 元数据与档案件关联表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "metadata_item_relation对象", description = "元数据与档案件关联表")
@Data
@TableName("metadata_item_relation")
public class MetadataItemRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**元数据ID*/
    @ApiModelProperty(value = "元数据ID")
    private String metadataId;
    /**档案件ID (实物或电子)*/
    @ApiModelProperty(value = "档案件ID")
    private String itemId;
    /**档案件类型*/
    @ApiModelProperty(value = "档案件类型 (physical, electronic)")
    private String itemType;
}
