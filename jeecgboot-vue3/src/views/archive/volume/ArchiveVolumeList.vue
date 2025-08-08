<template>
  <div>
    <!--引用表格-->
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <!--插槽:table标题-->
      <template #tableTitle>
        <a-button type="primary" @click="handleAdd" preIcon="ant-design:plus-outlined"> 新增案卷</a-button>
        <a-button type="primary" @click="handleGroup" preIcon="ant-design:folder-add-outlined"> 组卷</a-button>
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
    <ArchiveVolumeModal @register="registerModal" @success="handleSuccess"></ArchiveVolumeModal>
  </div>
</template>

<script lang="ts" name="archive-volume" setup>
  import { ref, computed, unref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { useListPage } from '/@/hooks/system/useListPage';
  import ArchiveVolumeModal from './components/ArchiveVolumeModal.vue';
  import { columns, searchFormSchema } from './ArchiveVolume.data';
  import { getList, deleteVolume, batchDeleteVolume } from '/@/api/archive/archiveVolume';

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
      },
      actionColumn: {
        width: 120,
        fixed: 'right',
      },
    },
  });

  const [registerTable, { reload }, { rowSelection, selectedRowKeys }] = tableContext;

  function handleAdd() {
    openModal(true, { isUpdate: false, showFooter: true });
  }

  function handleEdit(record: Recordable) {
    openModal(true, { record, isUpdate: true, showFooter: true });
  }

  function handleGroup() {
    // This would open a more complex modal to select items and a target volume
    console.log('组卷功能待实现');
  }

  async function handleDelete(record) {
    await deleteVolume({ id: record.id }, handleSuccess);
  }

  async function batchHandleDelete() {
    await batchDeleteVolume({ ids: selectedRowKeys.value }, handleSuccess);
  }

  function handleSuccess() {
    (selectedRowKeys.value = []) && reload();
  }

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
