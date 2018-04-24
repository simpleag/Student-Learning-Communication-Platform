<template>
  <div class="row form-inline" id='div1'>
      
    <div class="col-md-6 col-md-offset-3 form-inlin">
        <!-- 标签页 -->
        <ul id="myTab" class="nav nav-tabs">
        
            <li><a href="#article" data-toggle="tab">文章</a></li>
            <li><a href="#discuss" data-toggle="tab">讨论</a></li>
            <li><a href="#anoymous" data-toggle="tab">匿名讨论</a></li>
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
                                        <div class="sep100 pull-left" >
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
            <div class="tab-pane fade" id="anoymous">
                <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
            </div>
            <div class="tab-pane fade" id="ejb">
                <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
                </p>
            </div>
        </div>
    </div>
    <div class="col-md-3 " id='tagList'>

        <div class="col-md-6 ">
           <img src="https://cdn.v2ex.com/gravatar/ffe9848d066049b0b85b228165dc4420?s=48&d=retro" class="img-responsive img-circle" width="100%"alt="Responsive image">
            <strong>测试用户5</strong>
       </div>
        
       <div class="col-md-3 ">
           
            <button type="button" class="btn btn-default " style="margin-top: 10px">发表讨论</button>
            <button type="button" class="btn btn-default " style="margin-top: 10px">发表文章</button>
            <button type="button" class="btn btn-default " style="margin-top: 10px">发表匿名讨论</button>
       </div>
       <div class="col-md-12 sep5" >
       </div>
        <div class="col-md-12 sep5" >
       </div>
        <div class="col-md-12 sep5" >
       </div>
       <div class="col-md-12 sep5" >
           <strong>关注的元素</strong>
           <button type="button" class="btn btn-default" >全部</button>
       </div>
        <div class="col-md-12 sep5" >
       </div>
        <div class="col-md-12 sep5" >
       </div>
        <div class="col-md-12 sep5" >
       </div>
       <div class="col-md-12 " >
           <div v-for="item in items">
               <span>
                   <strong>{{item.TagName}}</strong>
                   <p>{{item.Info}}</p>
               </span>
           </div>
            
       </div>
    </div>
        
  </div>
  
</template>

<script>
import Vue from 'vue'

  var userLoginId = '';
  var userpwd = '';
  export default{
        data(){
            return{
                items:items
            }
        },methods:{

        },mounted(){
            axios.post('/localhost:5555/api/user/login', params, config)
            .then(function (response) {
                return response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
    },
    }
  var items = [
                    { 
                        discussId: 'Foo', 
                        discussTitle: '一直不会微积分，今后生活会比别人困难吗？',
                        authorId: '1',
                        userName: '用户1',
                        authorPicUrl: 'https://cdn.v2ex.com/avatar/c2db/c77d/146640_normal.png?m=1452582980',
                        createTime: '20180410 12:05:00',
                        approveNumber: '0',
                        commentNumber: '0',
                        TagId: 'TagId',
                        TagName: '微积分',
                        Info:"微积分（Calculus）是高等数学中研究函数的微分（Differentiation）、积分(Integration)以及有关概念和应用的数学分支。它是数学的一个基础学科。",
                    }, { 
                        discussId: 'Foo', 
                        discussTitle: '一直不会英语，今后生活会比别人困难吗？',
                        authorId: '1',
                        userName: '用户2',
                        authorPicUrl: 'https://cdn.v2ex.com/avatar/e1bd/dcd9/269624_normal.png?m=1519611593',
                        createTime: '20180410 12:05:00',
                        approveNumber: '1',
                        commentNumber: '0',
                        TagId: 'TagId',
                        TagName: '大学英语',
                        Info:"英语也是与电脑联系最密切的语言，大多数编程语言都与英语有联系，而且随着网络的使用，英文的使用更普及。英语是联合国的工作语言之一。 "
                    },{ 
                        discussId: 'Foo', 
                        discussTitle: '一直不会物理，今后生活会比别人困难吗？',
                        authorId: '1',
                        userName: '用户3',
                        authorPicUrl: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523947645&di=75a63bf92ff2f84f53989e29fdc017b8&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.colabug.com%2F2017%2F12%2F88677b9a4ba11a8407d3567cb729198f.png',
                        createTime: '20180410 12:05:00',
                        approveNumber: '0',
                        commentNumber: '0',
                        TagId: 'TagId',
                        TagName: '大学物理',
                        Info: "物理学是研究物质运动最一般规律和物质基本结构的学科。作为自然科学的带头学科，物理学研究大至宇宙，小至基本粒子等一切物质最基本的运动形式和规律，因此成为其他各自然科学学科的研究基础。"
                    },
                    
                ];


 
</script>

<style scoped>
 .login-wrap{text-align:center;}
    input{display:block; width:250px; height:40px; line-height:40px; margin:10px auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
    /* p{color:red;} */
    
    span{cursor:pointer;}
    /* span:hover{color:#41b883;} */
    #div1{
        background-color: #F2F2F2;
        
        height:auto!important;
        height:100px;
        min-height:900px;
    }
    /* 最小且适应高度 */
    #tagList{
        /* background-color: #41b883; */
        height:auto!important;
        height:100px;
        min-height:900px;
    }
    #example-1{
        /* background-color: #41b883; */
    }
    td {
        width: 48px;
        vertical-align: top;
        text-align: -webkit-center;
    }
    table {
        -webkit-border-horizontal-spacing: 0px;
        -webkit-border-vertical-spacing: 0px;
        border-top-width: 0px;
        border-right-width: 0px;
        border-bottom-width: 0px;
        border-left-width: 0px;
        width: 100%;
        /* table-layout:fixed; */
    }
    .item_title{
        font-size:16px;
        line-height: 130%;
        text-shadow: 0 1px #fff;
        word-wrap: break-word;
        
    }
    .sep5{
        height: 15px;
    }
    .sep100{
        height: 100px;
        width: 100px;
    }
    .sep200{
        height: 200px;
        width: 200px;
    }
    .sepr100{
        height: 80px;
        width: 100;
        align-self: right;
        align-content: right;
    }
    /* td, th {
        display: table-cell;
        vertical-align: inherit;
    } */
    img {
        border-top-width: 0px;
        border-right-width: 0px;
        border-bottom-width: 0px;
        border-left-width: 0px;
        border-top-style: solid;
        border-right-style: solid;
        border-bottom-style: solid;
        border-left-style: solid;
    }
    .page_current {
    display: inline-block;
    font-weight: 700;
    font-size: 14px;
    line-height: 14px;
    padding: 3px 6px 3px 6px;
    background-color: #f0f0f0;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    margin: 0 1px 0 1px;
    border: 1px solid #bbb;
    color: #000;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
   
</style>
