<template>
  <div class="main-box">
    <!-- Swiper轮播图 -->
    <div class="swiper-container clearfix">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="@/assets/images/slide/Slide2.jpg" />
        </div>
        <div class="swiper-slide">
          <img src="@/assets/images/slide/Slide1.jpg" />
        </div>
        <div class="swiper-slide">
          <img src="@/assets/images/slide/Slide4.jpg" />
        </div>
      </div>
      <!-- Add Pagination -->
      <div class="swiper-pagination"></div>
    </div>
    <div class="detail_name w">
      学校简介 <span style="color: rgb(65, 82, 133)">></span>
    </div>
    <div class="detail w">
      <div class="logo"><img src="@/assets/images/logo.webp" alt="" /></div>
      <div class="content">
        绵阳城市学院（Mianyang City
        College）是经中华人民共和国教育部批准的一所普通高等本科院校，是四川省建设类专业“双证制”学校、四川省“三全育人”综合改革试点高校。
        绵阳城市学院的前身西南科技大学城市学院是2006年经国务院教育行政部门批准，由西南科技大学和合肥万博社会事业发展集团有限公司联合举办的普通本科高等学校。2012年获得本科学士学位授予资格。
        2021年经国务院教育行政部门批准转设为独立设置的普通本科高等学校，更名为绵阳城市学院。
        截至2023年3月，学校有安州、游仙两个校区，规划占地1200亩，建成及规划建筑面积近40万平方米，教学科研仪器设备约1.06亿元，各类图书资源总量370余万册；设有二级学院8个，开设本专科专业共73个；有在校生20000人。
      </div>
    </div>
    <el-button
      type="primary"
      icon="el-icon-edit"
      class="fbtn"
      @click="createFeedback"
      >反馈</el-button
    >
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input
            type="textarea"
            autosize
            v-model="form.content"
            :min-height="192"
            placeholder="请输入内容"
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
import Swiper from "swiper";
import { BarrageRenderer, BarrageOptions } from "fly-barrage";
import { addFeedbackAPI } from "@/api/data";
export default {
  components: {
    Swiper,
  },
  data() {
    return {
      open: false,
      title: "提交反馈",
      form: {
        title: "",
        content: "",
      },
    };
  },
  mounted() {
    new Swiper(".swiper-container", {
      speed: 3000,
      loop: true,
      spaceBetween: 30,
      centeredSlides: true,
      autoplay: {
        delay: 2500,
        disableOnInteraction: false,
      },
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
      // navigation: {
      //   nextEl: ".swiper-button-next",
      //   prevEl: ".swiper-button-prev",
      // },
    });
  },
  beforeCreate() {
    let bg = document.querySelector("body");
    bg.style.background = "";
  },
  methods: {
    createFeedback() {
      this.open = true;
    },
    async submitForm() {
       let res = await addFeedbackAPI(this.form);
       if(res.data.statusCode == 200) {
        this.$message({
          message: "提交成功",
          type: "success",
        });
        this.open = false;
      }
    },
    cancel() {
      this.open = false;
    },
  },
};
</script>

<style lang="less" scoped>
.main-box {
  // height: 750px;
  .swiper-container {
    height: 550px;
    width: 100%;
    .swiper-wrapper img {
      height: 100%;
      width: 100%;
      // 保持原有尺寸比例，使图片的宽度完整的显示，高度自动缩放。
      object-fit: contain;
      // 保持原有尺寸比例。高度铺满容器，宽度等比缩放，超出部分被剪掉。
      // object-fit: cover;
      margin-bottom: 20px;
    }
  }
}
.detail_name {
  font-size: 28px;
  font-weight: 500;
  color: #333;
  text-align: left;
  margin-top: 48px;
  margin-bottom: 40px;
}
.detail {
  display: flex;
  justify-content: space-between;
  img {
    height: 50%;
  }
  .content {
    font-size: 20px;
    text-align: left;
  }
}
.fbtn {
  position: fixed;
  top: 800px;
  /* left: calc(50% + 1190px/2 + 20px); */
  left: 60%;
  margin-left: calc(1190px / 2 + 20px);
}
</style>>
