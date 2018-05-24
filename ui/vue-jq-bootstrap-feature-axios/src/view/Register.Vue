<template>
  <div class="row" id='div1'>
     
      <div class="col-md-6 col-md-offset-3 ">
          <div class="col-md-4 col-md-offset-4">
              <h2 class="form-signin-heading">注册</h2>
              
                  <el-input placeholder="用户名" v-model="userName">
                    <template slot="prepend" >用户名</template>
                  </el-input>
                  <P></p>
                   <el-input placeholder="登录ID" v-model="userLoginId">
                    <template slot="prepend" >登录ID</template>
                  </el-input>
                  <P></p>
                   <el-input  type='password' placeholder="新密码" v-model="pwd">
                    <template slot="prepend" style="width: 20%">新密码</template>
                  </el-input>
                  <P></p>
                  <el-input  type='password' placeholder="新密码" v-model="pwd2">
                    <template slot="prepend" style="width: 20%">新密码</template>
                  </el-input>
                    <P></p>
                    <P></p>
                  <el-button type="primary" @click="register()">注册</el-button>
          </div>
           <div class="col-xs-2 ">
          </div>
          
          <!-- <div class="col-md-4 col-md-offset-4 " id='inputBox'>
            <div class="form-inline">  
               <input type="text" class="form-control" placeholder="请输入手机号码" />  
               <button class="btn btn-primary">验证码</button>  
           </div>  
            <div class="login-wrap" v-show="showLogin">
                <p v-show="showTishi">{{tishi}}</p>
                
                
                <button v-on:click="login" button type="button" class="btn btn-primary">注册</button>
                <span v-on:click="ToRegister">忘记密码？</span>
            </div>  -->
                           
          </div>
          
          
          <span ><el-button type="text" @click="jumpToLogin()">已有帐号？请登录</el-button></span>
         
      </div>
        
  </div>
</template>

<script>
//逻辑尚未完善
import Vue from "vue";
import qs from "qs";
import { formatDate } from "../common/date.js";

var userName='';
var pwd='';
var pwd2='';
var userLoginId='';
  export default{
        data(){
            return{
                showLogin: true,
                showRegister: false,
                showTishi: false,
                tishi: '',
                userName: this.userName,
                pwd : this.pwd,
                pwd2: this.pwd2,
                userLoginId: this.userLoginId
            }
        },
        methods: {
            register(){
                var _this = this;
                
                _this.ajaxRegister();
            },
            ajaxRegister(){
                 var _this = this;
                axios
                    .post(
                    "http://localhost:5555/sclp/user/register",
                    qs.stringify({
                        userName: _this.userName,
                        pwd: _this.pwd,
                        pwd2: _this.pwd2,
                        userLoginId: _this.userLoginId
                    })
                    )
                    .then(function(res) {
                    if (res.data.code == '200') {
                        // _this.initUser(res);
                        _this.errorNet('成功','success',res.data.message)
                        _this.jumpToLogin();
                    } else {
                        _this.errorNet('出错','error',res.data.message)
                    }
                    
                    
                    })
                    .catch(function(err) {
                    console.log(err);
                    });
            },
            errorNet(title,type,message){
                    console.log("error")
                    this.$notify({
                    title: title,
                    message: message,
                    type: type
                    });
                    
                },
            jumpToLogin(){
                this.$router.push({ name: "Login" });
            }
        }
    }
</script>

<style scoped>
 .login-wrap{text-align:center;}
    input{display:block; width:250px; height:40px; line-height:40px; margin:10px auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
    p{color:red;}
    
    span{cursor:pointer;}
    span:hover{color:#41b883;}
    div1{
        background-color: #888;
    }
</style>
