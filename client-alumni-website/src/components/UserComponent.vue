<template>
  <div class="bg">
    <div class="w">
      <!-- <el-input placeholder="请输入内容" v-model="name" class="input-with-select">
    <el-button slot="append" icon="el-icon-search" @click="searchStudent"></el-button>
  </el-input>  -->
      <div class="panel">
        <div class="avatar">
          <!-- <img
            src="https://avatars2.githubusercontent.com/u/22117876?s=460&u=2431323208334222479&v=4"
            alt="avatar"
          /> -->
          <img
            :src="
              userData.avatar
                ? userData.avatar
                : 'https://waylon-personal-bucket.oss-cn-chengdu.aliyuncs.com/5590a805b4b14249a6bed711b5985f80.jpg'
            "
            alt="avatar"
          />
          <el-button
            type="primary"
            class="user_btn"
            round
            @click="clickToChange"
            >修改用户信息</el-button
          >
        </div>
        <div class="user">
          <div
            class="user_info"
            v-show="studentInfo.userId == 0 ? false : true"
          >
            <div class="title">个人信息</div>
            <div class="user_name">
              姓名：<span>{{ studentInfo.name }}</span>
            </div>
            <div class="class">
              班级：<span>{{ studentInfo.classId }}</span>
            </div>
            <div class="grade">
              年级: <span>{{ studentInfo.grade }}</span>
            </div>
            <div class="major">
              专业：<span>{{ studentInfo.majorId }}</span>
            </div>
            <div class="sex">
              性别：<span>{{ studentInfo.sex == 0 ? "男" : "女" }}</span>
            </div>
          </div>
          <div class="noInfo" v-show="studentInfo.userId == 0 ? true : false">
            <div class="msg">暂未绑定学生信息</div>
            <div>
              <el-button
                type="primary"
                class="student_btn"
                round
                @click="clickToBind"
                >点击绑定学生信息</el-button
              >
            </div>
          </div>
        </div>
      </div>
      <div v-show="isShow" class="create_user_form">
        <el-form label-position="top" label-width="80px" :model="user">
          <el-form-item label="邮箱">
            <el-input v-model="user.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              type="password"
              v-model="user.password"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="上传头像">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8089/api/uploadImg/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              :on-error="handleError"
            >
              <img v-if="user.avatar" :src="user.avatar" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="changeUserInfo">提交</el-button>
            <el-button @click="clickToClose">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div v-show="isDisplay" class="create_student_form">
        <el-form label-position="right" label-width="80px" :model="student">
          <el-form-item label="姓名">
            <el-input v-model="student.name"></el-input>
          </el-form-item>
          <el-form-item label="班级">
            <el-select
              v-model="student.classId"
              clearable
              placeholder="请选择"
              size="medium"
            >
              <el-option
                v-for="item in classOptions"
                :key="item.cid"
                :label="item.name"
                :value="item.cid"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年级">
            <el-input v-model="student.grade"></el-input>
          </el-form-item>
          <el-form-item label="专业">
            <el-select v-model="student.majorId" clearable placeholder="请选择">
              <el-option
                v-for="item in majorOptions"
                :key="item.mid"
                :label="item.name"
                :value="item.mid"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="student.sex" clearable placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addStudentInfo">关联</el-button>
            <el-button @click="clickToClose">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {
  changeUserInfoAPI,
  addStudentAPI,
  getStudentInfoAPI,
  getClassInfoAPI,
  getMajorInfoAPI,
  getStudentByUidAPI,
} from "@/api/data";
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      studentInfo: {
        userId: 0,
      },
      name: null,
      studentList: [],
      classOptions: [],
      majorOptions: [],
      isShow: false,
      isDisplay: false,
      user: {
        password: "",
        email: this.$store.state.userData.email,
        avatar: '',
        userId: this.$store.state.userData.userId,
        admin: this.$store.state.userData.admin,
        usrename: this.$store.state.userData.username,
      },
      student: {
        name: "",
        classId: "",
        grade: "",
        majorId: "",
        sex: "",
        userId: this.$store.state.userData.userId,
      },
      options: [
        {
          value: "0",
          label: "男",
        },
        {
          value: "1",
          label: "女",
        },
      ],
    };
  },
  methods: {
    clickToChange() {
      this.isShow = !this.isShow;
    },
    clickToBind() {
      this.isDisplay = !this.isDisplay;
    },
    clickToClose() {
      this.isDisplay = false;
      this.isShow = false;
    },
    async changeUserInfo() {
      console.log(this.user);
      let res = await changeUserInfoAPI(this.user);
      console.log(res);
      if (res.data.statusCode == 200) {
        this.$notify({
          title: "成功",
          message: "修改成功，请重新登录",
          type: "success",
        });
        sessionStorage.removeItem("userData");
        this.$router.push({ path: "/login" });
      }
    },
    async addStudentInfo() {
      let res = await addStudentAPI(this.student);
      console.log(res);
      if (res.data.statusCode == 200) {
        this.$notify({
          title: "成功",
          message: "绑定成功",
          type: "success",
        });
        this.isDisplay = false;
        this.$router.go(0);
      }
    },
    async getStudentInfo() {
      let res = await getStudentByUidAPI({
        userId: this.$store.state.userData.userId,
      });
      console.log(res);
      if (res.data.statusCode == 200 && res.data.data != null) {
        this.studentInfo = res.data.data;
      }
    },
    async getClassInfo() {
      let res = await getClassInfoAPI();
      console.log(res.data.data);
      if (res.data.statusCode == 200) {
        this.classOptions = res.data.data;
        if (this.studentInfo.userId != 0) {
          this.studentInfo.classId = this.classOptions.find(
            (classOption) => classOption.cid === this.studentInfo.classId
          ).name;
        }
      }
    },
    async getMajorInfo() {
      let res = await getMajorInfoAPI();
      console.log(res.data.data);
      if (res.data.statusCode == 200) {
        this.majorOptions = res.data.data;
        if (this.studentInfo.userId != 0) {
          this.studentInfo.majorId = this.majorOptions.find(
            (majorOption) => majorOption.mid === this.studentInfo.majorId
          ).name;
        }
      }
    },
    async searchStudent() {
      if(this.name == null && this.name == '') {
        this.$message({
          message: "请输入姓名",
          type: "warning",
        });
      }
      else {let res = await getStudentInfoAPI({
        name: this.name,
      })
      if(res.data.statusCode == 200) {
        console.log(res.data.data);
        this.studentList = res.data.data;
      }}
    },
    handleAvatarSuccess(res, file) {
      // console.log(URL.createObjectURL(file.raw));
      this.user.avatar = res.data;
      // console.log(this.user.avatar);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt1M = file.size / 1024 / 1024 < 1;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt1M) {
        this.$message.error("上传头像图片大小不能超过 1MB!");
      }
      return isJPG && isLt1M;
    },
    handleError(err) {
      console.log(err);

    }
  },
  computed: {
    ...mapState(["isLogined", "userData"]),
  },
  mounted() {
    if (this.$store.state.userData.uid != "") {
      this.getStudentInfo();
    }
    this.getClassInfo();
    this.getMajorInfo();
  },
};
</script>

