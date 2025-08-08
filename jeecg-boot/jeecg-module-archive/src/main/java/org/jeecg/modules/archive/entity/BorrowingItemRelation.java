package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 借阅与实物档案件关联表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@ApiModel(value = "borrowing_item_relation对象", description = "借阅与实物档案件关联表")
@Data
@TableName("borrowing_item_relation")
public class BorrowingItemRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**借阅记录ID*/
    @ApiModelProperty(value = "借阅记录ID")
    private String borrowingId;
    /**实物档案件ID*/
    @ApiModelProperty(value = "实物档案件ID")
    private String physicalItemId;
}
