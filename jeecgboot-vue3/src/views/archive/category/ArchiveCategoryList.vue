<template>
  <div>
    <!--引用表格-->
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <!--插槽:table标题-->
      <template #tableTitle>
        <a-button type="primary" @click="handleAdd" preIcon="ant-design:plus-outlined"> 新增</a-button>
        <a-dropdown v-if="selectedRowKeys.length > 0">
          <template #overlay>
            <a-menu>
              <a-menu-item key="1" @click="batchHandleDelete">
                <Icon icon="ant-design:delete-outlined"></Icon>
                删除
              </a-menu-item>
            </a-menu>
          </template>
          <a-button>批量操作
            <Icon icon="mdi:chevron-down"></Icon>
          </a-button>
        </a-dropdown>
      </template>
      <!--操作栏-->
      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)"/>
      </template>
    </BasicTable>
    <!-- 表单区域 -->
    <ArchiveCategoryModal @register="registerModal" @success="handleSuccess"></ArchiveCategoryModal>
  </div>
</template>

<script lang="ts" name="archive-category" setup>
  import { ref, computed, unref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { useListPage } from '/@/hooks/system/useListPage';
  import ArchiveCategoryModal from './components/ArchiveCategoryModal.vue';
  import { columns, searchFormSchema } from './ArchiveCategory.data';
  import { getList, getChildList, deleteCategory, batchDeleteCategory } from '/@/api/archive/archiveCategory';

  const [registerModal, { openModal }] = useModal();
  const { tableContext } = useListPage({
    tableProps: {
      api: getList,
      columns,
      canResize: false,
      isTree: true,
      rowKey: 'id',
      formConfig: {
        labelWidth: 120,
        schemas: searchFormSchema,
        autoSubmitOnEnter: true,
        showAdvancedButton: true,
        fieldMapToNumber: [],
        fieldMapToTime: [],
      },
      actionColumn: {
        width: 120,
        fixed: 'right',
      },
    },
  });

  const [registerTable, { reload, expandAll, collapseAll, updateTableDataRecord }, { rowSelection, selectedRowKeys }] = tableContext;

  /**
   * 新增事件
   */
  function handleAdd() {
    openModal(true, {
      isUpdate: false,
      showFooter: true,
    });
  }
  /**
   * 编辑事件
   */
  function handleEdit(record: Recordable) {
    openModal(true, {
      record,
      isUpdate: true,
      showFooter: true,
    });
  }
  /**
   * 删除事件
   */
  async function handleDelete(record) {
    await deleteCategory({ id: record.id }, handleSuccess);
  }
  /**
   * 批量删除事件
   */
  async function batchHandleDelete() {
    await batchDeleteCategory({ ids: selectedRowKeys.value }, handleSuccess);
  }
  /**
   * 成功回调
   */
  function handleSuccess() {
    reload();
  }
  /**
   * 操作栏
   */
  function getTableAction(record) {
    return [
      {
        label: '编辑',
        onClick: handleEdit.bind(null, record),
      },
      {
        label: '删除',
        popConfirm: {
          title: '是否确认删除',
          confirm: handleDelete.bind(null, record),
        },
      },
    ];
  }
</script>
