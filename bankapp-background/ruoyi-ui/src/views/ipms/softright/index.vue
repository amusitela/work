<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="登记部门" prop="registDepartId">
        <el-input
          v-model="queryParams.registDepartId"
          placeholder="请输入登记部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请日" prop="applyDate">
        <el-date-picker clearable
          v-model="queryParams.applyDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请日">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="联系人" prop="applyContacts">
        <el-input
          v-model="queryParams.applyContacts"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="软件名称" prop="softName">
        <el-input
          v-model="queryParams.softName"
          placeholder="请输入软件名称"
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
          v-hasPermi="['ipms:softright:add']"
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
          v-hasPermi="['ipms:softright:edit']"
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
          v-hasPermi="['ipms:softright:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:softright:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="softrightList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="院系/部门" align="center" prop="registDepartId"  width="150" />
      <el-table-column label="申请日" align="center" prop="applyDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" prop="applyContacts"  width="80" />
      <el-table-column label="联系电话" align="center" prop="applyPhone"   width="80"/>
      <el-table-column label="软件名称" align="center" prop="softName"  />
      <el-table-column label="软件分类" align="center" prop="softType"  width="80" />
      <el-table-column label="完成日期" align="center" prop="softFinishDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.softFinishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="权利人" align="center" prop="softObligee"  width="80" />
      <el-table-column label="申请人(公司)" align="center" prop="softApplyer"  width="100" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:softright:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:softright:remove']"
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

    <!-- 添加或修改软件著作权登记对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="院系/部门" prop="registDepartId">
          <treeselect v-model="form.registDepartId" :options="deptOptions" :normalizer="normalizer" placeholder="请选择部门" />
        </el-form-item>
        <el-form-item label="申请日" prop="applyDate">
          <el-date-picker clearable
            v-model="form.applyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="联系人" prop="applyContacts">
          <el-input v-model="form.applyContacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="applyPhone">
          <el-input v-model="form.applyPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="applyEmail">
          <el-input v-model="form.applyEmail" placeholder="请输入联系邮箱" />
        </el-form-item>
        <el-form-item label="软件名称" prop="softName">
          <el-input v-model="form.softName" placeholder="请输入软件名称" />
        </el-form-item>
        <el-form-item label="版本号" prop="softVersion">
          <el-input v-model="form.softVersion" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="软件类型" prop="softClass">
          <el-input v-model="form.softClass" placeholder="请输入软件类型" />
        </el-form-item>
        <el-form-item label="开发方式(合作/独立)" prop="softDevelopMode">
          <el-select v-model="form.softDevelopMode" placeholder="请输入开发方式(合作/独立)">
               <el-option value="合作">合作</el-option>
                <el-option value="独立">独立</el-option>
              </el-select>
        </el-form-item>
        <el-form-item label="开发环境/工具" prop="softDevelopTool">
          <el-input v-model="form.softDevelopTool" placeholder="请输入软件开发环境/开发工具" />
        </el-form-item>
        <el-form-item label="开发完成日期" prop="softFinishDate">
          <el-date-picker clearable
            v-model="form.softFinishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开发完成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="首次发表日期" prop="softPubDate">
          <el-date-picker clearable
            v-model="form.softPubDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择首次发表日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="权利人" prop="softObligee">
          <el-input v-model="form.softObligee" placeholder="请输入权利人" />
        </el-form-item>
        <el-form-item label="登记号" prop="softRegDate">
          <el-input v-model="form.softRegDate" placeholder="请输入登记号" />
        </el-form-item>
        <el-form-item label="申请人(公司)" prop="softApplyer">
          <el-input v-model="form.softApplyer" placeholder="请输入申请人(公司)" />
        </el-form-item>
        <el-form-item label="地址" prop="softAddress">
          <el-input v-model="form.softAddress" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="软件功能介绍" prop="softAbstract">
          <el-input v-model="form.softAbstract" type="textarea" placeholder="请输入内容" />
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
import { listSoftright, getSoftright, delSoftright, addSoftright, updateSoftright } from "@/api/ipms/softright";
import { listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Softright",
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 部门树选项
      deptOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 软件著作权登记表格数据
      softrightList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        registDepartId: null,
        applyDate: null,
        applyContacts: null,
        softName: null,
        softType: null,
        softApplyer: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applyDepartId: [
          { required: true, message: "部门不能为空", trigger: "blur" }
        ],
        softName: [
          { required: true, message: "软件名不能为空", trigger: "blur" }
        ],
        softObligee: [
          { required: true, message: "权利人不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询软件著作权登记列表 */
    getList() {
      this.loading = true;
      listSoftright(this.queryParams).then(response => {
        this.softrightList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
     /** 转换部门数据结构 */
     normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children
      };
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        softrightId: null,
        registPersonId: null,
        registDepartId: null,
        applyDate: null,
        applyContacts: null,
        applyPhone: null,
        applyEmail: null,
        softName: null,
        softVersion: null,
        softType: null,
        softClass: null,
        softDevelopMode: null,
        softDevelopTool: null,
        softFinishDate: null,
        softPubDate: null,
        softObligee: null,
        softRegDate: null,
        softApplyer: null,
        softAddress: null,
        softAbstract: null,
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
      this.ids = selection.map(item => item.softrightId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加软件著作权登记";
      listDept().then(response => {
        this.deptOptions = this.handleTree(response.data, "deptId");
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      listDept().then(response => {
        this.deptOptions = this.handleTree(response.data, "deptId");
      });
      const softrightId = row.softrightId || this.ids
      getSoftright(softrightId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改软件著作权登记";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.softrightId != null) {
            updateSoftright(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSoftright(this.form).then(response => {
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
      const softrightIds = row.softrightId || this.ids;
      this.$modal.confirm('是否确认删除软件著作权登记编号为"' + softrightIds + '"的数据项？').then(function() {
        return delSoftright(softrightIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/softright/export', {
        ...this.queryParams
      }, `softright_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
