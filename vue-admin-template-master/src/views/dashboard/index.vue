<template>
  <div class="school">
    <h1>欢迎来到绵阳城市学院校友网站管理系统！</h1>
    <div class="echarts">
      <div id="main" style="width: 1000px; height: 400px"></div>
    </div>
    <div class="detail">
      <div class="logo"><img src="@/assets/logo/logo.png" alt="" /></div>
      <div class="content">
        绵阳城市学院（Mianyang City
        College）是经中华人民共和国教育部批准的一所普通高等本科院校，是四川省建设类专业“双证制”学校、四川省“三全育人”综合改革试点高校。
        绵阳城市学院的前身西南科技大学城市学院是2006年经国务院教育行政部门批准，由西南科技大学和合肥万博社会事业发展集团有限公司联合举办的普通本科高等学校。2012年获得本科学士学位授予资格。
        2021年经国务院教育行政部门批准转设为独立设置的普通本科高等学校，更名为绵阳城市学院。
        截至2023年3月，学校有安州、游仙两个校区，规划占地1200亩，建成及规划建筑面积近40万平方米，教学科研仪器设备约1.06亿元，各类图书资源总量370余万册；设有二级学院8个，开设本专科专业共73个；有在校生20000人。
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { listUser } from "@/api/user";
import { listActivity } from "@/api/activity";
import { listStudent } from "@/api/student";
import { listNotice } from "@/api/notice";
import { listFeedback } from "@/api/feedback";
import { listMajor } from "@/api/major";
import { listClasses } from "@/api/classes";
export default {
  name: "Dashboard",
  data() {
    return {
      userNum: "",
      activityNum: "",
      majorNum: "",
      feedbackNum: "",
      classesNum: "",
      studentNum: "",
      noticeNum: "",
      query: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  methods: {
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById("main"));

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: "数据数量",
        },
        tooltip: {},
        legend: {
          data: ["数量"],
        },
        xAxis: {
          data: ["活动", "班级", "专业", "反馈", "用户", "学生", "公告"],
        },
        yAxis: {},
        series: [
          {
            name: "数量",
            type: "bar",
            data: [
              this.activityNum,
              this.classesNum,
              this.majorNum,
              this.feedbackNum,
              this.userNum,
              this.studentNum,
              this.noticeNum,
            ],
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    getUserNum() {
      listUser(this.query).then((response) => {
        this.userNum = response.data.data.total;
      });
    },
    getActivityNum() {
      listActivity(this.query).then((response) => {
        this.activityNum = response.data.data.total;
      });
    },
    getMajorNum() {
      listMajor(this.query).then((response) => {
        this.majorNum = response.data.data.total;
      });
    },
    getFeedbackNum() {
      listFeedback(this.query).then((response) => {
        this.feedbackNum = response.data.data.total;
      });
    },
    getClassesNum() {
      listClasses(this.query).then((response) => {
        this.classesNum = response.data.data.total;
      });
    },
    getStudentNum() {
      listStudent(this.query).then((response) => {
        this.studentNum = response.data.data.total;
      });
    },
    getNoticeNum() {
      listNotice(this.query).then((response) => {
        this.noticeNum = response.data.data.total;
        this.myEcharts();
      });
    },
    getAll() {
      this.getActivityNum();
      this.getMajorNum();
      this.getFeedbackNum();
      this.getClassesNum();
      this.getStudentNum();
      this.getUserNum();
      this.getNoticeNum();
    },
  },
  mounted() {
    this.getAll();
  },
};
</script>

<style lang="scss" scoped>
h1 {
  height: 100px;
  text-align: center;
  line-height: 100px;
  font-size: 50px;
}
.detail {
  // height: 500px;
  display: flex;
  // justify-content: space-evenly;
  justify-content: center;
  // justify-content: space-around;
  width: 1000px;
  margin: 0 auto;
  .logo {
    // text-align: center;
    width: 350px;
    img {
      height: 50%;
    }
  }
  .content {
    font-size: 20px;
  }
}
.echarts {
  margin-left:200px;
  margin-bottom: 40px;
}
</style>
