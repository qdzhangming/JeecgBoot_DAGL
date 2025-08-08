package org.jeecg.modules.archive.service;

import org.jeecg.modules.archive.entity.ElectronicFile;
import org.jeecg.common.system.base.service.JeecgService;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 电子文件表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
public interface IElectronicFileService extends JeecgService<ElectronicFile> {

    /**
     * Downloads a file, applying a watermark if it's a PDF.
     * @param fileId The ID of the file to download.
     * @param response The servlet response to write the file to.
     * @throws Exception
     */
    void downloadFileWithWatermark(String fileId, HttpServletResponse response) throws Exception;
}
