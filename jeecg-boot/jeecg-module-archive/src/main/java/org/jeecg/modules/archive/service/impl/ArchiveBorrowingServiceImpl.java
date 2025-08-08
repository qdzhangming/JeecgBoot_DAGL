package org.jeecg.modules.archive.service.impl;

import org.jeecg.modules.archive.entity.ArchiveBorrowing;
import org.jeecg.modules.archive.entity.BorrowingItemRelation;
import org.jeecg.modules.archive.entity.PhysicalArchiveItem;
import org.jeecg.modules.archive.mapper.ArchiveBorrowingMapper;
import org.jeecg.modules.archive.mapper.BorrowingItemRelationMapper;
import org.jeecg.modules.archive.mapper.PhysicalArchiveItemMapper;
import org.jeecg.modules.archive.service.IArchiveBorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 借阅记录
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Service
public class ArchiveBorrowingServiceImpl extends JeecgServiceImpl<ArchiveBorrowingMapper, ArchiveBorrowing> implements IArchiveBorrowingService {

    @Autowired
    private PhysicalArchiveItemMapper physicalArchiveItemMapper;
    @Autowired
    private BorrowingItemRelationMapper borrowingItemRelationMapper;

    private static final String STATUS_ON_LOAN = "ON_LOAN";
    private static final String STATUS_IN_STOCK = "IN_STOCK";
    private static final String BORROWING_STATUS_RETURNED = "RETURNED";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void borrowPhysicalItems(ArchiveBorrowing borrowingRecord, List<String> physicalItemIds) throws Exception {
        // 1. Validate items
        for (String itemId : physicalItemIds) {
            PhysicalArchiveItem item = physicalArchiveItemMapper.selectById(itemId);
            if (item == null) {
                throw new Exception("Physical item with ID " + itemId + " not found.");
            }
            if (STATUS_ON_LOAN.equals(item.getStatus())) {
                throw new Exception("Item with ID " + itemId + " is already on loan.");
            }
        }

        // 2. Save the main borrowing record
        this.save(borrowingRecord);

        // 3. Create relations and update item statuses
        for (String itemId : physicalItemIds) {
            // Create relation
            BorrowingItemRelation rel = new BorrowingItemRelation();
            rel.setBorrowingId(borrowingRecord.getId());
            rel.setPhysicalItemId(itemId);
            borrowingItemRelationMapper.insert(rel);

            // Update item status
            PhysicalArchiveItem item = physicalArchiveItemMapper.selectById(itemId);
            item.setStatus(STATUS_ON_LOAN);
            physicalArchiveItemMapper.updateById(item);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnPhysicalItems(String borrowingId) throws Exception {
        ArchiveBorrowing borrowingRecord = this.getById(borrowingId);
        if (borrowingRecord == null) {
            throw new Exception("Borrowing record not found.");
        }

        // 1. Update borrowing record status
        borrowingRecord.setStatus(BORROWING_STATUS_RETURNED);
        borrowingRecord.setReturnDate(new Date());
        this.updateById(borrowingRecord);

        // 2. Find all related physical items and update their status
        List<BorrowingItemRelation> relations = borrowingItemRelationMapper.selectList(
            new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<BorrowingItemRelation>()
                .lambda().eq(BorrowingItemRelation::getBorrowingId, borrowingId)
        );

        for (BorrowingItemRelation rel : relations) {
            PhysicalArchiveItem item = physicalArchiveItemMapper.selectById(rel.getPhysicalItemId());
            if (item != null) {
                item.setStatus(STATUS_IN_STOCK);
                physicalArchiveItemMapper.updateById(item);
            }
        }
    }
}
