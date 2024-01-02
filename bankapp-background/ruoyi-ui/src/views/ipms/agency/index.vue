<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="机构名称" prop="agencyName">
        <el-input
          v-model="queryParams.agencyName"
          placeholder="请输入机构名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司性质" prop="agencyNature">
        <el-select v-model="queryParams.agencyNature" placeholder="请选择公司性质" clearable>
          <el-option
            v-for="dict in dict.type.agency_nature"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="机构代码" prop="corpCode">
        <el-input
          v-model="queryParams.corpCode"
          placeholder="请输入机构代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经营地址" prop="corpAddress">
        <el-input
          v-model="queryParams.corpAddress"
          placeholder="请输入经营地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业法人" prop="corpLegaler">
        <el-input
          v-model="queryParams.corpLegaler"
          placeholder="请输入企业法人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="corpContacts">
        <el-input
          v-model="queryParams.corpContacts"
          placeholder="请输入联系人"
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
          v-hasPermi="['ipms:agency:add']"
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
          v-hasPermi="['ipms:agency:edit']"
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
          v-hasPermi="['ipms:agency:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:agency:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="agencyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机构名称" align="center" prop="agencyName" />
      <el-table-column label="公司性质" align="center" prop="agencyNature"  width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.agency_nature" :value="scope.row.agencyNature"/>
        </template>
      </el-table-column>
      <el-table-column label="服务范围" align="center" prop="serviceScope" />
      <el-table-column label="机构代码" align="center" prop="corpCode"  width="100" />
      <el-table-column label="信用代码" align="center" prop="corpCreditcode"  width="80" />
      <el-table-column label="经营地址" align="center" prop="corpAddress" />
      <el-table-column label="企业法人" align="center" prop="corpLegaler" width="80" />
      <el-table-column label="联系电话" align="center" prop="corpPhone"  width="80" />
      <el-table-column label="联系人" align="center" prop="corpContacts"  width="80">
        <template slot-scope="scope">
          <router-link :to="'/ipms/agency-agent/index/' + scope.row.agencyId" class="link-type">
            <span>{{ scope.row.corpContacts }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="推荐等级" align="center" prop="priority"   width="80"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:agency:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:agency:remove']"
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

    <!-- 添加或修改代理机构信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="机构名称" prop="agencyName">
          <el-input v-model="form.agencyName" placeholder="请输入机构名称" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公司性质" prop="agencyNature">
          <el-select v-model="form.agencyNature" placeholder="请选择公司性质" mo>
            <el-option
              v-for="dict in dict.type.agency_nature"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="服务范围" prop="serviceScope">
          <el-input v-model="form.serviceScope" placeholder="请输入服务范围" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构代码" prop="corpCode">
          <el-input v-model="form.corpCode" placeholder="请输入机构代码" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="信用代码" prop="corpCreditcode">
          <el-input v-model="form.corpCreditcode" placeholder="请输入信用代码" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业法人" prop="corpLegaler">
          <el-input v-model="form.corpLegaler" placeholder="请输入企业法人" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="联系人" prop="corpContacts">
          <el-input v-model="form.corpContacts" placeholder="请输入联系人" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="corpPhone">
          <el-input v-model="form.corpPhone" placeholder="请输入联系电话" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="经营地址" prop="corpAddress">
          <el-input v-model="form.corpAddress" placeholder="请输入经营地址" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="推荐等级" prop="priority">
          <el-select v-model="form.priority" placeholder="请选择推荐等级">
            <el-option value="优先">优先</el-option>
            <el-option value="暂停">暂停</el-option>
            <el-option value="排除">排除</el-option>
          </el-select>     
        </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAgency, getAgency, delAgency, addAgency, updateAgency } from "@/api/ipms/agency";

export default {
  name: "Agency",
  dicts: ['agency_nature'],
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
      // 代理机构信息表格数据
      agencyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        agencyName: null,
        agencyNature: null,
        corpCode: null,
        corpAddress: null,
        corpLegaler: null,
        corpContacts: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        agencyName: [
          { required: true, message: "机构名不能为空", trigger: "blur" }
        ],
        corpLegaler: [
          { required: true, message: "法人不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询代理机构信息列表 */
    getList() {
      this.loading = true;
      listAgency(this.queryParams).then(response => {
        this.agencyList = response.rows;
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
        agencyId: null,
        agencyName: null,
        agencyNature: null,
        serviceScope: null,
        corpCode: null,
        corpCreditcode: null,
        corpAddress: null,
        corpLegaler: null,
        corpPhone: null,
        corpContacts: null,
        priority: null,
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
      this.ids = selection.map(item => item.agencyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加代理机构信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const agencyId = row.agencyId || this.ids
      getAgency(agencyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改代理机构信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.agencyId != null) {
            updateAgency(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAgency(this.form).then(response => {
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
      const agencyIds = row.agencyId || this.ids;
      this.$modal.confirm('是否确认删除代理机构信息编号为"' + agencyIds + '"的数据项？').then(function() {
        return delAgency(agencyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/agency/export', {
        ...this.queryParams
      }, `agency_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
