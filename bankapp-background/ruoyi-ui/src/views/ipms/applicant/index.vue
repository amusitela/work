<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名/名称" prop="applicantName">
        <el-input
          v-model="queryParams.applicantName"
          placeholder="请输入姓名或名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     
      <el-form-item label="电话" prop="applicantPhone">
        <el-input
          v-model="queryParams.applicantPhone"
          placeholder="请输入电话"
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
          v-hasPermi="['ipms:applicant:add']"
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
          v-hasPermi="['ipms:applicant:edit']"
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
          v-hasPermi="['ipms:applicant:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:applicant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
     
      <el-table-column label="姓名/名称" align="center" prop="applicantName"  width="150" />
      <el-table-column label="机构代码/身份证号" align="center" prop="applicantIdentity" width="150" />
      <el-table-column label="地址" align="center" prop="applicantAddress" />
      <el-table-column label="电话" align="center" prop="applicantPhone" width="100" />
      <el-table-column label="邮编" align="center" prop="applicantZipcode" width="80" />
      <el-table-column label="是否减费" align="center" prop="applicantIsReduce"  width="80" />
      <el-table-column label="排序" align="center" prop="applicantOrder" width="60" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:applicant:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:applicant:remove']"
          >删除</el-button>
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

    <!-- 添加或修改专利申请人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="550px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="申请ID">
          <el-input v-model="form.patentapplyId" :disabled="true" />
        </el-form-item>
        <el-form-item label="姓名/名称" prop="applicantName">
          <el-input v-model="form.applicantName" placeholder="请输入姓名或名称" />
        </el-form-item>
        <el-form-item label="机构/身份证号" prop="applicantIdentity">
          <el-input v-model="form.applicantIdentity" placeholder="请输入组织机构代码或身份证号" />
        </el-form-item>
        <el-form-item label="地址" prop="applicantAddress">
          <el-input v-model="form.applicantAddress" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="电话" prop="applicantPhone">
          <el-input v-model="form.applicantPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="邮编" prop="applicantZipcode">
          <el-input v-model="form.applicantZipcode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="是否减费" prop="applicantIsReduce">
          <el-radio-group v-model="form.applicantIsReduce">
                <el-radio :label="1">是</el-radio>
                <el-radio :label="0">否</el-radio>
              </el-radio-group>
        </el-form-item>
        <el-form-item label="排序" prop="applicantOrder">
          <el-input-number v-model="form.applicantOrder" controls-position="right" :min="0" />
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
import { listApplicant, getApplicant, delApplicant, addApplicant, updateApplicant } from "@/api/ipms/applicant";

export default {
  name: "Applicant",
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
      // 专利申请人信息表格数据
      applicantList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applicantName: null,
        applicantIdentity: null,
        applicantPhone: null,
        applicantOrder: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applicantName: [
          { required: true, message: "姓名/名称不能为空", trigger: "blur" }
        ],
        applicantIdentity: [
          { required: true, message: "机构代码/身份证号不能为空", trigger: "blur" }
        ]

      }
    };
  },
  created() {
    const applyId = this.$route.params && this.$route.params.applyId;
    this.queryParams.patentapplyId=applyId;
    this.getList();
  },
  methods: {
    /** 查询专利申请人信息列表 */
    getList() {
      this.loading = true;
      listApplicant(this.queryParams).then(response => {
        this.applicantList = response.rows;
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
        patentapplicantId: null,
        patentapplyId: null,
        applicantName: null,
        applicantIdentity: null,
        applicantAddress: null,
        applicantPhone: null,
        applicantZipcode: null,
        applicantIsReduce: null,
        applicantOrder: null,
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
      this.ids = selection.map(item => item.patentapplicantId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加专利申请人信息";
      this.form.patentapplyId=this.queryParams.patentapplyId;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const patentapplicantId = row.patentapplicantId || this.ids
      getApplicant(patentapplicantId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专利申请人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patentapplicantId != null) {
            updateApplicant(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApplicant(this.form).then(response => {
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
      const patentapplicantIds = row.patentapplicantId || this.ids;
      this.$modal.confirm('是否确认删除专利申请人信息编号为"' + patentapplicantIds + '"的数据项？').then(function() {
        return delApplicant(patentapplicantIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/applicant/export', {
        ...this.queryParams
      }, `applicant_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
