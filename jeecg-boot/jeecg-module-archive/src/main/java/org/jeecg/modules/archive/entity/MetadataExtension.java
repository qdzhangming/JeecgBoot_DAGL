package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 元数据扩展表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "metadata_extension对象", description = "元数据扩展表")
@Data
@TableName("metadata_extension")
public class MetadataExtension implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**元数据ID*/
    @ApiModelProperty(value = "元数据ID")
    private String metadataId;
    /**扩展字段的Key*/
    @ApiModelProperty(value = "扩展字段的Key")
    private String metaKey;
    /**扩展字段的Value*/
    @ApiModelProperty(value = "扩展字段的Value")
    private String metaValue;
    /**定义该字段的分类ID*/
    @ApiModelProperty(value = "定义该字段的分类ID")
    private String categoryId;
}
