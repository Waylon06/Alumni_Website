<template>
  <div class="banner">
    <div class="banner_img"></div>
    <!-- <div class="banner_text"></div> -->
    <div class="banner_content w">
      <div class="title">
        <div class="list">通知列表</div>
        <dl>
          <dd :class="item.nid == active ? 'selected' : ''" v-for="item in noticeInfo" :key="item.nid" @click="changeItem(item)">{{ item.title }}</dd>
        </dl>
      </div>
      <div class="detail">
        <div class="title_name">{{title == '' ? '通知信息' : title}}</div>
        <div class="content">{{content}}</div>
        <div class="time">{{createDate == '' ? '' : '发布时间：'+createDate}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getNoticeInfoAPI } from "@/api/data";
export default {
  data() {
    return {
      noticeInfo: [],
      active: '',
      title: '',
      content: '',
      createDate: ''
    };
  },
  methods: {
    async getNoticeInfo() {
      let res = await getNoticeInfoAPI();
      if (res.data.statusCode == 200) {
        this.noticeInfo = res.data.data;
        console.log(this.noticeInfo);
      }
    },
    changeItem(item) {
      this.active = item.nid;
      this.title = item.title;
      this.content = item.content;
      this.createDate = item.createDate
    }
  },
  mounted() {
    this.getNoticeInfo();
  },
};
</script>

<style lang="less" scoped>
.banner {
  background-color: rgb(245, 245, 245);
  .banner_img {
    width: 100%;
    height: 400px;
    background: url("@/assets/images/BackGroundImg1.png") center center;
  }
  .banner_content {
    display: flex;
    justify-content: space-between;
    .title {
      .list {
        width: 165px;
        height: 52px;
        padding-top: 24px;
        padding-left: 30px;
        margin-top: -30px;
        background: #fff;
        font-size: 24px;
        font-weight: 500;
        color: #333;
        line-height: 33px;
        text-align: left;
        padding-right: 15px;
      }
      dl {
        padding-left: 0;
        padding-right: 0;
        padding-bottom: 20px;
        width: 210px;
        background: #fff;
        padding-left: 0;
        padding-right: 0;
        dd {
          display: flex;
          -ms-flex-align: center;
          align-items: center;
          box-sizing: border-box;
          min-width: 189px;
          min-height: 50px;
          font-family: PingFangSC-Regular;
          overflow: hidden;
          margin-bottom: 4px;
          padding: 13px 0 13px 30px;
          font-size: 16px;
        }
      }
      .selected {
        background: rgb(65, 82, 133);
        background-repeat: no-repeat;
        position: relative;
        width: 220px;
        left: 20px;
        padding-left: 10px;
        color: white;
      }
    }
  }
  .detail {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    background-color: #fff;
    width: 900px;
    height: 400px;
    box-shadow: 0 0 28px 0 rgba(34, 142, 161, 0.1);
    box-sizing: border-box;
    padding: 40px 20px;
    margin-top: -30px;
    .title_name {
      margin-bottom: 30px;
      margin-top: 0;
      height: 26px;
      font-size: 30px;
      line-height: 26px;
      color: #333;
      padding-right: 15px;
      padding-left: 15px;
      font-weight: 700;
    }
    .content {
      font-size: 22px;
    }
    .time {
      color: gray;
      font-size: 15px;
    }
  }
}
</style>