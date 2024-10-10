<template>
  <div class="banner">
    <div class="banner_img"></div>
    <div class="detail w">
      <div class="top">
        <div class="back" @click="$router.push('/party')">&lt; 返回</div>
        <div class="title_name">{{ activityInfo.title }}</div>
        <div class="btn" v-show="isShow">
          <el-button
            @click="delActivity"><i class="el-icon-delete" ></i>删除</el-button
          >
          <el-button @click="editActivity"><i class="el-icon-edit" ></i>编辑</el-button>
        </div>
      </div>
      <div class="time">
        <span>{{
          activityInfo.startTime ? "发布时间：" + activityInfo.startTime : ""
        }}</span>
      </div>
      <div class="line"></div>
      <div class="place">活动地点：{{ activityInfo.place }}</div>
      <div class="image">
        <img :src="activityInfo.pic" alt="" />
      </div>
      <div class="content">
        <p>
          {{ activityInfo.content }}
        </p>
      </div>
    </div>
    <div v-show="isDisplay" class="w">
      <el-form
        class="create_activity_form"
        ref="form"
        :model="activityInfo"
        label-width="80px"
      >
        <div class="header">修改活动</div>
        <el-form-item label="活动名称">
          <el-input
            v-model="activityInfo.title"
            placeholder="请填写活动名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="活动地点">
          <el-input v-model="activityInfo.place" placeholder="请填写活动地点">
          </el-input>
        </el-form-item>
        <el-form-item label="活动详情">
          <el-input
            type="textarea"
            v-model="activityInfo.content"
            placeholder="请填写活动详情"
          ></el-input>
        </el-form-item>
        <el-form-item label="图片链接">
          <el-input
            type="textarea"
            v-model="activityInfo.pic"
            placeholder="请填写图片链接"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="confirmActivity">立即修改</el-button>
          <el-button @click="clickToClose">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  getActivityByIdAPI,
  delActivityAPI,
  editActivityAPI,
} from "@/api/data";
export default {
  data() {
    return {
      activityInfo: {
        title: "",
        place: "",
        content: "",
        userId: "",
        pic: "",
        aid: "",
        startTime: ""
      },
      isShow: false,
      isDisplay: false,
      form: {
        name: "",
        region: "",
        desc: "",
      },
      pic: "",
    };
  },
  mounted() {
    this.getActivityById();
  },
  methods: {
    async getActivityById() {
      let aid = JSON.parse(this.$route.query.aid);
      console.log(aid);
      let res = await getActivityByIdAPI({ aid });
      if (res.data.statusCode == 200) {
        this.activityInfo = res.data.data;
        if (this.activityInfo.userId == this.$store.state.userData.userId) {
          this.isShow = true;
          console.log(this.activityInfo.aid);
        } else {
          this.isShow = false;
        }
      }
      console.log(res);
      console.log(this.activityInfo);
    },
    async delActivityFun() {
      // if(confirm("确认删除？")) {
      //   let res = await delActivityAPI({ aid: JSON.parse(this.$route.query.aid) });
      //   if (res.data.statusCode == 200) {
      //     this.$message({
      //       message: "删除成功",
      //       type: "success",
      //     });
      //     this.$router.push("/party");
      //   }
      // }
      // if (confirm("确认删除？")) {
      let res = await delActivityAPI({
        aid: JSON.parse(this.$route.query.aid),
      });
      if (res.data.statusCode == 200) {
        this.$message({
          message: "删除成功",
          type: "success",
        });
        this.$router.push("/party");
      } else {
        this.$message({
          message: "删除失败",
          type: "warning",
        });
      }
      // }
    },
    delActivity() {
      this.delActivityFun();
    },
    editActivity() {
      this.isDisplay = true
    },
    confirmActivity() {
      // let param = {
      //   aid : JSON.stringify(this.activityInfo.aid),
      //   place : JSON.stringify(this.activityInfo.place),
      //   content : JSON.stringify(this.activityInfo.content),
      //   title : JSON.stringify(this.activityInfo.title),
      //   startTime: JSON.stringify(this.activityInfo.startTime),
      //   userId: JSON.stringify(this.activityInfo.userId)
      // }
      editActivityAPI(this.activityInfo).then((res) => {
        this.$message({
          message: "修改成功",
          type: "success"
        })
        this.isDisplay = false
        // this.$router.push(0);
      }).catch((err) => {
        this.$message({
          message: "修改失败",
          type: "error"
        })
      })
    },
    clickToClose() {
      this.isDisplay = false
    }
  },
};
</script>

<style lang="less" scpoed>
.banner {
  background-color: rgb(245, 245, 245);
  .banner_img {
    width: 100%;
    height: 400px;
    background: url("@/assets/images/BackGroundImg1.png") center center;
  }
  .detail {
    // display: flex;
    // flex-direction: column;
    // justify-content: space-around;
    background-color: #fff;
    width: 1100px;
    // height: 400px;
    box-shadow: 0 0 28px 0 rgba(34, 142, 161, 0.1);
    box-sizing: border-box;
    padding: 20px 40px;
    // margin-top: -30px auto 0 auto;
    margin: -30px auto 0;
    .top {
      // margin-top: -30px;
      .back {
        float: left;
        font-size: 14px;
        line-height: 32px;
        color: #999;
        margin-bottom: 30px;
        cursor: pointer;
      }
      .title_name {
        padding: 0 70px;
        font-size: 24px;
        line-height: 32px;
        color: #333;
        margin-bottom: 26px;
        text-align: center;
      }
    }
    .time {
      // margin-top: -100px;
      padding-bottom: 34px;
      border-bottom: 1px solid #ebebeb;
      font-size: 14px;
      color: #999;
      line-height: 22px;
      text-align: center;
      margin-bottom: 30px;
    }
    .line {
      width: 1000px;
      height: 30px;
      background: rgb(65, 82, 133);
      margin: 0 auto 20px;
    }
    .place {
      margin-bottom: 30px;
    }
    .image {
      margin: 0 auto 40px;
      img {
        width: 1000px;
      }
    }
    .content {
      width: 1000px;
      font-size: 14px;
      color: rgb(81, 74, 74);
      line-height: 2;
      letter-spacing: 2px;
      margin: 0 auto 50px;
      p {
        word-wrap: break-word;
        word-break: break-all;
      }
    }
  }
}
 .create_activity_form {
    position: absolute;
    top: 600px;
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
</style>