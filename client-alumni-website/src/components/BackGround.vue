<template>
  <div>
    <div class="title">绵城校友网站</div>
    <router-view class="banner"></router-view>
  </div>
</template>

<script>
export default {
  name: 'BackGround',
  data() {
    return {
      bgImgs:[
      {imgUrl:require('../assets/images/BackGroundImg1.png')},
      {imgUrl:require('../assets/images/BackGroundImg2.jpg')},
      {imgUrl:require('../assets/images/BackGroundImg3.jpg')},
      {imgUrl:require('../assets/images/BackGroundImg4.jpg')},
      // {imgUrl:require('../assets/images/BackGroundImg5.jpg')},
      ]
    };
  },
  beforeCreate() {
    this.$nextTick(function() {
    let num =Math.floor(Math.random()*this.bgImgs.length);
    let bg = document.querySelector('body');
    // console.log(bg,bgImgs,num);
    bg.style.background = "url("+this.bgImgs[num].imgUrl+") no-repeat"
    bg.style.backgroundSize = "cover"
    })
  }
};
</script>

<style scoped>
.title {
  margin: 130px 0 auto;
  color: rgba(255, 255, 255, 1);
  font-size: 36px;
}

.banner {
  width: 440px;
  margin: 0 auto;
}
</style>