<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
    <div class="col-md-12 text-left" id='div3'>
        <h1>我的私信</h1>
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
                                            <strong>来自   {{item.userName}}</strong>发表于: {{item.createTime}}</strong>
                                        <!-- <a href="www.baidu.com"> {{item.discussTitle}}</a> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容
                                            正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容正文内容
                                            正文内容正文内容正文内容正文内容正文内容正文内容正文内容
                                        </strong>
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <strong>历史</strong>消息
                                        <p></p>
                                    </span>
                                    <span>
                                    <button type="button" class="btn btn-default">回复（在历史消息里实现好了）</button>
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
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
#div1{
        background-color: #F2F2F2;
        
        height:auto!important;
        height:100px;
        min-height:900px;
}
#div2{
        background-color: darkslateblue;
        height:auto!important;
        min-height:900px;
}
#div3{
    background-color:darkgreen;
    min-height:20px;
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

