import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { rules } from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';

export const columns: BasicColumn[] = [
  {
    title: '档号',
    align: 'center',
    dataIndex: 'archiveNumber',
  },
  {
    title: '题名',
    align: 'center',
    dataIndex: 'title',
  },
  {
    title: '责任者',
    align: 'center',
    dataIndex: 'responsiblePerson',
  },
  {
    title: '文件形成时间',
    align: 'center',
    dataIndex: 'documentDate',
    customRender: ({ text }) => {
      return !text ? '' : text.length > 10 ? text.substr(0, 10) : text;
    },
  },
  {
    title: '密级',
    align: 'center',
    dataIndex: 'securityLevel',
    customRender: ({ text }) => {
      return render.renderDict(text, 'security_level');
    },
  },
  {
    title: '保管期限',
    align: 'center',
    dataIndex: 'retentionPeriod',
    customRender: ({ text }) => {
      return render.renderDict(text, 'retention_period');
    },
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    label: '档号',
    field: 'archiveNumber',
    component: 'Input',
  },
  {
    label: '题名',
    field: 'title',
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
    label: '档号',
    field: 'archiveNumber',
    component: 'Input',
    required: true,
  },
  {
    label: '题名',
    field: 'title',
    component: 'Input',
    required: true,
  },
  {
    label: '责任者',
    field: 'responsiblePerson',
    component: 'Input',
  },
  {
    label: '文件形成时间',
    field: 'documentDate',
    component: 'DatePicker',
  },
  {
    label: '密级',
    field: 'securityLevel',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'security_level',
    },
    required: true,
  },
  {
    label: '保管期限',
    field: 'retentionPeriod',
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'retention_period',
    },
    required: true,
  },
  {
    label: '全宗号',
    field: 'fondsNumber',
    component: 'Input',
  },
];
