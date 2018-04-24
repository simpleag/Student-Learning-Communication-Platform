<template>
  <div class="row" id='div1'>
     
      <div class="col-md-6 col-md-offset-3 ">
          <div class="col-md-4 col-md-offset-4">
              <h2 class="form-signin-heading">注册</h2>
          </div>
           <div class="col-xs-2 ">
          </div>
          
          <div class="col-md-4 col-md-offset-4 " id='inputBox'>
            <div class="form-inline">  
               <input type="text" class="form-control" placeholder="请输入手机号码" />  
               <button class="btn btn-primary">验证码</button>  
           </div>  
            <div class="login-wrap" v-show="showLogin">
                <p v-show="showTishi">{{tishi}}</p>
                <input type="text" class="form-control" placeholder="请输入登录id" v-model="username">
                <input type="text" class="form-control" placeholder="请输入用户名" v-model="username">
                <input type="password" class="form-control" placeholder="请输入密码" v-model="password">
                <input type="password" class="form-control" placeholder="请确认密码" v-model="password">
                <input type="text" class="form-control" placeholder="请输入短信验证码" v-model="username">
                
                <button v-on:click="login" button type="button" class="btn btn-primary">注册</button>
                <span v-on:click="ToRegister">忘记密码？</span>
            </div> 
                           
          </div>
          
          
          <span v-on:click="ToRegister">已有帐号？请登录</span>
         
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
