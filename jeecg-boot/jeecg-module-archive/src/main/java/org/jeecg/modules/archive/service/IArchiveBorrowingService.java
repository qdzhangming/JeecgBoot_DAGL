package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ArchiveBorrowing;
import org.jeecg.common.system.base.service.JeecgService;
import java.util.List;

/**
 * @Description: 借阅记录
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface IArchiveBorrowingService extends JeecgService<ArchiveBorrowing> {

    /**
     * 执行借阅流程
     * @param borrowingRecord 借阅主记录
     * @param physicalItemIds 要借阅的实物档案件ID列表
     * @throws Exception
     */
    void borrowPhysicalItems(ArchiveBorrowing borrowingRecord, List<String> physicalItemIds) throws Exception;

    /**
     * 执行归还流程
     * @param borrowingId 要归还的借阅记录ID
     * @throws Exception
     */
    void returnPhysicalItems(String borrowingId) throws Exception;

}
