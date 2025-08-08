package org.jeecg.modules.archive.service.impl;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.archive.entity.ElectronicFile;
import org.jeecg.modules.archive.mapper.ElectronicFileMapper;
import org.jeecg.modules.archive.service.IElectronicFileService;
import org.jeecg.modules.archive.service.IWatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 电子文件表
 * @Author: Jules
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Service
public class ElectronicFileServiceImpl extends JeecgServiceImpl<ElectronicFileMapper, ElectronicFile> implements IElectronicFileService {

    @Autowired
    private IWatermarkService watermarkService;

    @Override
    public void downloadFileWithWatermark(String fileId, HttpServletResponse response) throws Exception {
        ElectronicFile fileInfo = this.getById(fileId);
        if (fileInfo == null) {
            throw new Exception("File not found.");
        }

        // In a real application, this would come from a file storage service like MinIO
        File file = new File(fileInfo.getFilePath());
        if (!file.exists()) {
            throw new Exception("Physical file not found at path: " + fileInfo.getFilePath());
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileInfo.getFileName(), "UTF-8"));

        try (InputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            if ("application/pdf".equalsIgnoreCase(fileInfo.getFileType())) {
                // It's a PDF, apply watermark
                LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                String watermarkText = "User: " + user.getRealname() + " Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                watermarkService.addWatermark(fis, os, watermarkText);
            } else {
                // Not a PDF, just stream the file
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