<style lang="less" scoped>
.bg {
  width: 100%;
  height: 100vh;
  background: url("@/assets/images/BackGroundImg1.png");
  background-size: cover;
  position: relative;
}
.panel {
  display: flex;
  justify-content: space-around;
  // position: absolute;
  // right: 400px;
  // top: 106px;
  padding-top: 100px;
  .avatar {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 600px;
    img {
      width: 300px;
      height: 300px;
      border-radius: 300px;
    }
    .user_btn {
      width: 350px;
    }
  }
  .user {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Noto Sans",
      Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
    text-align: left;
    border-radius: 10px;
    height: 400px;
    margin: auto 0;
    width: 600px;
    color: white;
    border: 1px solid rgba(255, 255, 255, 0.5);
    .noInfo {
      padding-top: 130px;
      .msg {
        text-align: center;
        font-size: 1.5em;
      }
      .student_btn {
        display: block;
        margin: 30px auto !important;
      }
    }
    .user_info {
      .title {
        margin-bottom: 16px;
        // font-size: 30px;
        font-size: 1.25em;
        font-weight: 600;
        margin: 30px 0 40px 20px;
      }
      div {
        margin: 16px 0 16px 66px;
      }
    }
  }
}
.create_user_form {
  height: 700px;
  width: 600px;
  position: absolute;
  top: 50px;
  left: 0;
  right: 0;
  padding: 30px 20px 0 0;
  background-color: #fff;
  border-radius: 20px;
  margin: 0 auto;
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
}
.create_student_form {
  position: absolute;
  top: 100px;
  left: 0;
  right: 0;
  height: 400px;
  width: 600px;
  padding: 30px 40px 0 0;
  background-color: #fff;
  border-radius: 20px;
  margin: 0 auto;
}
.el-select {
  width: 100%;
}
</style>