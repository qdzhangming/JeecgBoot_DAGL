import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';

export const columns: BasicColumn[] = [
  {
    title: '题名',
    align: 'left',
    dataIndex: 'title',
  },
  {
    title: '案卷编号',
    align: 'center',
    dataIndex: 'volumeNumber',
  },
  {
    title: '案卷类型',
    align: 'center',
    dataIndex: 'volumeType',
    customRender: ({ text }) => {
      return render.renderDict(text, 'volume_type');
    },
  },
  {
    title: '创建时间',
    align: 'center',
    dataIndex: 'createTime',
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    label: '题名',
    field: 'title',
    component: 'Input',
  },
  {
    label: '案卷编号',
    field: 'volumeNumber',
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
    label: '父级案卷',
    field: 'parentId',
    component: 'JTreeSelect',
    componentProps: {
      dict: 'archive_volume,title,id',
      pidField: 'parent_id',
      pidValue: '0',
    },
  },
  {
    label: '题名',
    field: 'title',
    component: 'Input',
    required: true,
  },
  {
    label: '案卷编号',
    field: 'volumeNumber',
    component: 'Input',
  },
  {
    label: '案卷类型',
    field: 'volumeType',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'volume_type',
    },
    required: true,
  },
];
