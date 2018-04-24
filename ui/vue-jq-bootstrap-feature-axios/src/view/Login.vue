<template>
  <div class="row" id='div1'>
      <div class="col-md-6 col-md-offset-3 ">
          <div class="col-md-4 col-md-offset-4">
              <h2 class="form-signin-heading">请登录</h2>
          </div>
           <div class="col-xs-2 ">
          </div>
          
          <div class="col-md-4 col-md-offset-4 " id='inputBox'>
            <div class="login-wrap" v-show="showLogin">
            <p v-show="showTishi">{{tishi}}</p>
            <input type="text" placeholder="请输入登录id" v-model="username">
            <input type="password" placeholder="请输入密码" v-model="password">
            <button v-on:click="login" button type="button" class="btn btn-primary">登录</button>
            <span v-on:click="ToRegister">忘记密码？</span>
            </div>                
          </div>
          
          
          <span v-on:click="ToRegister">没有账号？马上注册</span>
         
      </div>
        
  </div>
</template>

<script>
//逻辑尚未完善
  export default{
        data(){
            return{
                showLogin: true,
                showRegister: false,
                showTishi: false,
                tishi: '',
                username: '',
                password: '',
                newUsername: '',
                newPassword: ''
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
