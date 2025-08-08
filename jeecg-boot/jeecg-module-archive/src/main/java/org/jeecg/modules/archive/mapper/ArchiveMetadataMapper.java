package org.jeecg.modules.archive.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.archive.entity.ArchiveMetadata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 档案元数据
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface ArchiveMetadataMapper extends BaseMapper<ArchiveMetadata> {
    /**
     * 恢复被软删除的数据
     * @param id
     */
    void restoreById(@Param("id") String id);

    /**
     * 永久删除数据
     * @param id
     */
    void permanentDeleteById(@Param("id") String id);
}
