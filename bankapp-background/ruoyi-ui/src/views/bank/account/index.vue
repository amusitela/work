<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="accountId">
        <el-input
          v-model="queryParams.accountId"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="idHolder">
        <el-input
          v-model="queryParams.idHolder"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="balance">
        <el-input
          v-model="queryParams.balance"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="openDate">
        <el-input
          v-model="queryParams.openDate"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="isActive">
        <el-input
          v-model="queryParams.isActive"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="lastUpdatedAt">
        <el-input
          v-model="queryParams.lastUpdatedAt"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['bank:account:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bank:account:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bank:account:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['bank:account:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="accountId" />
      <el-table-column label="${comment}" align="center" prop="idHolder" />
      <el-table-column label="${comment}" align="center" prop="accountType" />
      <el-table-column label="${comment}" align="center" prop="balance" />
      <el-table-column label="${comment}" align="center" prop="openDate" />
      <el-table-column label="${comment}" align="center" prop="address" />
      <el-table-column label="${comment}" align="center" prop="phoneNumber" />
      <el-table-column label="${comment}" align="center" prop="email" />
      <el-table-column label="${comment}" align="center" prop="isActive" />
      <el-table-column label="${comment}" align="center" prop="lastUpdatedAt" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['bank:account:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['bank:account:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改Account对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="accountRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="idHolder">
          <el-input v-model="form.idHolder" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="openDate">
          <el-input v-model="form.openDate" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="address">
          <el-input v-model="form.address" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="email">
          <el-input v-model="form.email" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="isActive">
          <el-input v-model="form.isActive" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="lastUpdatedAt">
          <el-input v-model="form.lastUpdatedAt" placeholder="请输入${comment}" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Account">
import { listAccount, getAccount, delAccount, addAccount, updateAccount } from "@/api/bank/account";

const { proxy } = getCurrentInstance();

const accountList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    accountId: null,
    idHolder: null,
    accountType: null,
    balance: null,
    openDate: null,
    address: null,
    phoneNumber: null,
    email: null,
    isActive: null,
    lastUpdatedAt: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询Account列表 */
function getList() {
  loading.value = true;
  listAccount(queryParams.value).then(response => {
    accountList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    accountId: null,
    idHolder: null,
    accountType: null,
    balance: null,
    openDate: null,
    address: null,
    phoneNumber: null,
    email: null,
    isActive: null,
    lastUpdatedAt: null
  };
  proxy.resetForm("accountRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.accountId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加Account";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _accountId = row.accountId || ids.value
  getAccount(_accountId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改Account";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["accountRef"].validate(valid => {
    if (valid) {
      if (form.value.accountId != null) {
        updateAccount(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAccount(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _accountIds = row.accountId || ids.value;
  proxy.$modal.confirm('是否确认删除Account编号为"' + _accountIds + '"的数据项？').then(function() {
    return delAccount(_accountIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('bank/account/export', {
    ...queryParams.value
  }, `account_${new Date().getTime()}.xlsx`)
}

getList();
</script>
