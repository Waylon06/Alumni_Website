<template>
  <div class="footer_box">
    <div class="w">
      <div class="logo">
        <img src="@/assets/images/logo2.png" alt="" />
      </div>
      <div class="contact">
        <div class="contact_title">与我们联系:</div>
        <ul>
          <li><img src="@/assets/images/contact/weibo.png" alt="" /></li>
          <li><img src="@/assets/images/contact/wechat.png" alt="" /></li>
          <li><img src="@/assets/images/contact/douyin.png" alt="" /></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style lang="less" scoped>
.footer_box {
  // height: 150px;
  background-color: rgb(65, 82, 133);
  .w {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .logo {
    width: 600px;
    border-right: 1px solid hsla(0, 0%, 100%, 0.4);
    img {
      width: 30%;
      height: 50%;
    }
  }
  .contact {
    width: 600px;
    .contact_title {
      color: #fff;
      font-size: 18px;
      font-weight: 500;
      line-height: 22px;
      letter-spacing: 1px;
      padding-left: 0;
      padding-right: 0;
    }
    ul {
      padding-left: 200px;
      width: 200px;
      display: flex;
      justify-content: space-around;
      li {
        img {
          height: 42px;
          width: 42px;
        }
      }
    }
  }
}
</style>