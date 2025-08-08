package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecgframework.poi.excel.annotation.Excel;


/**
 * @Description: 卷-件关系表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "volume_item_relation对象", description = "卷-件关系表")
@Data
@TableName("volume_item_relation")
public class VolumeItemRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**案卷ID*/
    @ApiModelProperty(value = "案卷ID")
    private String volumeId;
    /**档案件ID (实物或电子)*/
    @ApiModelProperty(value = "档案件ID")
    private String itemId;
    /**档案件类型*/
    @ApiModelProperty(value = "档案件类型 (physical, electronic)")
    private String itemType;
    /**卷内顺序号*/
    @Excel(name = "卷内顺序号", width = 15)
    @ApiModelProperty(value = "卷内顺序号")
    private Integer sortOrder;
}
