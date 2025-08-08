import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/archive/archiveVolume/list',
  save = '/archive/archiveVolume/add',
  edit = '/archive/archiveVolume/edit',
  delete: '/archive/archiveVolume/delete',
  deleteBatch: '/archive/archiveVolume/deleteBatch',
  groupItems: '/archive/archiveVolume/groupItems',
  unwrapItem: '/archive/archiveVolume/unwrapItem',
}

/**
 * 列表接口
 * @param params
 */
export const getList = (params) => defHttp.get({ url: Api.list, params });

/**
 * 删除
 */
export const deleteVolume = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 批量删除
 */
export const batchDeleteVolume = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.deleteBatch, data: params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或者更新
 * @param params
 */
export const saveOrUpdateVolume = (params, isUpdate) => {
  const url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};

/**
 * 组卷
 */
export const groupItems = (params) => {
    return defHttp.post({ url: Api.groupItems, params });
};

/**
 * 解卷
 */
export const unwrapItem = (params) => {
    return defHttp.delete({ url: Api.unwrapItem, params }, { joinParamsToUrl: true });
};
