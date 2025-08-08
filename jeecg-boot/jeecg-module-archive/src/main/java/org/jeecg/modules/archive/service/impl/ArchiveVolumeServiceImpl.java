package org.jeecg.modules.archive.service.impl;

import org.jeecg.modules.archive.entity.ArchiveVolume;
import org.jeecg.modules.archive.entity.VolumeItemRelation;
import org.jeecg.modules.archive.mapper.ArchiveVolumeMapper;
import org.jeecg.modules.archive.mapper.VolumeItemRelationMapper;
import org.jeecg.modules.archive.service.IArchiveVolumeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;

/**
 * @Description: 档案卷
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Service
public class ArchiveVolumeServiceImpl extends JeecgServiceImpl<ArchiveVolumeMapper, ArchiveVolume> implements IArchiveVolumeService {

	@Autowired
	private VolumeItemRelationMapper volumeItemRelationMapper;

	@Override
	@Transactional
	public void groupItemsIntoVolume(String volumeId, List<VolumeItemRelation> itemsToGroup) {
		// First, determine the starting sort order
		QueryWrapper<VolumeItemRelation> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("volume_id", volumeId);
		long currentCount = volumeItemRelationMapper.selectCount(queryWrapper);

		int sortOrder = (int) currentCount + 1;

		for (VolumeItemRelation item : itemsToGroup) {
			item.setVolumeId(volumeId);
			item.setSortOrder(sortOrder++);
			volumeItemRelationMapper.insert(item);
		}
	}

	@Override
	@Transactional
	public void unwrapItemFromVolume(String volumeId, String relationId) {
		// Simple delete of the relation record.
		// A more complex implementation might need to re-order the remaining items.
		volumeItemRelationMapper.deleteById(relationId);
	}
}
