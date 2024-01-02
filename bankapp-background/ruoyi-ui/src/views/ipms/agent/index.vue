<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="代理人名" prop="agentName">
        <el-input
          v-model="queryParams.agentName"
          placeholder="请输入代理人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="agentPhone">
        <el-input
          v-model="queryParams.agentPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ipms:agent:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ipms:agent:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ipms:agent:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:agent:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="代理人姓名" align="center" prop="agentName" />
      <el-table-column label="性别" align="center" prop="agentSex" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.agentSex"/>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="agentAge" />
      <el-table-column label="邮箱" align="center" prop="agentEmail" />
      <el-table-column label="联系电话" align="center" prop="agentPhone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:agent:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:agent:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:agent:edit']">设为机构管理员</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改代理机构代理人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="代理机构" prop="agencyId">
          <el-input v-model="form.agencyId" :disabled="true"  />
        </el-form-item>
        <el-form-item label="代理人名" prop="agentName">
          <el-input v-model="form.agentName" placeholder="请输入代理人姓名" />
        </el-form-item>
        <el-form-item label="性别">
              <el-select v-model="form.agentSex" placeholder="请选择性别">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
        <el-form-item label="年龄" prop="agentAge">
          <el-input v-model="form.agentAge" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="邮箱" prop="agentEmail">
          <el-input v-model="form.agentEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="联系电话" prop="agentPhone">
          <el-input v-model="form.agentPhone" placeholder="请输入联系电话" />
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAgent, getAgent, delAgent, addAgent, updateAgent } from "@/api/ipms/agent";

export default {
  name: "Agent",
  dicts: ['sys_user_sex'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 代理机构代理人信息表格数据
      agentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        agencyId:null,
        agentName: null,
        agentSex: null,
        agentPhone: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        agentName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        agentPhone: [
          { required: true, message: "电话不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    const agencyId = this.$route.params && this.$route.params.agencyId;
    this.queryParams.agencyId=agencyId;
    this.getList();
  },
  methods: {
    /** 查询代理机构代理人信息列表 */
    getList() {
      this.loading = true;
      listAgent(this.queryParams).then(response => {
        this.agentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        agentId: null,
        agencyId: null,
        agentName: null,
        agentSex: null,
        agentAge: null,
        agentEmail: null,
        agentPhone: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.agentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加代理机构代理人信息";
      this.form.agencyId=this.queryParams.agencyId;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const agentId = row.agentId || this.ids
      getAgent(agentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改代理机构代理人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.agentId != null) {
            updateAgent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAgent(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const agentIds = row.agentId || this.ids;
      this.$modal.confirm('是否确认删除代理机构代理人信息编号为"' + agentIds + '"的数据项？').then(function() {
        return delAgent(agentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/agent/export', {
        ...this.queryParams
      }, `agent_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
