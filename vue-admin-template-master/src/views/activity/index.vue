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
      <el-form-item label="地点" prop="place">
        <el-input
          v-model="queryParams.place"
          placeholder="请输入地点"
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

    <el-table :data="activityList" style="width: 100%" class="table-container">
      <el-table-column label="活动id" align="center" prop="aid" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        width="180"
      >
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template> -->
      </el-table-column>
      <el-table-column label="图片链接" align="center" prop="pic" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.pic" style="width: 100px; height: 100px" />
        </template>
      </el-table-column>
      <el-table-column label="地点" align="center" prop="place" />
      <el-table-column label="用户id" align="center" prop="userId" />
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
            @click="handleEdit(scope.$index, scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="table-pagination">
      <el-pagination
        :total="total"
        :page-sizes= "[2, 5, 10]"
        layout="sizes, prev, pager, next, total"
        @next-click= "nextClick"
        @prev-click= "prevClick"
        @size-change= "handleSizeChange"
        @current-change= "currentChange"
      >
      </el-pagination>
    </div>
    <!-- 添加或修改活动管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="图片链接" prop="pic">
          <el-input v-model="form.pic" placeholder="请输入图片链接"> </el-input>
        </el-form-item>
        <el-form-item label="地点" prop="place">
          <el-input v-model="form.place" placeholder="请输入地点" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
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
  getActivity,
  addActivity,
  updateActivity,
  delActivity,
  listActivity,
} from "@/api/activity";
export default {
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        place: null,
        userId: null,
      },
      // 是否显示弹出层
      open: false,
      form: {},
      activityList: [],
      title: "",
      total: 0,
    };
  },
  methods: {
    async getActivityList() {
      // try{
      let res = await listActivity(this.queryParams);
      console.log(res);
      this.activityList = res.data.data.list;
      this.total = res.data.data.total;
      // }catch(err) {
      //     console.log(err);
      // }
    },
    handleEdit(index, row) {
      // console.log(index, row);
      this.reset();
      const aid = row.aid;
      getActivity(aid).then((response) => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改活动管理";
      });
    },
    handleDelete(index, row) {
      console.log(index, row);
      delActivity(row.aid).then((res) => {
        console.log(res);
        this.getActivityList();
      });
    },
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动管理";
    },
    handleExport() {
      window.location.href = "http://localhost:8089/api/activity/export";
    },
    handleQuery() {
      this.getActivityList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    resetForm(refName) {
      if (this.$refs[refName]) {
        this.$refs[refName].resetFields();
      }
    },
    reset() {
      this.form = {
        aid: null,
        title: null,
        content: null,
        startTime: null,
        pic: null,
        place: null,
        userId: null,
      };
      this.resetForm("form");
    },
    submitForm() {
      if (this.form.aid != null) {
        updateActivity(this.form).then((res) => {
          console.log(res);
          this.open = false;
          this.getActivityList();
        });
      } else {
        addActivity(this.form).then((res) => {
          console.log(res);
          this.open = false;
          this.getActivityList();
        });
      }
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    nextClick(val) {
      this.queryParams.pageNum = val;
      this.getActivityList();
    },
    prevClick(val) {
      this.queryParams.pageNum = val;
      this.getActivityList();
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getActivityList();
    },
    currentChange(val) {
      this.queryParams.pageNum = val;
      this.getActivityList();
    },
  },
  mounted() {
    this.getActivityList();
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