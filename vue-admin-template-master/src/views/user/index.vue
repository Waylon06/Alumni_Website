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
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="权限" prop="admin">
        <el-input
          v-model="queryParams.admin"
          placeholder="请输入权限"
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
    <el-table :data="userList" class="table-container">
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="头像" align="center" prop="avatar" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" style="width: 100px; height: 100px" />
        </template>
      </el-table-column>
      <el-table-column label="权限" align="center" prop="admin" />
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
    <!-- 添加或修改用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="头像照片链接" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像照片链接">
          </el-input>
        </el-form-item>
        <el-form-item label="权限" prop="admin">
          <el-input v-model="form.admin" placeholder="请输入权限" />
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
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/user";
export default {
  data() {
    return {
      // 用户管理表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        email: null,
        admin: null,
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
    /** 查询用户管理列表 */
    getList() {
      listUser(this.queryParams).then((response) => {
        this.userList = response.data.data.list;
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
        username: null,
        userId: null,
        password: null,
        email: null,
        avatar: null,
        admin: null,
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
      this.title = "添加用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId;
      getUser(userId).then((response) => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.userId != null) {
            updateUser(this.form).then((response) => {
              console.log(response);
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then((response) => {
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      delUser(row.userId).then((response) => {
        //   this.$modal.msgSuccess("删除成功");
        this.getList();
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      window.location.href = "http://localhost:8089/api/user/export";
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

<style>
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