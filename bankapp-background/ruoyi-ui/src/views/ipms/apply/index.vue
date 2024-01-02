<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="联系人" prop="applyContacts">
        <el-input
          v-model="queryParams.applyContacts"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="applyPhone">
        <el-input
          v-model="queryParams.applyPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专利名称" prop="patentName">
        <el-input
          v-model="queryParams.patentName"
          placeholder="请输入专利名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属领域" prop="patentField">
        <el-input
          v-model="queryParams.patentField"
          placeholder="请输入专利所属领域"
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
      <el-form-item label="专利范围" prop="patentScope">
        <el-input
          v-model="queryParams.patentScope"
          placeholder="请输入专利范围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人" prop="patentApplicant">
        <el-input
          v-model="queryParams.patentApplicant"
          placeholder="请输入申请人"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ipms:apply:add']"
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
          v-hasPermi="['ipms:apply:edit']"
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
          v-hasPermi="['ipms:apply:remove']"
        >删除</el-button>
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
      <el-table-column type="selection" width="55" align="center"
      :selectable="selectable"/>
      <el-table-column label="申请部门" align="center" prop="dept.deptName" />
      <el-table-column label="专利名称" align="center" prop="patentName" />
      <el-table-column label="专利类型" align="center" prop="patentType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patent_type" :value="scope.row.patentType"/>
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" prop="applyContacts" />
      <el-table-column label="联系电话" align="center" prop="applyPhone" />
      <el-table-column label="申请人" align="center" prop="patentApplicant" >
        <template slot-scope="scope">
          <router-link :to="'/ipms/apply-applicant/index/' + scope.row.patentapplyId" class="link-type">
            <span>点击添加</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="发明人" align="center" prop="patentInventor" >
        <template slot-scope="scope">
          <router-link :to="'/ipms/apply-inventor/index/'+ scope.row.patentapplyId" class="link-type">
            <span>点击添加</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="代理机构" align="center" prop="agency.agencyName" />
     
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  width="100">
        <template slot-scope="scope">
          <p v-if="scope.row.processInst==null || scope.row.processInst==''">
            <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ipms:apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ipms:apply:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-user"
            @click="submitapply(scope.row)"
            v-hasPermi="['ipms:apply:edit']"
          >提交申请</el-button>
          </p>
         <p v-else> 已提交
         </p>
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
        <el-row>
          <el-col :span="24">
            <el-form-item label="院系/部门" prop="applyDepartId">
              <treeselect v-model="form.applyDepartId" :options="deptOptions" :normalizer="normalizer" placeholder="请选择部门" />
           </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
                <el-form-item label="计划申请" prop="applyPlandate">
              <el-date-picker clearable
                v-model="form.applyPlandate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择计划申请日">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-form-item label="联系人" prop="applyContacts">
              <el-input v-model="form.applyContacts" placeholder="请输入联系人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
                <el-form-item label="专利名称" prop="patentName">
              <el-input v-model="form.patentName" placeholder="请输入专利名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="applyPhone">
          <el-input v-model="form.applyPhone" placeholder="请输入联系电话" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="专利所属领域" prop="patentField">
              <el-input v-model="form.patentField" placeholder="请输入专利所属领域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-form-item label="联系邮箱" prop="applyEmail">
            <el-input v-model="form.applyEmail" placeholder="请输入联系邮箱" />
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="专利类型" prop="patentType">
              <el-select v-model="form.patentType" placeholder="请选择专利类型">
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
              <el-radio-group v-model="form.patentIsJoinowned">
                  <el-radio :label="0" :key="0" >否</el-radio>
                  <el-radio :label="1" :key="1">是</el-radio>
                </el-radio-group>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="9">
            <el-form-item label="专利范围" prop="patentScope" >
              <el-select v-model="form.patentScope" placeholder="请选择专利范围" @change="patentScopeChange">
                <el-option value="国内">国内</el-option>
                <el-option value="PCT">PCT</el-option>
                </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="3"> <el-input v-model="form.patentPct" placeholder="请输入PCT国家名" v-show="ispatentPctshow" /></el-col>
        
          <el-col :span="12">
            <el-form-item label="专利权人" prop="patentPatentee">
              <el-input v-model="form.patentPatentee" placeholder="请输入专利权人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否涉密" prop="patentIsClassified">
              <el-radio-group v-model="form.patentIsClassified">
                <el-radio :label="0">否</el-radio>
                <el-radio :label="1">是</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否职务申请" prop="patentIsJobapp">
              <el-radio-group v-model="form.patentIsJobapp">
                <el-radio :label="0">否</el-radio>
                <el-radio :label="1">是</el-radio>
              </el-radio-group>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="依靠项目" prop="patentRelyproj">
          <el-input v-model="form.patentRelyproj" placeholder="请输入依靠项目" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="申报奖励情况" prop="patentDecreward">
          <el-input v-model="form.patentDecreward" placeholder="请输入申报奖励情况" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="14">
            <el-form-item label="代理机构ID" prop="patentAgencyId">
              <el-select v-model="form.patentAgencyId" placeholder="请选择代理机构"  @change="patentAgyChange" style="width：100%" > 
                <el-option
                  v-for="dict in agencyOptions"
                  :key="dict.agencyId"
                  :label="dict.agencyName"
                  :value="dict.agencyId"></el-option>
                </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="代理人" prop="patentAgent">
              <el-select v-model="form.patentAgent" placeholder="请选择代理人" style="width：100%" > 
                <el-option
                  v-for="dict in agentOptions"
                  :key="dict.agentId"
                  :label="dict.agentName"
                  :value="dict.agentName"></el-option>
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
import { listApply, getApply, delApply, addApply, updateApply } from "@/api/ipms/apply";
import { listDept} from "@/api/system/dept";
import { listAgency} from "@/api/ipms/agency";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {definitionStart} from "@/api/flowable/definition";
import {flowFormData} from "@/api/flowable/process";
import { listAgent } from "@/api/ipms/agent";

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
      //代理人选项
      agentOptions:[],
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
        applyDepartId: [
          { required: true, message: "部门不能为空", trigger: "blur" }
        ],
        patentName: [
          { required: true, message: "专利名称不能为空", trigger: "blur" }
        ],
        applyContacts: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        patentType: [
          { required: true, message: "专利类型不能为空", trigger: "blur" }
        ],
        applyPhone: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        applyEmail: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ], patentAgencyId: [
          { required: true, message: "代理机构不能为空", trigger: "blur" }
        ]
      },
      formConf: {}, // 默认表单数据
      variables: [], // 流程变量数据
      formData: {} // 填写的表单数据
    };
  },
  created() {
    this.getList();
    this.getFlowFormData(process.env.VUE_APP_PATENT_DEPLOYID);
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
    selectable(row,index){
      if(row.processInst==null || row.processInst=='')
         return 1;
      else
         return 0;
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
    //代理机构改变
    patentAgyChange(event){
      console.info(this.form.patentAgencyId);
      listAgent({"agencyId":this.form.patentAgencyId}).then(response => {
        this.agentOptions = response.rows;
      });
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加专利申请审批";
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
      const patentapplyId = row.patentapplyId || this.ids
      getApply(patentapplyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专利申请审批";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patentapplyId != null) {
            updateApply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApply(this.form).then(response => {
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
      const patentapplyIds = row.patentapplyId || this.ids;
      this.$modal.confirm('是否确认删除专利申请审批编号为"' + patentapplyIds + '"的数据项？').then(function() {
        return delApply(patentapplyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ipms/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    },
     /** 流程表单数据 */
     getFlowFormData(deployId) {
      const that = this
      const params = {deployId: deployId}
      flowFormData(params).then(res => {
        // 流程过程中不存在初始化表单 直接读取的流程变量中存储的表单值
          that.formConf = res.data;
      }).catch(res => {
        
      })
    },
    /**提交审批 */
    submitapply(row){
      console.info(row);
      this.formConf.fields[0].__config__.defaultValue=row.patentName;
      this.formConf.fields[1].__config__.defaultValue=row.dept.deptName;
      this.formConf.fields[2].__config__.defaultValue=row.applyContacts;
      this.formConf.fields[3].__config__.defaultValue=row.agency.agencyName;
      this.formConf.disabled = true;
      this.formConf.formBtns = false;
      // 启动流程并将表单数据加入流程变量
      const variables = {"patentName":row.patentName,"deptName":row.deptName,"applyContacts":row.applyContacts,"agencyName":row.agencyName};
      variables.variables = this.formConf;
      
      definitionStart(process.env.VUE_APP_PATENT_PROVDEFID, JSON.stringify(variables)).then(res => {
      console.info(res);
      if(res.code==200){
        row.processInst=res.data;
        this.reset();
        this.form.patentapplyId=row.patentapplyId;
        this.form.processInst=row.processInst;
        updateApply(this.form).then(response => {
              this.$modal.msgSuccess("提交成功");
            });
      }
     
      })
    }
  }
};
</script>
