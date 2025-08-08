package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.PhysicalArchiveItem;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Description: 实物档案件
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
public interface IPhysicalArchiveItemService extends IService<PhysicalArchiveItem> {

    /**
     * 为一个实物档案件批量上传扫描文件
     * @param physicalItemId 实物档案件ID
     * @param files 上传的文件列表
     * @throws Exception
     */
    void uploadScans(String physicalItemId, List<MultipartFile> files) throws Exception;
}
