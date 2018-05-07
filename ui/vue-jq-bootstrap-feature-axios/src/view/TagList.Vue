<template>
<div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <ul id="myTab" class="nav nav-tabs">
        
            <li><a href="#attention" data-toggle="tab">已关注</a></li>
            <li><a href="#unattention" data-toggle="tab">未关注</a></li>
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
            <div class="tab-pane fade in active" id="attention">
                <div class="pull-left form-inline">
                    <div class="sep5"></div>
                    当前页码：<strong>1</strong>  共<strong>1</strong>页
                  

                </div>
                <div class="pull-right form-inline">
                    <input type="text" placeholder="跳转的页码" class="form-control">  
                    <button type="button" class="btn btn-default">跳转</button>  
                    <button type="button" class="btn btn-default">下一页</button>
                </div>
                <div id="example-1">
                    <table class="table table-hover">
                        <thead >
                            <tr>
                                <th>操作</th>
                                <th>兴趣名</th>
                                <th>兴趣介绍</th>
                            </tr>
                        </thead>
                        <tbody v-for="item in items">
                            <tr>
                                <td class='hide'>{{item.tagId}}</td>
                                <td><button type="button" class="btn btn-default">取消关注</button>  </td>
                                <td width='200px'><a href="http://www.w3school.com.cn">{{item.tagName}}</a></td>
                                <td>{{item.tagInfo}}</td>
                            </tr>
                            
                        </tbody>
                    </table>
                    
                </div>
            </div>
            <div class="tab-pane fade" id="unattention" cellpadding="0" cellspacing="0" border="0" width="100%">
                
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
            tagId:1,
            tagName:'微积分',
            tagInfo:'微积分（Calculus）是高等数学中研究函数的微分（Differentiation）、积分(Integration)以及有关概念和应用的数学分支。它是数学的一个基础学科。',
        },{
            tagId:1,
            tagName:'大学英语',
            tagInfo:'英语也是与电脑联系最密切的语言，大多数编程语言都与英语有联系，而且随着网络的使用，英文的使用更普及。英语是联合国的工作语言之一。',
        },{
            tagId:1,
            tagName:'大学物理',
            tagInfo:'物理学是研究物质运动最一般规律和物质基本结构的学科。作为自然科学的带头学科，物理学研究大至宇宙，小至基本粒子等一切物质最基本的运动形式和规律，因此成为其他各自然科学学科的研究基础',
        }
    ];
  
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
</style>
