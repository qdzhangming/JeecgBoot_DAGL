package org.jeecg.modules.archive.service.impl;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.archive.entity.ArchiveCategory;
import org.jeecg.modules.archive.mapper.ArchiveCategoryMapper;
import org.jeecg.modules.archive.service.IArchiveCategoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 档案分类
 * @Author: Jules
 * @Date:   2025-08-08
 * @Version: V1.0
 */
@Service
public class ArchiveCategoryServiceImpl extends ServiceImpl<ArchiveCategoryMapper, ArchiveCategory> implements IArchiveCategoryService {

	@Override
	public void addArchiveCategory(ArchiveCategory archiveCategory) {
		//判断新增的节点是否有父节点
		if(oConvertUtils.isNotEmpty(archiveCategory.getPid())){
			ArchiveCategory parent = baseMapper.selectById(archiveCategory.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(archiveCategory);
	}

	@Override
	public void updateArchiveCategory(ArchiveCategory archiveCategory) throws Exception {
		ArchiveCategory entity = this.getById(archiveCategory.getId());
		if(entity==null) {
			throw new Exception("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = archiveCategory.getPid();
		if(!oConvertUtils.equals(old_pid, new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isNotEmpty(new_pid)){
				baseMapper.updateById(new ArchiveCategory().setId(new_pid).setHasChild("1"));
			}
		}
		baseMapper.updateById(archiveCategory);
	}

	@Override
	public void deleteArchiveCategory(String id) throws Exception {
		ArchiveCategory archiveCategory = this.getById(id);
		if(archiveCategory==null) {
			throw new Exception("未找到对应实体");
		}
		updateOldParentNode(archiveCategory.getPid());
		baseMapper.deleteById(id);
	}


	/**
	 * 根据所传pid查询旧的父级节点并修改
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(oConvertUtils.isNotEmpty(pid)){
			Long count = baseMapper.selectCount(new QueryWrapper<ArchiveCategory>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateById(new ArchiveCategory().setId(pid).setHasChild("0"));
			}
		}
	}

}
