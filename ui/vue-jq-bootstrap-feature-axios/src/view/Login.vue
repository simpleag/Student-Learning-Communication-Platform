<template>
  <div class="row" id='div1'>
      <div class="col-md-6 col-md-offset-3 ">
          <div class="col-md-4 col-md-offset-4">
              <h2 class="form-signin-heading">请登录</h2>
          </div>
           <div class="col-xs-2 ">
          </div>
          
          <div class="col-md-4 col-md-offset-4 ">
            <!-- <div class="login-wrap" v-show="showLogin"> -->
            <!-- <p v-show="showTishi">{{tishi}}</p> -->
            <el-input v-model="longinId" placeholder="请输入登录id"></el-input>
            <p></p>
            <el-input type="password" v-model="pwd" placeholder="请输入密码"></el-input>
            <!-- <input type="text" placeholder="请输入登录id" v-model="username">
            <input type="password" placeholder="请输入密码" v-model="password"> -->
            <p></p>
            <el-button type="primary" @click="longin()">登录</el-button>
            <!-- <span ><el-button type="text">忘记密码?</el-button></span> -->
            <!-- </div>                 -->
          </div>
          
          <!-- <el-button type="text">没有账号？马上注册</el-button> -->
          <span ><el-button type="text">没有账号？马上注册</el-button></span>
         
      </div>
        
  </div>
</template>

<script>
import Vue from "vue";
import qs from "qs";
import { formatDate } from "../common/date.js";
//逻辑尚未完善
var longinId = "";
var pwd = "";
export default {
  data() {
    return {
      loginId: this.longinId,
      pwd: this.pwd
    };
  },
  methods: {
    longin() {
      console.log(this.longinId);
      console.log(this.pwd);

      //   window.localStorage.setItem("slcptest","test2");
      //   console.log(window.localStorage.getItem("slcptest"))
      //   window.localStorage.removeItem("slcptest")
      this.ajaxUser();
    },
    initUser(res) {
      var type = "error";
      if (res.data.code == "200") {
        type = "success";
      }
      //存入cookie
      var date = new Date();
      var str = "X_TOKEN" + "=" + escape(res.data.token);
      //小时
      var ms = 7 * 24 * 3600 * 1000;
      date.setTime(date.getTime() + ms);
      str += "; expires=" + date.toGMTString();

      document.cookie = str;
      var str2 = document.cookie;
      console.log(str2);
      window.localStorage.setItem("userId", res.data.user.userId);
      // console.log(window.localStorage.getItem("slcptest"));
      // window.localStorage.removeItem("slcptest");
      this.$notify({
        title: res.data.code,
        message: res.data.message,
        type: type
      });
      this.$router.push({
        name: "Main",
      });
    },
    errorNet(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
        this.$router.push({ name: "Login" });
      },
    ajaxUser: function() {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/user/login",
          qs.stringify({
            userLonginId: this.longinId,
            pwd: this.pwd
          })
        )
        .then(function(res) {
          if (res.data.code == '200') {
            _this.initUser(res);
          } else {
            _this.errorNet(res.data.message)
          }
          
          
        })
        .catch(function(err) {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped>
.login-wrap {
  text-align: center;
}
input {
  display: block;
  width: 250px;
  height: 40px;
  line-height: 40px;
  margin: 10px auto;
  margin-bottom: 10px;
  outline: none;
  border: 1px solid #888;
  padding: 10px;
  box-sizing: border-box;
}
p {
  color: red;
}

span {
  cursor: pointer;
}
span:hover {
  color: #41b883;
}
div1 {
  background-color: #888;
}
</style>
