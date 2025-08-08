import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';

export const columns: BasicColumn[] = [
  {
    title: '分类名称',
    align: 'left',
    dataIndex: 'name',

  },
  {
    title: '排序',
    align: 'center',
    dataIndex: 'sortCode',
  },
  {
    title: '创建时间',
    align: 'center',
    dataIndex: 'createTime',
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    label: '分类名称',
    field: 'name',
    component: 'Input',
  },
];

export const formSchema: FormSchema[] = [
  {
    label: 'id',
    field: 'id',
    component: 'Input',
    show: false,
  },
  {
    label: '父级节点',
    field: 'pid',
    component: 'JTreeSelect',
    componentProps: {
      dict: 'archive_category,name,id',
      pidField: 'pid',
      pidValue: '0',
      hasChildField: 'has_child',
    },
  },
  {
    label: '分类名称',
    field: 'name',
    component: 'Input',
    required: true,
  },
  {
    label: '排序',
    field: 'sortCode',
    component: 'InputNumber',
  },
];
