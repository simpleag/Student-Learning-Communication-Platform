<template>
  <div class="home" id='home'>
     <!--class=navbar-fixed-top导航条固定在顶部，class=navbar-inverse黑色背景-->  
        <nav class="navbar navbar-default navbar-fixed-top " role="navigation">  
            <div class="container-fluid">  
                <div class="navbar-header">  
                    <router-link to="/Home/Main" class="navbar-brand" >学生学习交流平台</router-link>  
                </div>  
    
                <div class="collapse navbar-collapse">  
                    <!-- <ul class="nav navbar-nav" id="mytab">  
                        <li class="active"><button type="button" class="btn btn-default " style="margin-top: 10px">发表讨论</button>  </li> 
                        <li class="active"><button type="button" class="btn btn-default " style="margin-top: 10px">发表文章</button>  </li> 
                        <li class="active"><button type="button" class="btn btn-default " style="margin-top: 10px">发表匿名讨论</button>  </li> 
                    </ul>   -->
    
                    <form class="narbar-form navbar-left" role="search">  
                        <!-- <div class="form-group col-xs-9" style="margin-top: 10px;">  
                            <input type="text" placeholder="搜索" class="form-control">  
                        </div>  
                        <button type="submit" class="btn btn-default col-xs-3" style="margin-top: 10px">搜索</button>   -->
                    </form>  
    
                    <ul class="nav navbar-nav navbar-right">  
                      <!-- <li><el-badge :value= messageNumber :max="99" class="item">
                        <el-button size="small">未读私信</el-button>
                      </el-badge></li> 
                      <li><el-badge :value= infoNumber :max="99" class="item">
                        <el-button size="small">未读消息</el-button>
                      </el-badge></li>  -->
                        <!-- <li><a href="#">0条未读私信</a></li> 
                        <li><a href="#">0条未读消息</a></li>   -->
                        <li class="dropdown">  
                            <!--data-toggle与JS文件有关-->  
                            <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown">  
                                用户个人主页 
    `                           <span class="caret"></span>  
                            </a>  
                            <ul class="dropdown-menu" role="menu">  
                                <li><a href="" role="menuitem">设置</a></li>  
                                <li><a href="" role="menuitem">个人中心</a></li>  
                                
                            </ul>   -->
                        </li>  
                    </ul>
                    <p></p>
                    <el-button class="pull-right" type="danger" @click="logOut()">登出</el-button>  
                </div>  
            </div>  
        </nav>  
    <div class="container-fluid form-inline">
    <router-view></router-view>
    </div>
  </div>
  

</template>

<script>
// import Main from './Main'
var user = {
  userId: "1",
  userName: "user1",
  userPicUrl:
    "https://cdn.v2ex.com/gravatar/ffe9848d066049b0b85b228165dc4420?s=48&d=retro"
};
export default {
  name: "home",
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
      cc: "",
      infoNumber: 0,
      messageNumber: 0,
      user: user
    };
  },
  methods: {
    logOut() {
      window.localStorage.removeItem("userId");
      var exp = new Date();
      var name = 'X_TOKEN';
      exp.setTime(exp.getTime() - 1);
      var cval = this.getCookie(name);
      if (cval != null) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
      }
      this.$router.push({ name: "Login" });
    },
    getCookie(name) {
      var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
      if (arr = document.cookie.match(reg))
        return (arr[2]);
      else
        return null;
      },
    ajax: function() {
      var _this = this;
      axios
        .get(
          "http://localhost:8093/user/usersInfoAndMessageNumber?userId=2",
          {}
        )
        .then(function(res) {
          _this.init(res);
        })
        .catch(function(err) {
          console.log(err);
        });
    },

    init: function(res) {
      this.messageNumber = res.data.messageNumber;
      this.infoNumber = res.data.infoNumber;
    }
  },
  mounted() {
    this.ajax();
  }
  // components: {
  //   Main: Main
  // }
};
</script>

<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

img {
  width: 160px;
}

#alert {
  margin: 0 auto;
  width: 500px;
}

#top {
  background-color: black;
}
#btn_show {
  border-color: #108ee9;
  color: #108ee9;
}
#btn_show:active,
#btn_show:focus,
#btn_show:hover {
  background-color: #108ee9;
  color: #fff;
  outline: none;
}

.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
