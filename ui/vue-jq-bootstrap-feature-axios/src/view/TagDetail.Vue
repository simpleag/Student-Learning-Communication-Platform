<template>
<div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class='col-md-12 ' id='div3'>
            <h2>大学英语</h2>
            <strong>英语也是与电脑联系最密切的语言，大多数编程语言都与英语有联系，而且随着网络的使用，英文的使用更普及。英语是联合国的工作语言之一。</strong>
        </div>
        <div class='col-md-12 ' id='div5'>
            <button type="button" class="btn btn-default col-md-offset-11">取消关注</button>  
        </div>
        <div id='div4' class='col-md-12 '>
            <!-- 标签页 -->
        <ul id="myTab" class="nav nav-tabs">
        
            <li><a href="#article" data-toggle="tab">文章</a></li>
            <li><a href="#discuss" data-toggle="tab">讨论</a></li>
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
            </div>
            <div class="tab-pane fade" id="ejb">
                <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
                </p>
            </div>
        </div>
        </div>
    </div>

</div>
  
</template>

<script>
 import Vue from 'vue'
    export default{
        data(){
            return{
                items:items
            }
        },methods:{

        },mounted(){
            
        },
    }
     var items = [
         { 
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
                    }
     ]
</script>

<style scoped>
   #div1{
        background-color: #F2F2F2;
        
        height:auto!important;
        height:100px;
        min-height:900px;
    }
    #div2{
        height:auto!important;
        min-height:900px;
    }
    #div3{
         height:180px;
    }
    #div4{
         min-height:680px;
    }
    #div5{
         /* background-color:antiquewhite; */
         height:40px;
    }
    .sep5{
        height: 15px;
    }
</style>
