package org.jeecg.modules.archive.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 教学档案-借阅记录
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Data
@TableName("archive_borrowing")
@ApiModel(value="archive_borrowing对象", description="教学档案-借阅记录")
public class ArchiveBorrowing implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**借阅人*/
	@Excel(name = "借阅人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "借阅人")
	private String borrowerId;
	/**借阅人照片*/
	@Excel(name = "借阅人照片", width = 15)
    @ApiModelProperty(value = "借阅人照片")
	private String borrowerPhotoPath;
	/**借出日期*/
	@Excel(name = "借出日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借出日期")
	private Date borrowDate;
	/**应还日期*/
	@Excel(name = "应还日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "应还日期")
	private Date dueDate;
	/**实际归还日期*/
	@Excel(name = "实际归还日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "实际归还日期")
	private Date returnDate;
	/**借阅事由*/
	@Excel(name = "借阅事由", width = 15)
    @ApiModelProperty(value = "借阅事由")
	private String purpose;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "borrowing_status")
	@Dict(dicCode = "borrowing_status")
    @ApiModelProperty(value = "状态")
	private String status;
	/**经办人*/
	@Excel(name = "经办人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "经办人")
	private String handlerId;
}
