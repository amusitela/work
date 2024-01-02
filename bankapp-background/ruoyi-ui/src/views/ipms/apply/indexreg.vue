<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
     
      <el-form-item label="专利名称" prop="patentName">
        <el-input
          v-model="queryParams.patentName"
          placeholder="请输入专利名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专利类型" prop="patentType">
        <el-select v-model="queryParams.patentType" placeholder="请选择专利类型" clearable>
          <el-option
            v-for="dict in dict.type.patent_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="代理机构" prop="patentAgencyId">
        <el-input
          v-model="queryParams.patentAgencyId"
          placeholder="请输入代理机构"
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
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleReg"
          v-hasPermi="['ipms:apply:edit']"
        >登记</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ipms:apply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请部门" align="center" prop="dept.deptName"  width="100" />
      <el-table-column label="专利名称" align="center" prop="patentName" />
      <el-table-column label="专利类型" align="center" prop="patentType"  width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patent_type" :value="scope.row.patentType"/>
        </template>
      </el-table-column>
      <el-table-column label="申请人" align="center" prop="patentApplicant"  width="100" >
        <template slot-scope="scope">
          <router-link :to="'/ipms/apply-applicant/index/' + scope.row.patentapplyId" class="link-type">
            <span>点击查看</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="发明人" align="center" prop="patentInventor"  width="100">
        <template slot-scope="scope">
          <router-link :to="'/ipms/apply-inventor/index/'+ scope.row.patentapplyId" class="link-type">
            <span>点击查看</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="代理机构" align="center" prop="agency.agencyName" />
      <el-table-column label="公开号" align="center" prop="patentPubNum" />
      <el-table-column label="公开日" align="center" prop="patentPubDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patentPubDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请日" align="center" prop="patentApplyDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patentApplyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请公开日" align="center" prop="patentApplyPubdate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patentApplyPubdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleReg(scope.row)"
            v-hasPermi="['ipms:apply:edit']">登记</el-button>  
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

    <!-- 添加或修改专利申请审批对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-collapse v-model="activePanel" >
        <el-collapse-item title="登记信息">
          <el-row>
          <el-col :span="24">
            <el-form-item label="部   门" prop="applyDepartId">
              <treeselect v-model="form.applyDepartId" :options="deptOptions" :normalizer="normalizer" :disabled="true" />
           </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
                <el-form-item label="专利名称" prop="patentName">
              <el-input v-model="form.patentName" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
                <el-form-item label="计划申请日" prop="applyPlandate">
              <el-date-picker clearable
                v-model="form.applyPlandate"
                type="date"
                value-format="yyyy-MM-dd"
                :disabled="true">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="公开号" prop="patentPubNum">
          <el-input v-model="form.patentPubNum" placeholder="请输入公开号" />
        </el-form-item>     
          </el-col>
          <el-col :span="12">
            <el-form-item label="申请日" prop="patentApplyDate">
          <el-date-picker clearable
            v-model="form.patentApplyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请日">
          </el-date-picker>
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="公开日" prop="patentPubDate">
          <el-date-picker clearable
            v-model="form.patentPubDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择公开日">
          </el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申请公开日" prop="patentApplyPubdate">
          <el-date-picker clearable
            v-model="form.patentApplyPubdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请公开日">
          </el-date-picker>
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
              <el-form-item label="摘   要" prop="patentAbstract">
                <el-input v-model="form.patentAbstract" type="textarea" placeholder="请输入内容" />
              </el-form-item>
          </el-col>
        </el-row>
        </el-collapse-item>
        <el-collapse-item title="申请信息">
        <el-row>
          <el-col :span="12">
              <el-form-item label="联系人" prop="applyContacts">
              <el-input v-model="form.applyContacts" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="applyPhone">
          <el-input v-model="form.applyPhone" :disabled="true" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="专利所属领域" prop="patentField">
              <el-input v-model="form.patentField" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-form-item label="联系邮箱" prop="applyEmail">
            <el-input v-model="form.applyEmail" :disabled="true" />
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="专利类型" prop="patentType">
              <el-select v-model="form.patentType" :disabled="true">
                <el-option
                  v-for="dict in dict.type.patent_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"></el-option>
                </el-select>
          </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-form-item label="是否共有" prop="patentIsJoinowned">
              <el-radio-group v-model="form.patentIsJoinowned" :disabled="true">
                  <el-radio :label="0">否</el-radio>
                  <el-radio :label="1">是</el-radio>
                </el-radio-group>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="9">
            <el-form-item label="专利范围" prop="patentScope" >
              <el-select v-model="form.patentScope" :disabled="true" @change="patentScopeChange">
                <el-option value="国内">国内</el-option>
                <el-option value="PCT">PCT</el-option>
                </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="3"> <el-input v-model="form.patentPct" :disabled="true" v-show="ispatentPctshow" /></el-col>
        
          <el-col :span="12">
            <el-form-item label="专利权人" prop="patentPatentee">
              <el-input v-model="form.patentPatentee" :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否涉密" prop="patentIsClassified" >
              <el-radio-group v-model="form.patentIsClassified" :disabled="true">
                <el-radio :label="0" >否</el-radio>
                <el-radio :label="1">是</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否职务申请" prop="patentIsJobapp" >
              <el-radio-group v-model="form.patentIsJobapp" :disabled="true">
                <el-radio :label="0">否</el-radio>
                <el-radio :label="1">是</el-radio>
              </el-radio-group>
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="依靠项目" prop="patentRelyproj">
          <el-input v-model="form.patentRelyproj" :disabled="true" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="申报奖励情况" prop="patentDecreward">
          <el-input v-model="form.patentDecreward" :disabled="true" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="代理机构ID" prop="patentAgencyId">
              <el-select v-model="form.patentAgencyId" :disabled="true" style="width：100%" > 
                <el-option
                  v-for="dict in agencyOptions"
                  :key="dict.agencyId"
                  :label="dict.agencyName"
                  :value="dict.agencyId"></el-option>
                </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="代理人" prop="patentAgent">
          <el-input v-model="form.patentAgent" :disabled="true" />
        </el-form-item>
          </el-col>
        </el-row>

        </el-collapse-item>
      </el-collapse>
       
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApply, getApply, delApply, addApply, updateApply } from "@/api/ipms/apply";
import { listDept} from "@/api/system/dept";
import { listAgency} from "@/api/ipms/agency";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Apply",
  dicts: ['patent_type'],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 部门树选项
      deptOptions: [],
      //代理机构选项
      agencyOptions:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 专利申请审批表格数据
      applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      ispatentPctshow:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        applyContacts: null,
        applyPhone: null,
        patentName: null,
        patentField: null,
        patentType: null,
        patentScope: null,
        patentApplicant: null,
        patentAgencyId: null,
        patentIsClassified: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patentPubNum: [
          { required: true, message: "公开号不能为空", trigger: "blur" }
        ],
        patentPubDate: [
          { required: true, message: "公开日期不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询专利申请审批列表 */
    getList() {
      this.loading = true;
      listApply(this.queryParams).then(response => {
        this.applyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listAgency().then(response => {
        this.agencyOptions = response.rows;
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
    // 显示PCT国家录入框
    patentScopeChange(){
      console.info(this.form.patentScope);
      if(this.form.patentScope=="PCT")
      this.ispatentPctshow=true;
       else
       this.ispatentPctshow=false;
    },
    // 表单重置
    reset() {
      this.form = {
        patentapplyId: null,
        applyPersionId: null,
        applyDepartId: null,
        applyPlandate: null,
        applyContacts: null,
        applyPhone: null,
        applyEmail: null,
        patentName: null,
        patentField: null,
        patentType: null,
        patentScope: null,
        patentPct: null,
        patentIsJoinowned: null,
        patentApplicant: null,
        patentApplicantAddress: null,
        patentInventor: null,
        patentRelyproj: null,
        patentDecreward: null,
        patentAgencyId: null,
        patentAgent: null,
        patentPatentee: null,
        patentIsClassified: null,
        patentIsJobapp: null,
        patentPubNum: null,
        patentPubDate: null,
        patentApplyDate: null,
        patentApplyPubdate: null,
        patentAbstract: null,
        processInst: null,
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
      this.ids = selection.map(item => item.patentapplyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleReg(row) {
      this.reset();
      listDept().then(response => {
        this.deptOptions = this.handleTree(response.data, "deptId");
      });
      const patentapplyId = row.patentapplyId || this.ids
      getApply(patentapplyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "专利申请登记";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patentapplyId != null) {
            updateApply(this.form).then(response => {
              this.$modal.msgSuccess("登记成功");
              this.open = false;
              this.getList();
            });
          } 
        }
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
