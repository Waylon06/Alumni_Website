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
      <el-form-item label="名字" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级id" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" 年级" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入 年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业id" prop="majorId">
        <el-input
          v-model="queryParams.majorId"
          placeholder="请输入专业id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
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
    <el-table :data="studentList" class="table-container">
      <el-table-column label="名字" align="center" prop="name" />
      <el-table-column label="学生id" align="center" prop="sid" />
      <el-table-column label="班级id" align="center" prop="classId" />
      <el-table-column label=" 年级" align="center" prop="grade" />
      <el-table-column label="专业id" align="center" prop="majorId" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="性别" align="center" prop="sex" />
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
            @click="handleEdit(scope.row)"
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
    <!-- 添加或修改学生管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入名字" />
        </el-form-item>
        <el-form-item label="班级id" prop="classesId">
          <el-input v-model="form.classId" placeholder="请输入班级id" />
        </el-form-item>
        <el-form-item label=" 年级" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入 年级" />
        </el-form-item>
        <el-form-item label="专业id" prop="majorId">
          <el-input v-model="form.majorId" placeholder="请输入专业id" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="form.sex" placeholder="请输入性别" />
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
  listStudent,
  getStudent,
  delStudent,
  addStudent,
  updateStudent,
  exportStudent,
} from "@/api/student";
export default {
  data() {
    return {
      // 学生管理表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        classId: null,
        grade: null,
        majorId: null,
        userId: null,
      },
      total: 0,
      // 表单参数
      form: {},
    };
  },
  methods: {
    resetForm(refName) {
      if (this.$refs[refName]) {
        this.$refs[refName].resetFields();
      }
    },
    reset() {
      this.form = {
        name: null,
        sid: null,
        classId: null,
        grade: null,
        majorId: null,
        userId: null,
        sex: null,
      };
      this.resetForm("form");
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleQuery() {
      this.getList();
    },
    // 查询学生管理列表
    getList() {
      //   this.loading = true;
      listStudent(this.queryParams).then((response) => {
        this.studentList = response.data.data.list;
        this.total = response.data.data.total;
        // this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生管理";
    },
    /** 修改按钮操作 */
    handleEdit(row) {
      this.reset();
      const sid = row.sid;
      getStudent(sid).then((response) => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改学生管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.sid != null) {
            updateStudent(this.form).then((response) => {
              //   this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudent(this.form).then((response) => {
              //   this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      delStudent(row.sid).then((response) => {
        //   this.$modal.msgSuccess("删除成功");
        this.getList();
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      window.location.href = "http://localhost:8089/api/student/export";
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
  created() {
    this.getList();
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