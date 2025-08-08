package org.jeecg.modules.archive.service.impl;

import org.jeecg.modules.archive.entity.ArchiveEntityCategoryRelation;
import org.jeecg.modules.archive.entity.ArchiveMetadata;
import org.jeecg.modules.archive.mapper.ArchiveEntityCategoryRelationMapper;
import org.jeecg.modules.archive.mapper.ArchiveMetadataMapper;
import org.jeecg.modules.archive.service.IArchiveMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Service
public class ArchiveMetadataServiceImpl extends JeecgServiceImpl<ArchiveMetadataMapper, ArchiveMetadata> implements IArchiveMetadataService {

    @Autowired
    private ArchiveEntityCategoryRelationMapper relationMapper;

    private static final String ENTITY_TYPE_METADATA = "METADATA";

    @Override
    public void restoreById(String id) {
        baseMapper.restoreById(id);
    }

    @Override
    public void permanentDeleteById(String id) {
        baseMapper.permanentDeleteById(id);
    }

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

    @Override
    @Transactional
    public void updateCategories(String metadataId, List<String> categoryIds) {
        // 1. Delete old relations
        QueryWrapper<ArchiveEntityCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entity_id", metadataId).eq("entity_type", ENTITY_TYPE_METADATA);
        relationMapper.delete(queryWrapper);

        // 2. Insert new relations
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (String categoryId : categoryIds) {
                ArchiveEntityCategoryRelation newRel = new ArchiveEntityCategoryRelation();
                newRel.setEntityId(metadataId);
                newRel.setEntityType(ENTITY_TYPE_METADATA);
                newRel.setCategoryId(categoryId);
                relationMapper.insert(newRel);
            }
        }
    }

    @Override
    @Transactional
    public void batchUpdate(List<String> ids, ArchiveMetadata metadataWithUpdates) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        UpdateWrapper<ArchiveMetadata> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);

        // Dynamically add fields to update
        boolean hasUpdate = false;
        if (metadataWithUpdates.getSecurityLevel() != null) {
            updateWrapper.set("security_level", metadataWithUpdates.getSecurityLevel());
            hasUpdate = true;
        }
        if (metadataWithUpdates.getResponsiblePerson() != null) {
            updateWrapper.set("responsible_person", metadataWithUpdates.getResponsiblePerson());
            hasUpdate = true;
        }
        // Add other fields here as needed...

        if (hasUpdate) {
            this.baseMapper.update(null, updateWrapper);
        }
    }
}
