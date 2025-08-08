package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 实体与分类的关联表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "archive_entity_category_relation对象", description = "实体与分类的关联表")
@Data
@TableName("archive_entity_category_relation")
public class ArchiveEntityCategoryRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**实体ID (metadata_id or volume_id)*/
    @ApiModelProperty(value = "实体ID")
    private String entityId;
    /**实体类型 ('METADATA', 'VOLUME')*/
    @ApiModelProperty(value = "实体类型")
    private String entityType;
    /**分类ID*/
    @ApiModelProperty(value = "分类ID")
    private String categoryId;
}
