<template>
  <div class="banner">
    <div class="banner_img"></div>
    <ul class="activity_list w" v-show="activityInfo.length">
      <el-button
        type="primary"
        class="activity activity_btn"
        round
        @click="clickToCreate()"
        >创建活动</el-button
      >
      <li
        class="activity"
        v-for="item in activityInfo"
        :key="item.aid"
        @click="clickToDetail(item.aid)"
      >
        <div>
          <a href="#">
            <img :src="item.pic" alt="" />
          </a>
        </div>
        <div class="start_time">{{ formatDateChinese(item.startTime) }}</div>
        <p class="title">{{ item.title }}</p>
      </li>
    </ul>
    <el-empty description="暂无任何活动信息" v-show="!activityInfo.length"></el-empty>
    <div v-show="isShow" class="w">
      <el-form
        class="create_activity_form"
        ref="form"
        :model="form"
        label-width="80px"
      >
        <div class="header">创建活动</div>
        <el-form-item label="活动名称">
          <el-input v-model="form.name" placeholder="请填写活动名称"></el-input>
        </el-form-item>
        <el-form-item label="活动地点">
          <el-input v-model="form.region" placeholder="请填写活动地点">
          </el-input>
        </el-form-item>
        <el-form-item label="活动详情">
          <el-input
            type="textarea"
            v-model="form.desc"
            placeholder="请填写活动详情"
          ></el-input>
        </el-form-item>
        <el-form-item label="上传图片">
          <el-upload
            class="upload-demo"
            drag
            action="http://localhost:8089/api/uploadImg/upload"
            :on-success="handlePicSuccess"
            :on-error="imgError"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addActivity">立即创建</el-button>
          <el-button @click="clickToClose">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  getActivityInfoAPI,
  getPicUrlAPI,
  getActivityByIdAPI,
  addActivityAPI,
} from "@/api/data";
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      activityInfo: [],
      isShow: false,
      form: {
        name: "",
        region: "",
        desc: "",
      },
      pic: "",
    };
  },
  methods: {
    async getActivityInfo() {
      let res = await getActivityInfoAPI({pageNum:1, pageSize:100});
      if (res.data.statusCode == 200) {
        this.activityInfo = res.data.data;
        console.log(this.activityInfo);
      }
    },
    formatDateChinese(dateString) {
      // 解析输入的日期字符串为Date对象
      const date = new Date(dateString);

      // 获取年、月、日并格式化为中文格式
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);

      // 组合成中文格式的日期字符串
      return `${year}年${month}月${day}日`;
    },
    clickToDetail(aid) {
      // console.log(aid);
      this.$router
        .push({ path: "/activity", query: { aid: JSON.stringify(aid) } })
        .catch((err) => {
          console.log(err);
        });
    },
    clickToClose() {
      this.isShow = !this.isShow;
    },
    clickToCreate() {
    if(this.userData.userId == '' || JSON.parse(sessionStorage.getItem("userData"))== null) {
      this.$notify({
          title: '未登录账号',
          message: '请先登录账号',
          position: 'bottom-left',
          type: 'warning'
        });
    }else {
      this.isShow = !this.isShow;
    }
    },
    handlePicSuccess(res) {
      // console.log(res);
      this.pic = res.data;
      console.log(this.pic);
    },
    imgError(res) {
      console.log(res);
      console.log("图片上传失败");
      this.$message()
    },
    async addActivity() {
      let params = {
        title: this.form.name,
        pic: this.pic,
        content: this.form.desc,
        place: this.form.region,
        userId: JSON.parse(sessionStorage.getItem("userData")).userId,
      };
      let res = await addActivityAPI(params);
      if(res.data.statusCode == 200) {
        this.$message({
          message: "创建成功",
          type: "success",
        });
        this.clickToClose();
        this.getActivityInfo();
      }
    },
  },
  mounted() {
    this.getActivityInfo();
  },
  computed: {
    ...mapState(["isLogined", "userData"]),
  }
};
</script>

<style lang="less" scoped>
.banner {
  position: relative;
  background-color: rgb(245, 245, 245);
  .banner_img {
    width: 100%;
    height: 400px;
    background: url("@/assets/images/BackGroundImg1.png") center center;
  }
  .activity_list {
    .activity {
      float: left;
      height: 190px;
      width: 290px;
      margin: 30px 5px 10px 5px;
      position: relative;
      color: white;
      img {
        height: 190px;
        width: 290px;
      }
      .start_time {
        position: absolute;
        width: 100px;
        height: 20px;
        line-height: 20px;
        text-align: center;
        // background: #d7af73;
        background: rgb(65, 82, 133);
        font-size: 12px;
        font-weight: 400;
        top: 16px;
        left: 16px;
      }
      .title {
        position: absolute;
        bottom: 5px;
        left: 20px;
        font-weight: 500;
        text-shadow: 0 1px 1px #000;
        line-height: 22px;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      // margin-bottom: 100px;
    }
    .activity_btn {
      background: rgb(65, 82, 133);
      font-size: 40px;
    }
  }
  .create_activity_form {
    position: absolute;
    top: 20px;
    height: 600px;
    width: 600px;
    left: 0;
    right: 0;
    margin: 0 auto;
    padding: 30px 50px 0 40px;
    background-color: #fff;
    border-radius: 20px;
    .header {
      font-size: 20px;
      font-weight: 500;
      text-align: center;
      margin-bottom: 20px;
    }
  }
}
</style>