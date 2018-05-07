<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <ul id="myTab" class="nav nav-tabs">
        
            <li><a href="#userPicUrl" data-toggle="tab">修改头像</a></li>
            <li><a href="#honor" data-toggle="tab">修改社区信息</a></li>
            <li><a href="#base" data-toggle="tab">修改个人资料</a></li>
            <li><a href="#sercurity" data-toggle="tab">修改账户安全信息</a></li>
            
            <!-- <li class="dropdown">
                <a href="#" id="myTabDrop1" class="dropdown-toggle" 
                data-toggle="dropdown">
                    Java <b class="caret"></b></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                    <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
                    <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
                </ul>
            </li> -->
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="article">
                <p>菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
            
            </div>
            <div class="tab-pane fade" id="discuss" cellpadding="0" cellspacing="0" border="0" width="100%">
                <div class="pull-left form-inline">
                    <div class="sep5"></div>
                    当前页码：<strong>1</strong>  共<strong>1</strong>页
                    <button type="button" class="btn btn-default">按照时间排序</button> 
                    <button type="button" class="btn btn-default">按照热度排序</button> 

                </div>
                <div class="pull-right form-inline">
                    <input type="text" placeholder="跳转的页码" class="form-control">  
                    <button type="button" class="btn btn-default">跳转</button>  
                    <button type="button" class="btn btn-default">下一页</button>
                </div>
                <div id="example-1">
                    <div v-for="item in items">
                        <table class="table table-striped table-hover ">
                            <thead>
                                <tr>
                                <th width="15%"></th>
                                <th width="55%"></th>
                                <th width="30%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <td  class="col-md-2">
                                        <div class="sep100 pull-center" >
                                            <a href="wwww.baidu.com">
                                            <img :src="item.authorPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                        <a href="www.baidu.com"> {{item.discussTitle}}</a>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userName}}</strong>发表于: {{item.createTime}} 来自<strong>{{item.TagName}}</strong>
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <strong>{{item.commentNumber}}</strong>条回复
                                        <p></p>
                                    </span>
                                    <span>
                                    <button type="button" class="btn btn-default">{{item.approveNumber}}个赞同</button>
                                    <button type="button" class="btn btn-default">0个收藏</button>
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
            <div class="tab-pane fade" id="base">
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class="input-group col-xs-4">
                    <span class="input-group-addon">登录id</span>
                    <input type="text" class="form-control" disabled="disabled" placeholder="testLogin1">
		        </div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class="input-group col-xs-4">
                    <span class="input-group-addon">用户名</span>
                    <input type="text" class="form-control" disabled="disabled" placeholder="用户1">
		        </div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class="input-group col-xs-4" >
                    <span class="input-group-addon">性别</span>
                    <select class="form-control" >
                        <option>保密                 </option>
                        <option>男</option>
                        <option>女</option>
                    </select>
		        </div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class="input-group col-xs-4">
                        <span class="demonstration">生日</span>
                        <el-date-picker v-model="value1"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                </div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class='sep5'></div>
                <div class="input-group col-xs-4" >
                    <span class="input-group-addon">学历</span>
                    <select class="form-control" >
                        <option>小学                 </option>
                        <option>初中</option>
                        <option>高中</option>
                        <option>本科</option>
                        <option>研究生</option>
                    </select>
		        </div>
                
            </div>
            <div class="tab-pane fade" id="ejb">
                <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
                </p>
            </div>
        </div>
    </div>
   </div>
</template>

<script>
import ElementUI from 'element-ui'
 import Vue from 'vue'
    export default{
        components: {
            
        },
        data(){
            return{
                items:items,
                value1: ''
            }
        },methods:{

        },mounted(){
            
        },
    }
</script>


<style scoped>
#div1{
        background-color: #F2F2F2;
        
        height:auto!important;
        height:100px;
        min-height:900px;
}
#div2{
        /* background-color: darkslateblue; */
        height:auto!important;
        min-height:800px;
}
#div3{
    /* background-color:darkgreen; */
    height:200px;
}
#div4{
    /* background-color:crimson; */
    height:auto!important;
    min-height:80px;
}
#div5{
    /* background-color:chartreuse; */
    height:auto!important;
    min-height:30px;
}
#div6{
    /* background-color:yellow; */
    height:auto!important;
    min-height:50px;
}
#div7{
    /* background-color:violet; */
    height:auto!important;
    min-height:500px;
}
.sep5{
    height: 5px;
}

</style>

