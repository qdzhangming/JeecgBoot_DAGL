-- ==============================================================
-- 教学档案管理系统 - 自定义业务表
-- Author: Jules
-- Date: 2025-08-07
-- ==============================================================

-- ----------------------------
-- Table structure for archive_category
-- ----------------------------
DROP TABLE IF EXISTS `archive_category`;
CREATE TABLE `archive_category` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `pid` varchar(36) DEFAULT NULL COMMENT '父级节点',
  `has_child` varchar(3) DEFAULT NULL COMMENT '是否有子节点',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `sort_code` int(11) DEFAULT NULL COMMENT '排序',
  `is_node` int(11) DEFAULT NULL COMMENT '是否为根节点',
  `root_node_id` varchar(36) DEFAULT NULL COMMENT '根节点ID',
  `tenant_id` varchar(36) DEFAULT NULL COMMENT '租户ID',
  `sys_org_code` varchar(64) DEFAULT NULL COMMENT '部门编码',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `del_flag` int(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='档案分类';

-- ----------------------------
-- Table structure for archive_metadata
-- ----------------------------
DROP TABLE IF EXISTS `archive_metadata`;
CREATE TABLE `archive_metadata` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `archive_number` varchar(255) DEFAULT NULL COMMENT '档号',
  `title` varchar(255) DEFAULT NULL COMMENT '题名',
  `responsible_person` varchar(100) DEFAULT NULL COMMENT '责任者',
  `document_date` date DEFAULT NULL COMMENT '文件形成时间',
  `security_level` varchar(50) DEFAULT NULL COMMENT '密级',
  `retention_period` varchar(50) DEFAULT NULL COMMENT '保管期限',
  `fonds_number` varchar(100) DEFAULT NULL COMMENT '全宗号',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='档案元数据';

-- ----------------------------
-- Table structure for physical_archive_item
-- ----------------------------
DROP TABLE IF EXISTS `physical_archive_item`;
CREATE TABLE `physical_archive_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `media_type` varchar(50) DEFAULT NULL COMMENT '介质类型',
  `page_count` int(11) DEFAULT NULL COMMENT '页数',
  `storage_id` varchar(36) DEFAULT NULL COMMENT '存放位置',
  `status` varchar(50) DEFAULT 'IN_STOCK' COMMENT '状态 (IN_STOCK, ON_LOAN)',
  `del_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='实物档案件';

-- ----------------------------
-- Table structure for electronic_archive_item
-- ----------------------------
DROP TABLE IF EXISTS `electronic_archive_item`;
CREATE TABLE `electronic_archive_item` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `version` varchar(50) DEFAULT NULL COMMENT '版本号',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `del_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子档案件';

-- ----------------------------
-- Table structure for electronic_file
-- ----------------------------
DROP TABLE IF EXISTS `electronic_file`;
CREATE TABLE `electronic_file` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `archive_item_id` varchar(36) DEFAULT NULL COMMENT '关联的档案件ID',
  `archive_item_type` varchar(50) DEFAULT NULL COMMENT '档案件类型',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型 (MIME)',
  `del_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子文件表';

-- ----------------------------
-- Table structure for metadata_item_relation
-- ----------------------------
DROP TABLE IF EXISTS `metadata_item_relation`;
CREATE TABLE `metadata_item_relation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `metadata_id` varchar(36) NOT NULL COMMENT '元数据ID',
  `item_id` varchar(36) NOT NULL COMMENT '档案件ID',
  `item_type` varchar(50) NOT NULL COMMENT '档案件类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元数据与档案件关联表';

-- ----------------------------
-- Table structure for archive_volume
-- ----------------------------
DROP TABLE IF EXISTS `archive_volume`;
CREATE TABLE `archive_volume` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `parent_id` varchar(36) DEFAULT NULL COMMENT '父级ID',
  `volume_type` varchar(50) DEFAULT NULL COMMENT '案卷类型',
  `volume_number` varchar(100) DEFAULT NULL COMMENT '案卷编号',
  `title` varchar(255) DEFAULT NULL COMMENT '题名',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='档案卷';

-- ----------------------------
-- Table structure for volume_item_relation
-- ----------------------------
DROP TABLE IF EXISTS `volume_item_relation`;
CREATE TABLE `volume_item_relation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `volume_id` varchar(36) NOT NULL COMMENT '案卷ID',
  `item_id` varchar(36) NOT NULL COMMENT '档案件ID',
  `item_type` varchar(50) NOT NULL COMMENT '档案件类型',
  `sort_order` int(11) DEFAULT NULL COMMENT '卷内顺序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卷-件关系表';

-- ----------------------------
-- Table structure for metadata_extension
-- ----------------------------
DROP TABLE IF EXISTS `metadata_extension`;
CREATE TABLE `metadata_extension` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `metadata_id` varchar(36) NOT NULL COMMENT '元数据ID',
  `meta_key` varchar(100) NOT NULL COMMENT '扩展字段的Key',
  `meta_value` text COMMENT '扩展字段的Value',
  `category_id` varchar(36) DEFAULT NULL COMMENT '定义该字段的分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元数据扩展表';

-- ----------------------------
-- Table structure for archive_task
-- ----------------------------
DROP TABLE IF EXISTS `archive_task`;
CREATE TABLE `archive_task` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `related_entity_id` varchar(36) DEFAULT NULL,
  `related_entity_type` varchar(50) DEFAULT NULL,
  `task_type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `creator_id` varchar(36) DEFAULT NULL,
  `assignee_id` varchar(36) DEFAULT NULL,
  `creation_notes` text,
  `completion_notes` text,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='档案业务任务表';

-- ----------------------------
-- Table structure for archive_borrowing
-- ----------------------------
DROP TABLE IF EXISTS `archive_borrowing`;
CREATE TABLE `archive_borrowing` (
  `id` varchar(36) NOT NULL,
  `borrower_id` varchar(36) DEFAULT NULL,
  `borrower_photo_path` varchar(500) DEFAULT NULL,
  `borrow_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `purpose` text,
  `status` varchar(50) DEFAULT NULL,
  `handler_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借阅记录';

-- ----------------------------
-- Table structure for borrowing_item_relation
-- ----------------------------
DROP TABLE IF EXISTS `borrowing_item_relation`;
CREATE TABLE `borrowing_item_relation` (
  `id` varchar(36) NOT NULL,
  `borrowing_id` varchar(36) NOT NULL,
  `physical_item_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借阅与实物档案件关联表';

-- ----------------------------
-- Table structure for archive_entity_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `archive_entity_category_relation`;
CREATE TABLE `archive_entity_category_relation` (
  `id` varchar(36) NOT NULL,
  `entity_id` varchar(36) NOT NULL,
  `entity_type` varchar(50) NOT NULL,
  `category_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='实体与分类的关联表';
