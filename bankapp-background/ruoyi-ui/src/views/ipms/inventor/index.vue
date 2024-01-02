<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="inventorName">
        <el-input
          v-model="queryParams.inventorName"
          placeholder="请输入发明人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="inventorPhone">
        <el-input
          v-model="queryParams.inventorPhone"
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
          v-hasPermi="['ipms:inventor:add']"
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
          v-hasPermi="['ipms:inventor:edit']"
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
          v-hasPermi="['ipms:inventor:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:inventor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="发明人姓名" align="center" prop="inventorName"  width="150" />
      <el-table-column label="身份证号" align="center" prop="inventorIdentity"  />
      <el-table-column label="邮箱" align="center" prop="inventorEmail"  width="150" />
      <el-table-column label="电话" align="center" prop="inventorPhone"  width="150" />
      <el-table-column label="排序" align="center" prop="inventorOrder"  width="100" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:inventor:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:inventor:remove']"
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

    <!-- 添加或修改专利发明人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请ID">
          <el-input v-model="form.patentapplyId" :disabled="true" />
        </el-form-item>
        <el-form-item label="姓名" prop="inventorName">
          <el-input v-model="form.inventorName" placeholder="请输入发明人姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="inventorIdentity">
          <el-input v-model="form.inventorIdentity" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="inventorEmail">
          <el-input v-model="form.inventorEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="电话" prop="inventorPhone">
          <el-input v-model="form.inventorPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="排序" prop="inventorOrder">
          <el-input-number v-model="form.inventorOrder" controls-position="right" :min="0" />
         
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
import { listInventor, getInventor, delInventor, addInventor, updateInventor } from "@/api/ipms/inventor";

export default {
  name: "Inventor",
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
      // 专利发明人信息表格数据
      inventorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patentapplyId:null,
        inventorName: null,
        inventorPhone: null,
        inventorOrder: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inventorName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        inventorIdentity: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
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
    /** 查询专利发明人信息列表 */
    getList() {
      this.loading = true;
      listInventor(this.queryParams).then(response => {
        this.inventorList = response.rows;
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
        patentinventorId: null,
        inventorName: null,
        inventorIdentity: null,
        inventorEmail: null,
        inventorPhone: null,
        inventorOrder: null,
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
      this.ids = selection.map(item => item.patentinventorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加专利发明人信息";
      this.form.patentapplyId=this.queryParams.patentapplyId;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const patentinventorId = row.patentinventorId || this.ids
      getInventor(patentinventorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专利发明人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patentinventorId != null) {
            updateInventor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInventor(this.form).then(response => {
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
      const patentinventorIds = row.patentinventorId || this.ids;
      this.$modal.confirm('是否确认删除专利发明人信息编号为"' + patentinventorIds + '"的数据项？').then(function() {
        return delInventor(patentinventorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/inventor/export', {
        ...this.queryParams
      }, `inventor_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
