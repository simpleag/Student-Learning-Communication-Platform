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
                        <div class="form-group col-xs-9" style="margin-top: 10px;">  
                            <input type="text" placeholder="搜索" class="form-control">  
                        </div>  
                        <button type="submit" class="btn btn-default col-xs-3" style="margin-top: 10px">搜索</button>  
                    </form>  
    
                    <ul class="nav navbar-nav navbar-right">  
                        <li><a href="#">0条未读私信</a></li> 
                        <li><a href="#">0条未读消息</a></li>  
                        <li class="dropdown">  
                            <!--data-toggle与JS文件有关-->  
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">  
                                用户个人主页  
    `                           <span class="caret"></span>  
                            </a>  
                            <ul class="dropdown-menu" role="menu">  
                                <li><a href="" role="menuitem">设置</a></li>  
                                <li><a href="" role="menuitem">个人中心</a></li>  
                                
                            </ul>  
                        </li>  
                    </ul>  
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

export default {
    name: 'home',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
      }
    },
    methods:{
     
    },
    mounted(){
      
    },
    // components: {
    //   Main: Main
    // }
  }

  
  
</script>

<style scoped>
  h1, h2 {
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
  
  #top{
      background-color:black;
  }
  #btn_show {
    border-color: #108ee9;
    color: #108ee9;
  }
  #btn_show:active,#btn_show:focus,#btn_show:hover {
    background-color: #108ee9;
    color: #fff;
    outline: none;
  }
</style>
