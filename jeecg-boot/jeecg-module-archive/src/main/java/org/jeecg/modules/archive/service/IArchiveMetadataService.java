package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ArchiveMetadata;
import org.jeecg.common.system.base.service.JeecgService;

/**
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
import org.jeecg.modules.archive.entity.ArchiveMetadata;
import org.jeecg.common.system.base.service.JeecgService;
import java.util.List;

/**
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface IArchiveMetadataService extends JeecgService<ArchiveMetadata> {

    /**
     * 恢复被软删除的数据
     * @param id
     */
    void restoreById(String id);

    /**
     * 永久删除数据
     * @param id
     */
    void permanentDeleteById(String id);

    /**
     * 更新一个元数据实体所关联的所有分类
     * @param metadataId 元数据ID
     * @param categoryIds 分类ID列表
     */
    void updateCategories(String metadataId, List<String> categoryIds);

    /**
     * 批量更新实体
     * @param ids 要更新的实体ID列表
     * @param metadataWithUpdates 包含要更新的字段和值的实体对象
     */
    void batchUpdate(List<String> ids, ArchiveMetadata metadataWithUpdates);
}
