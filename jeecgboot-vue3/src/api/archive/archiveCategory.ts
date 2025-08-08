import { defHttp } from '/@/utils/http/axios';
import { useMessage } from '/@/hooks/web/useMessage';

const { createConfirm } = useMessage();

enum Api {
  list = '/archive/archiveCategory/rootList',
  save = '/archive/archiveCategory/add',
  edit = '/archive/archiveCategory/edit',
  delete: '/archive/archiveCategory/delete',
  deleteBatch: '/archive/archiveCategory/deleteBatch',
  importExcel: '/archive/archiveCategory/importExcel',
  exportXls: '/archive/archiveCategory/exportXls',
  getChildList: '/archive/archiveCategory/childList',
  getChildListBatch: '/archive/archiveCategory/getChildListBatch',
}
/**
 * 导出api
 */
export const getExportUrl = Api.exportXls;
/**
 * 导入api
 */
export const getImportUrl = Api.importExcel;
/**
 * 列表接口
 * @param params
 */
export const getList = (params) => defHttp.get({ url: Api.list, params });
/**
 * 删除
 */
export const deleteCategory = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};
/**
 * 批量删除
 * @param params
 */
export const batchDeleteCategory = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({ url: Api.deleteBatch, data: params }, { joinParamsToUrl: true }).then(() => {
        handleSuccess();
      });
    },
  });
};
/**
 * 保存或者更新
 * @param params
 */
export const saveOrUpdate = (params, isUpdate) => {
  const url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
/**
 * 获取子节点
 * @param params
 */
export const getChildList = (params) => defHttp.get({ url: Api.getChildList, params });
/**
 * 批量获取子节点
 * @param params
 */
export const getChildListBatch = (params) => defHttp.get({ url: Api.getChildListBatch, params }, { isReturnNativeResponse: true });
