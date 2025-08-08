package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ArchiveVolume;
import org.jeecg.modules.archive.entity.VolumeItemRelation;
import org.jeecg.common.system.base.service.JeecgService;
import java.util.List;

/**
 * @Description: 档案卷
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface IArchiveVolumeService extends JeecgService<ArchiveVolume> {

	/**
	 * 将一批档案件组合进一个案卷
	 * @param volumeId 目标案卷ID
	 * @param itemsToGroup 要添加的档案件列表
	 */
	void groupItemsIntoVolume(String volumeId, List<VolumeItemRelation> itemsToGroup);

	/**
	 * 将一个档案件从案卷中移除 (解卷)
	 * @param volumeId 案卷ID
	 * @param relationId 卷-件关系ID
	 */
	void unwrapItemFromVolume(String volumeId, String relationId);
}
