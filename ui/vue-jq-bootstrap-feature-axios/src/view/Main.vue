<template>
  <div class="row form-inline" id='div1'>
      
    <div class="col-md-6 col-md-offset-3 form-inlin">
        <!-- 标签页 -->
        <el-tabs v-model="activeName" @tab-click="handleClick" >
            
            <el-tab-pane label="讨论" name="discuss" >
                <!-- <div class="block">
                     <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage3"
                        :page-size="1"
                        layout="prev, pager, next, jumper"
                        :total="discussPages">
                        </el-pagination>
                </div> -->
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
                                            <!-- <a href="/"> -->
                                            <img :src="item.userPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link>
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        <P>来自<strong>{{item.tagName}}</strong></P>
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag>
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <el-tag type="success">{{item.approveNumber}}个赞同</el-tag>
                                    <p></p>
                                    <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag>
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
                 <div class="block">
                   <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page.sync="disscussNow"
                    :page-size="10"
                    layout="prev, pager, next, jumper"
                    :total="discussPages">
                    </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="文章" name="article">
                <div id="example-1">
                    <div v-for="item in articlesList">
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
                                            <img :src="item.userPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <router-link :to="{ name: 'Article', params : {articleId: item.articleId, userId: userId}}">{{item.articleTitle}}</router-link>
                                        <!-- <a href="www.baidu.com"> {{item.articleTitle}}</a> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        <P>来自<strong>{{item.tagName}}</strong></P>
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <strong>{{item.articleCommentNumber}}</strong>条回复 -->
                                        <el-tag type="danger"><strong>{{item.articleCommentNumber}}</strong>条回复</el-tag>
                                    </span>
                                    <p></p>
                                    <span >
                                        <el-tag type="success">{{item.approveNumber}}个赞同</el-tag>
                                        <p></p>
	                                    <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag>
                                    <!-- <button type="button" class="btn btn-default">{{item.approveNumber}}个赞同</button>
                                    <button type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
                 <div class="block">
                     
                   <el-pagination
                    @current-change="articleHandleCurrentChange"
                    :current-page.sync="articleNow"
                    :page-size="10"
                    layout="prev, pager, next, jumper"
                    :total="articlePages">
                    </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="匿名讨论" name="anoymous">
                <div id="example-1">
                    <div v-for="item in anoymousList">
                        <table class="table table-striped table-hover ">
                            <thead>
                                <tr>
                                <!-- <th width="15%"></th> -->
                                <th width="55%"></th>
                                <th width="30%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <!-- <td  class="col-md-2">
                                        <div class="sep100 pull-center" >
                                            <a href="wwww.baidu.com">
                                            <img :src="item.userPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td> -->
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <router-link :to="{ name: 'Article', params : {anoymousId: item.anoymousId, userId: userId}}">{{item.anoymousTitle}}</router-link>
                                        <!-- <a href="www.baidu.com"> {{item.anoymousTitle}}</a> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userAnonymouseName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <!-- <p></p>
                                        <P>来自<strong>{{item.tagName}}</strong></P> -->
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                       <el-tag type="danger"><strong>{{item.anoymousCommentNumber}}</strong>条回复</el-tag>
                                        <!-- <strong>{{item.anoymousCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                        <el-tag type="success">{{item.approveNumber}}个赞同</el-tag>
                                        <p></p>
	                                    <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag>
                                    <!-- <button type="button" class="btn btn-default">{{item.approveNumber}}个赞同</button>
                                    <button type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
                 <div class="block">
                   <el-pagination
                    @current-change="anoymousHandleCurrentChange"
                    :current-page.sync="anoymousNow"
                    :page-size="1"
                    layout="prev, pager, next, jumper"
                    :total="anoymousPages">
                    </el-pagination>
                </div>
            </el-tab-pane>
        </el-tabs>
        
    </div>
    <div class="col-md-3 " id='tagList'>

        <div class="col-md-6 ">
           <img :src = 'user.userPicUrl' class="img-responsive img-circle" width="100%"alt="Responsive image">
            <strong>{{user.userName}}</strong>
       </div>
        
       <div class="col-md-3 ">
           
            <button type="button" @click="createDiscuss()" class="btn btn-default " style="margin-top: 10px">发表讨论</button>
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
           <div v-for="tag in tags">
               <span>
                   <strong>{{tag.tagName}}</strong>
                   <p>{{tag.tagInfo}}</p>
               </span>
           </div>
            
       </div>
    </div>
        
  </div>
  
</template>

<script>
import Vue from "vue";
//另外，由于axios默认发送数据时，数据格式是Request Payload，而并非我们常用的Form Data格式，PHP后端未必能正常获取到，所以在发送之前，需要使用qs模块对其进行处理
import qs from "qs";
import { formatDate } from "../common/date.js";

var userLoginId = "";
var userpwd = "";
var userId;
var user = "";
var tags = [];
var discussPages = 0;
var discussPagesNumber = [];
var articlePages = 0;
var anoymousPages = 0;
var articlesList = [];
var anoymousList = [];
var articleNow = 1;
var disscussNow = 1;
var anoymousNow = 1;
//   var parms = new URLSearchParams();
//   parms.append('userId',userId);
//   parms.append('pageNumber',1);
//   parms.append('pageSize',1);
export default {
  components: {
    formatDate: formatDate
  },
  data() {
    return {
      items: items,
      activeName: "discuss",
      anoymousList: anoymousList,
      articlesList: articlesList,
      anoymousPages: anoymousPages,
      articlePages: articlePages,
      discussPages: discussPages,
      tags: tags,
      articleNow: articleNow,
      disscussNow: disscussNow,
      anoymousNow: anoymousNow,
      userId: this.userId
    };
  },
  methods: {
    // clickDiscussApprove(id, type) {
    //     var tempList;
    //     // var temp;
    //     if (type == 1) {
    //         tempList = this.items;
    //     } else if (type == 2) {
    //         tempList = this.articlesList;
    //     } else if (type == 3) {
    //         tempList = this.anoymousList;
    //     } else {

    //     }
    //     if (tempList == undefined || tempList.length == 0) {

    //     } else {
    //         for(var i=0;i<tempList.length;i++) {
    //             var temp = tempList[i];
    //             if (type==1 && id==temp.discussId ) {

    //             } else if (type==2 && id==temp.articleId) {

    //             } else if (type==3 && id==temp.anoymousId) {

    //             }
    //         }
    //     }

    // },
    createDiscuss() {
      this.$router.push({ name: "CreateDiscuss", params: { userId: this.userId } });
    },
    errorNet(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
        this.$router.push({ name: "Login" });
      },
    handleClick(tab, event) {
      console.log("tab:" + tab.name + "+" + event);
      if (tab.name == "article") {
        console.log("articleSuccess");
        this.ajaxArticles(articleNow);
      } else if (tab.name == "discuss") {
        console.log("discussSuccess");
        this.ajax(disscussNow);
      } else if (tab.name == "anoymous") {
        console.log("anoymousSuccess");
        this.ajaxAnoymous(anoymousNow);
      }
    },
    handleCurrentChange(val) {
      disscussNow = val;
      this.ajax(val);
    },
    articleHandleCurrentChange(val) {
      articleNow = val;
      this.ajaxArticles(val);
    },
    anoymousHandleCurrentChange(val) {
      anoymousNow = val;
      this.ajaxAnoymous(val);
    },
    ajax: function(number) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/discuss/listOrderByTime",
          qs.stringify({
            userId: this.userId,
            pageNumber: number,
            pageSize: "10"
          })
        )
        .then(function(res) {
          _this.initDiscuss(res);
        })
        .catch(function(err) {
          console.log(err);
        });
    },
    initDiscuss: function(res) {
      console.log("code" + res.data.code + res.data.message);
      items = res.data.discussList.list;
      discussPages = res.data.discussList.total;
      console.log("page" + discussPages);
      this.discussPages = discussPages;
      discussPagesNumber = res.data.discussList.navigatepageNums;
      this.discussPagesNumber = discussPagesNumber;
      var date = new Date(res.data.discussList.list[0].createTime);
      console.log("data" + formatDate(date, "yyyy-MM-dd hh:mm"));
      console.log(items[0].tagName);

      this.items = items;
    },
    ajaxUser: function() {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/user/baseInfo",
          qs.stringify({
            userId: this.userId
          })
        )
        .then(function(res) {
          _this.initUser(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    initUser: function(res) {
      user = res.data.limitUserInfo;
      this.user = user;
      console.log("user" + res.data.limitUserInfo.userName);
    },
    ajaxTag: function() {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/tag/userAttentionTags",
          qs.stringify({
            userId: this.userId,
            pageNumber: "1",
            pageSize: "5"
          })
        )
        .then(function(res) {
          _this.initTags(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    initTags: function(res) {
      tags = res.data.attentionTags.list;
      this.tags = tags;
      // console.log('user'+ res.data.limitUserInfo.userName)
    },
    ajaxArticles: function(number) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/article/listOrderByTime",
          qs.stringify({
            userId: this.userId,
            pageNumber: number,
            pageSize: "10"
          })
        )
        .then(function(res) {
          _this.initArticles(res);
        })
        .catch(function(err) {
          console.log(err);
        });
    },
    initArticles: function(res) {
      articlesList = res.data.articleList.list;
      this.articlesList = articlesList;
      articlePages = res.data.articleList.total;
      console.log("page" + articlePages);
      this.articlePages = articlePages;
      // console.log('user'+ res.data.limitUserInfo.userName)
    },
    ajaxAnoymous: function(number) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/anoymous/listOrderByTime",
          qs.stringify({
            userId: this.userId,
            pageNumber: number,
            pageSize: "10"
          })
        )
        .then(function(res) {
          _this.initAnoymous(res);
        })
        .catch(function(err) {
          console.log(err);
        });
    },
    initAnoymous: function(res) {
      anoymousList = res.data.anoymousList.list;
      this.anoymousList = anoymousList;
      anoymousPages = res.data.anoymousList.total;
      console.log("page" + anoymousPages);
      this.anoymousPages = anoymousPages;
      // console.log('user'+ res.data.limitUserInfo.userName)
    },

    init() {
      console.log("testUserId"+ window.localStorage.getItem("userId"))
      this.userId = window.localStorage.getItem("userId");
      this.ajaxTag();
      this.ajax(1);
      this.ajaxUser();
    }
  },
  mounted() {
    this.init();
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh : mm");
    }
  }
};
var items = [];
</script>

<style scoped>
.login-wrap {
  text-align: center;
}
input {
  display: block;
  width: 250px;
  height: 40px;
  line-height: 40px;
  margin: 10px auto;
  margin-bottom: 10px;
  outline: none;
  border: 1px solid #888;
  padding: 10px;
  box-sizing: border-box;
}
/* p{color:red;} */

span {
  cursor: pointer;
}
/* span:hover{color:#41b883;} */
#div1 {
  background-color: #f2f2f2;

  height: auto !important;
  height: 100px;
  min-height: 900px;
}
/* 最小且适应高度 */
#tagList {
  /* background-color: #41b883; */
  height: auto !important;
  height: 100px;
  min-height: 900px;
}
#example-1 {
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
.item_title {
  font-size: 16px;
  line-height: 130%;
  text-shadow: 0 1px #fff;
  word-wrap: break-word;
}
.sep5 {
  height: 15px;
}
.sep100 {
  height: 100px;
  width: 100px;
}
.sep200 {
  height: 200px;
  width: 200px;
}
.sepr100 {
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
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}
</style>
