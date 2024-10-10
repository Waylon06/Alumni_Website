<template>
  <div>
    <div class="user">
      <i class="icon-u"></i
      ><input type="text" id="user" placeholder="账号" v-model="username" />
    </div>
    <div class="password">
      <i class="icon-p"></i
      ><input
        type="password"
        id="password"
        placeholder="密码"
        v-model="password"
      />
    </div>
    <div class="email">
      <i class="icon-e"></i
      ><input type="email" id="email" placeholder="邮箱" v-model="email" />
    </div>
    <button class="register" @click="clickToRegister">注册</button>
    <div class="more">
      <router-link to="/login" class="login">已注册？去登录</router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { clickToSignUpAPI } from "@/api/data";
export default {
  name: "RegisterPage",
  data() {
    return {
      username: "",
      password: "",
      email: "",
    };
  },
  methods: {
    changePage() {
      this.$router.push({});
    },
    async clickToRegister() {
      if (
        this.username == "" ||
        this.password == "" ||
        this.email == ""
      ) {
         this.$message({
          message: '账号或者密码以及邮箱不能为空！',
          type: 'warning'
        });
      } else {
        let userData = await clickToSignUpAPI({
          username: this.username,
          password: this.password,
          email: this.email,
          admin: 0
        });
        console.log(userData);
        if (userData.data.statusCode === 200) {
          // this.changeUserInfo(userData.data);
          // this.$router.push("/");
          this.$message({
            message: '注册成功，请登录！',
            type: 'success'
          });
          this.$router.push("/login");
        }
      }
    },
  },
  computed: {
    // 方式一
    // username() {
    //   return this.$store.state.username;
    // }

    // 方式二
    // ...mapState(["username"]),
  },
};
</script>

<style scoped>
.user,
.password,
.email {
  height: 60px;
  width: 440px;
  border-radius: 30px;
  opacity: 0.25;
  margin: 0 auto 24px;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(255, 255, 255, 1);
}

#user,
#password,
#email {
  width: 370px;
  height: 60px;
  opacity: 0.25;
  margin-left: 70px;
  border-top-right-radius: 30px;
  border-bottom-right-radius: 30px;
  border: none;
  outline: none;
}

.user {
  margin-top: 28px;
  position: relative;
}

.icon-u::before {
  font-family: "icomoon";
  content: "\e971";
  position: absolute;
  top: 18px;
  left: 35px;
  font-size: 20px;
}

.password {
  position: relative;
}

.icon-p::before {
  font-family: "icomoon";
  content: "\e98f";
  position: absolute;
  top: 18px;
  left: 35px;
  font-size: 20px;
}

.email {
  position: relative;
}

.icon-e::before {
  font-family: "icomoon";
  content: "\ea86";
  position: absolute;
  top: 18px;
  left: 35px;
  font-size: 20px;
}

input::-webkit-input-placeholder {
  /* color: rgba(211, 211, 211, 1); */
  font-size: 16px;
  font-family: SourceHanSansSC-regular;
  opacity: 1;
}

.register {
  height: 60px;
  width: 440px;
  border-radius: 30px;
  margin-bottom: 30px;
  font-size: 16px;
  background-color: rgba(58, 98, 215, 1);
  color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(58, 98, 215, 1);
}

.more {
  font-size: 18px;
  text-align: right;
}

.login {
  color: rgba(211, 211, 211, 1);
  cursor: pointer;
}
</style>