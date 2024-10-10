<template>
  <div>
    <div class="banner_img">
      <div class="search">
        <div class="search-box">
          <div class="ipt-box">
            <!-- <select name="search-name" class="select-box">
              <option value="0">全部</option>
              <option value="1">外国文学</option>
              <option value="2">历史传记</option>
              <option value="3">华文小说</option>
            </select> -->
            <input
              type="text"
              class="ipt"
              placeholder="请输入搜索的名字"
              v-model="name"
            />
            <button class="btn" @click="getStudentList">
              <i icon="el-icon-search"></i>
              <span>搜索</span>
            </button>
          </div>
        </div>
      </div>
    </div>
    <ul class="student_list">
      <div class="w">
        <li class="student" v-for="(item, index) in studentList" :key="index">
          <div class="avatar">
            <img
              :src="
                findUserAvatar(item.userId) == false
                  ? '../assets/images/BackGroundImg1.png'
                  : findUserAvatar(item.userId)
              "
              alt=""
            />
          </div>
          <div class="info">
            <div>姓名：{{ item.name }}</div>
            <div>班级：{{ findClassName(item.classId) }}</div>
            <div>专业：{{ findMajorName(item.majorId) }}</div>
            <div>性别：{{ item.sex == 0 ? "男" : "女" }}</div>
            <div>邮箱：{{ finUserEmail(item.userId) }}</div>
          </div>
        </li>
      </div>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  getStudentInfoAPI,
  getClassInfoAPI,
  getMajorInfoAPI,
  getUserInfoAPI,
} from "../api/data";
export default {
  data() {
    return {
      studentList: [],
      name: null,
      classOptions: [],
      majorOptions: [],
      userList: [],
    };
  },
  created() {
    // this.getStudentList();
    this.getClassInfo();
    this.getMajorInfo();
    this.getUserInfo();
  },
  methods: {
    getStudentList() {
      if (this.name == null || this.name == "") {
        this.$message.error("请输入校友的名字");
      } else {
        getStudentInfoAPI({
          name: this.name,
        }).then((res) => {
          console.log(res.data.data);
          this.studentList = res.data.data;
        });
      }
    },
    async getClassInfo() {
      let res = await getClassInfoAPI();
      console.log(res.data.data);
      if (res.data.statusCode == 200) {
        this.classOptions = res.data.data;
        // this.studentInfo.classId = this.classOptions.find(
        //   (classOption) => classOption.cid === this.studentInfo.classId
        // ).name;
      }
    },
    async getMajorInfo() {
      let res = await getMajorInfoAPI();
      console.log(res.data.data);
      if (res.data.statusCode == 200) {
        this.majorOptions = res.data.data;
        // this.studentInfo.majorId = this.majorOptions.find(
        //   (majorOption) => majorOption.mid === this.studentInfo.majorId
        // ).name;
      }
    },
    async getUserInfo() {
      let res = await getUserInfoAPI();
      if (res.data.statusCode == 200) {
        this.userList = res.data.data;
      }
    },
    findClassName(classId) {
      if (this.classOptions.length == 0) {
        return null;
      }
      if (
        this.classOptions.find((classOption) => classOption.cid === classId) ===
        undefined
      ) {
        return "暂无此人班级信息";
      }
      return this.classOptions.find(
        (classOption) => classOption.cid === classId
      ).name;
    },
    findMajorName(majorId) {
      if (this.majorOptions.length == 0) {
        return null;
      }
      if (
        this.majorOptions.find((majorOption) => majorOption.mid === majorId) ===
        undefined
      ) {
        return "暂无此人专业信息";
      }
      return this.majorOptions.find(
        (majorOption) => majorOption.mid === majorId
      ).name;
    },
    findUserAvatar(userId) {
      if (this.userList.length == 0) {
        return null;
      }
      if (this.userList.find((user) => user.userId === userId) === undefined) {
        return require("../assets/images/BackGroundImg1.png");
      }
      return this.userList.find((user) => user.userId === userId).avatar;
    },
    finUserEmail(userId) {
      if (this.userList.length == 0) {
        return null;
      }
      if (this.userList.find((user) => user.userId === userId) === undefined) {
        return "暂无此人邮箱信息";
      }
      return this.userList.find((user) => user.userId === userId).email;
    },
  },
};
</script>

<style lang="less" scoped>
.banner_img {
  width: 100%;
  height: 400px;
  background: url("@/assets/images/BackGroundImg1.png") center center;
}
.student_list {
  height: 200px;
  padding-top: 20px;
  // display: flex;
  // justify-content: space-between;
  background-color: rgb(245, 245, 245);
  //  .w {
  //   height: 200px;
  //  }
  .student {
    float: left;
    // margin-right: 10px;
    // background-color: blue;
    box-shadow: #415285;
    width: 385px;
    height: 150px;
    // border:1px solid #415285;
    background-color: #fff;
    // display: flex;
    // justify-content: space-between;
    position: relative;
    margin-right: 5px;
    margin-top: 20px;
    .avatar {
      position: absolute;
      top: 12%;
      left: 10px;
      width: 120px;
      // height: 100px;
      img {
        // width: 100%;
        width: 120px;
        height: 120px;
        border-radius: 60px;
      }
    }
    .info {
      position: absolute;
      top: 20%;
      right: 0;
      width: 250px;
      div {
        text-align: left;
      }
    }
  }
}

.search {
  position: absolute;
  top: 30%;
  right: 0;
  left: 0;
  padding: 40px 80px 0 80px;
  z-index: 999;
  .search-box {
    width: 803px;
    height: 162px;
    font-size: 0;
    box-sizing: border-box;
    border-top: 6px solid #415285;
    background: rgba(255, 255, 255, 0.7);
    border-radius: 0px 6px 6px 6px;
    padding: 35px 50px;
    margin: 0 auto;
    .ipt-box {
      width: 100%;
      height: 50px;
      border: 1px solid rgba(65, 82, 133, 0.5);
      border-radius: 6px;
      .select-box {
        border: none;
        width: 128px;
        height: 50px;
        background: rgba(65, 82, 133, 0.05);
        border-right: 1px solid rgba(65, 82, 133, 0.5);
        float: left;
        text-align: center;
        font-size: 16px;
      }
      .ipt {
        line-height: 50px;
        height: 50px;
        padding: 0 20px;
        font-size: 16px;
        border-radius: 6px;
        float: left;
        border: none;
        // width: 425px;
        width: 553px;
        background: rgba(255, 255, 255, 0.7);
      }
      .btn {
        background: #3d82f2;
        border: none;
        outline: none;
        width: 110px;
        height: 50px;
        border-radius: 0px 6px 6px 0px;
        font-size: 18px;
        color: #ffffff;
        float: right;
      }
    }
  }
}
</style>