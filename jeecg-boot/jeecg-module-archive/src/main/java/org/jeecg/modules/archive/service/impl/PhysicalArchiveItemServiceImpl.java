package org.jeecg.modules.archive.service.impl;

import org.jeecg.modules.archive.entity.ElectronicFile;
import org.jeecg.modules.archive.entity.PhysicalArchiveItem;
import org.jeecg.modules.archive.mapper.PhysicalArchiveItemMapper;
import org.jeecg.modules.archive.service.IElectronicFileService;
import org.jeecg.modules.archive.service.IPhysicalArchiveItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 实物档案件
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
@Service
public class PhysicalArchiveItemServiceImpl extends ServiceImpl<PhysicalArchiveItemMapper, PhysicalArchiveItem> implements IPhysicalArchiveItemService {

    @Autowired
    private IElectronicFileService electronicFileService;

    // In a real app, this would be a configured, robust path
    @Value("${jeecg.path.upload}")
    private String uploadPath;

    private static final String ITEM_TYPE_PHYSICAL_SCAN = "PHYSICAL_SCAN";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadScans(String physicalItemId, List<MultipartFile> files) throws Exception {
        PhysicalArchiveItem item = this.getById(physicalItemId);
        if (item == null) {
            throw new Exception("Physical item not found with ID: " + physicalItemId);
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            // 1. Save the file to storage
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            File destFolder = new File(uploadPath + File.separator + "scans");
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(destFolder.getAbsolutePath(), fileName), StandardCopyOption.REPLACE_EXISTING);
            String filePath = destFolder.getAbsolutePath() + File.separator + fileName;

            // 2. Create ElectronicFile record
            ElectronicFile electronicFile = new ElectronicFile();
            electronicFile.setArchiveItemId(physicalItemId);
            electronicFile.setArchiveItemType(ITEM_TYPE_PHYSICAL_SCAN);
            electronicFile.setFileName(file.getOriginalFilename());
            electronicFile.setFilePath(filePath);
            electronicFile.setFileSize(file.getSize());
            electronicFile.setFileType(file.getContentType());

            electronicFileService.save(electronicFile);
        }
    }
}
