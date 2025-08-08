package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ArchiveCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 档案分类
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
public interface IArchiveCategoryService extends IService<ArchiveCategory> {

	/**
	 * 新增节点
	 *
	 * @param archiveCategory
	 */
	void addArchiveCategory(ArchiveCategory archiveCategory);

	/**
	 * 修改节点
	 *
	 * @param archiveCategory
	 * @throws Exception
	 */
	void updateArchiveCategory(ArchiveCategory archiveCategory) throws Exception;

	/**
	 * 删除节点
	 *
	 * @param id
	 * @throws Exception
	 */
	void deleteArchiveCategory(String id) throws Exception;

}
