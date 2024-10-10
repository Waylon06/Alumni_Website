<template>
  <div>
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      label-width="68px"
      class="query-form"
    >
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <div class="table-opts">
      <el-button
        type="primary"
        plain
        icon="el-icon-plus"
        size="mini"
        @click="handleAdd"
        >新增</el-button
      >
      <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="handleExport"
        >导出</el-button
      >
    </div>
    <el-table :data="feedbackList">
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="反馈id" align="center" prop="fid" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="table-pagination">
      <el-pagination
        :total="total"
        :page-sizes="[2, 5, 10]"
        layout="sizes, prev, pager, next, total"
        @next-click="nextClick"
        @prev-click="prevClick"
        @size-change="handleSizeChange"
        @current-change="currentChange"
      >
      </el-pagination>
    </div>
    <!-- 添加或修改反馈管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input
            type="textarea"
            autosize
            v-model="form.content"
            :min-height="192"
          />
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
import {
  listFeedback,
  getFeedback,
  delFeedback,
  addFeedback,
  updateFeedback,
} from "@/api/feedback";
export default {
  data() {
    return {
      // 反馈管理表格数据
      feedbackList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
      },
      total: 0,
      // 表单参数
      form: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询反馈管理列表 */
    getList() {
      listFeedback(this.queryParams).then((response) => {
        this.feedbackList = response.data.data.list;
        this.total = response.data.data.total;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    resetForm(refName) {
      if (this.$refs[refName]) {
        this.$refs[refName].resetFields();
      }
    },
    // 表单重置
    reset() {
      this.form = {
        title: null,
        fid: null,
        content: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加反馈管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fid = row.fid;
      getFeedback(fid).then((response) => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改反馈管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.fid != null) {
            updateFeedback(this.form).then((response) => {
              this.open = false;
              this.getList();
            });
          } else {
            addFeedback(this.form).then((response) => {
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      delFeedback(row.fid).then((response) => {
        this.getList();
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      window.location.href = "http://localhost:8089/api/feedback/export";
    },
    nextClick(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },
    prevClick(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
    },
    currentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },
  },
};
</script>

<style scoped>
.query-form {
  padding-top: 20px;
}
.table-opts {
  margin: 20px;
}
.table-container {
  margin: 0 20px;
}
.table-pagination {
  margin-top: 20px;
  text-align: right;
}
</style>