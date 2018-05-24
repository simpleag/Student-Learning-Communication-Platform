<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class="col-md-12" id='div7'>
            <img :src="articleDetail.articlePicUrl" width="100%" height="100%" class="center-block" alt="Responsive image">
        </div>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class="col-md-9 text-left">
                <h2 class="text-left">{{articleDetail.articleTitle}}</h2>
            </div>
            <div class="col-md-3 text-center text-right">
                <div class='sep5'></div>
                
                <button @click="clickDiscussApprove()" type="button" class="btn btn-default" >{{articleDetail.approveNumber}}个赞同</button>
                <button @click="clickDiscussFavorite()" type="button" class="btn btn-default">{{articleDetail.favoriteNumber}}个收藏</button>
            </div>
        </div>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class="col-md-3" id='div4'>
              <router-link :to="{ name: 'UserCenter', params : {targetUserId: articleDetail.articleAuthorId}}">
                <img :src="articleDetail.userPicUrl" style="width:100%; height:200px;" class="img-responsive img-circle" border="0" align="default" >
                 </router-link> 
                <div class='sep5'></div>
                <strong>{{articleDetail.userHonor}}</strong>
                <div class='sep5'></div>
                <strong>{{articleDetail.userName}}</strong>
            </div>
             <div class="col-md-9" id='div5'>
                 <p class='text-justify' v-html='articleDetail.articleContent'></p>
                 <!-- {{articleDetail.discussContent}} -->
            </div>
            
        </div>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class='sep5'></div>
            <p class="text-right">发表于{{articleDetail.createTime | formatDate}} 共<strong>{{articleDetail.articleCommentNumber}}</strong>条回复</p>
        </div>
        <div class="col-md-12"id="div6">
            <div v-for="item in commentList">
                        <table class="table table-striped table-hover ">
                            <thead>
                                <tr>
                                <th width="15%"></th>
                                <th width="70%"></th>
                                <th width="15%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <td  class="col-md-3">
                                        <div class="sep100 pull-center" >
                                            <!-- <a href="wwww.baidu.com"> -->
                                               <router-link :to="{ name: 'UserCenter', params : {targetUserId: item.articleCommentAuthorId}}">
                                            <img :src="item.userPicUrl"  class=" img-circle" border="0" align="default" style="width:100%; height:180px;">
                                             </router-link>
 
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-8 pull-left">
                                        <p class="text-left"><strong>{{item.userName}}</strong>发表于: {{item.createTime | formatDate}}</strong></p>
                                    </span>
                                    <div class="sep5"></div>
                                    <p class="text-left">
                                    <span >
                                        <strong v-html="item.articleCommentContent"></strong>
                                    </span>
                                    </p>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                       <strong>#{{item.articleListNumber}}</strong>
                                       <p></p>
                                        
                                    </span>
                                    <span>
                                    <button @click="clickCommentApprove(item.articleCommentId)" type="button" class="btn btn-default">{{item.approveNumber}}个赞同</button>
                                    <button @click="clickReplayComment(item.userName, item.articleCommentAuthorId)" type="button" class="btn btn-default">回复评论</button>
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
        
        </div>
        <div class="col-md-12"id="div6">
            <div class="block">
                   <el-pagination
                    @current-change="commentHandleCurrentChange"
                    :current-page.sync="commentPagesNow"
                    :page-size="pageSize"
                    layout="prev, pager, next, jumper"
                    :total="commentTotal">
                    </el-pagination>
                </div>
        </div>

        <div class="col-md-12"id="div6">
            <div class='sep5'></div>
           <div class="components-container">
               <div class='text-right'>
                    <button @click="createComment()" type="button" class="btn btn-default text-right">发表回复</button> 
               </div>
                
                <!-- <button @click="getUEContent()" >获取内容</button> -->
                <div class="editor-container">
                    <!-- <editor-c ref="we">
                        
                    </editor-c> -->
                <UE :defaultMsg=defaultMsg :config=config ref="ue"></UE>
                </div>
            </div>
             
        </div>
       
        
    </div>
   </div>
   
</template>

<script>
import Vue from "vue";
import UE from "../components/Ue.vue";
import qs from "qs";
import { formatDate } from "../common/date.js";
import EditorC from "../components/Editor";
var commentList = [];
var articleDetail;
var commentPages;
var pageSize = 10;
var commentPagesNow = 1;
var defaultMsg = "在这里输入回复";
var replayUserId = 0;
var articleCommentNumber = 0;
var commentTotal = 0;
var userId;
var articleId;
// var name = 'editor';
export default {
//   name: "editor",
  components: {
    UE,
    formatDate: formatDate,
    EditorC
  },
  data() {
    return {
      items: items,
      defaultMsg: defaultMsg,
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 350,
        maximumWords: 255
      },
      articleDetail: articleDetail,
      commentList: commentList,
      commentPages: commentPages,
      commentPagesNow: commentPagesNow,
      pageSize: pageSize,
      commentTotal: commentTotal,
      replayUserId: replayUserId,
      // input content to editor
      content: "base on wangeditor",
      // output content from editor
      result: ""
      // set image upload api url
    };
  },
  methods: {
    getUEContent() {
      let content = this.$refs.ue.getUEContent();
      this.$notify({
        title: "获取成功，可在控制台查看！",
        message: content,
        type: "success"
      });
      console.log(content);
    },

    clickReplayComment(userName, userId) {
      this.defaultMsg = "回复" + userName + ": ";
      this.replayUserId = userId;
      console.log("set");

      var ueTemp = this.$refs.ue;
      console.log(ueTemp.setUEContent(this.defaultMsg));
    },

    clickCommentApprove(articleCommentId) {
      console.log("atteath" + articleCommentId);
      //   var item;
      // console.log('length'+this.commentList.length)
      for (var i = 0; i < this.commentList.length; i++) {
        var item = this.commentList[i];
        //   console.log('atteath2'+item.articleCommentId)
        if (item.articleCommentId == articleCommentId) {
          this.ajaxApproveArticleComment(this.userId, articleCommentId);
          if (item.userApproveType == null || item.userApproveType == 0) {
            item.userApproveType = 1;
            item.approveNumber += 1;
          } else {
            item.userApproveType = 0;
            item.approveNumber -= 1;
          }
        }
      }
    },
    clickDiscussApprove() {
      if (
        this.articleDetail.userApproveType == null ||
        this.articleDetail.userApproveType == 0
      ) {
        this.articleDetail.approveNumber += 1;
        this.articleDetail.userApproveType = 1;
      } else {
        this.articleDetail.approveNumber -= 1;
        this.articleDetail.userApproveType = null;
      }
      this.ajaxApproveArticle(this.userId, this.articleId);
    },
    clickDiscussFavorite() {
      if (
        this.articleDetail.userFavoriteType == null ||
        this.articleDetail.userFavoriteType == 0
      ) {
        this.articleDetail.favoriteNumber += 1;
        this.articleDetail.userFavoriteType = 1;
      } else {
        this.articleDetail.favoriteNumber -= 1;
        this.articleDetail.userFavoriteType = null;
      }
      this.ajaxFavoriteArticle(this.userId, this.articleId);
    },
    ajaxArticle: function(userId, articleId) {
      var _this = this;
      console.log("userIDd: " + userId);
      console.log("articleId: " + articleId);
      axios
        .post(
          "http://localhost:8093/article/detail",
          qs.stringify({
            userId: userId,
            articleId: articleId
          })
        )
        .then(function(res) {
           
          _this.initArticle(res);

        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    createComment() {
      let content = this.$refs.ue.getUEContent();
      //提醒
      // this.$notify({
      //   title: '获取成功，可在控制台查看！',
      //   message: content,
      //   type: 'success'
      // });
      console.log(content);
      this.articleCommentNumber = this.articleCommentNumber + 1;
      this.ajaxCreateComment(
        this.userId,
        content,
        this.articleCommentNumber,
        this.replayUserId,
        this.articleId
      );
    },
    ajaxCreateComment: function(
      userId,
      content,
      number,
      replayUserId,
      articleId
    ) {
      var _this = this;
      console.log("userId: " + userId);
      console.log("conetent: " + content);
      console.log("number: " + number);
      console.log("replayUserId: " + replayUserId);
      console.log("articleId: " + articleId);
      axios
        .post(
          "http://localhost:8093/comment/createArticleComment",
          qs.stringify({
            userId: userId,
            articleCommentAuthorId: userId,
            articleCommentContent: content,
            articleListNumber: number,
            articleReplayUserid: replayUserId,
            articleId: articleId
          })
        )
        .then(function(res) {
          window.location.reload();
          console.log(res.data.code + " " + res.data.message);
          // _this.initDiscuss(res);
          //    this.ajaxComment(this.$route.params.userId, this.$route.params.discussId,1,10)
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxFavoriteArticle: function(userId, articleId) {
      var _this = this;
      console.log("userId: " + userId);
      axios
        .post(
          "http://localhost:8093/article/userFavorite",
          qs.stringify({
            userId: userId,
            articleId: articleId
          })
        )
        .then(function(res) {
          console.log(res.data.code);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxApproveArticle: function(userId, articleId) {
      var _this = this;
      console.log("userIDd: " + userId);
      axios
        .post(
          "http://localhost:8093/article/userApprove",
          qs.stringify({
            userId: userId,
            articleId: articleId
          })
        )
        .then(function(res) {
          console.log(res.data.code);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxApproveArticleComment: function(userId, commentId) {
      var _this = this;
      console.log("userIDd: " + userId);
      axios
        .post(
          "http://localhost:8093/comment/approveComment",
          qs.stringify({
            userId: userId,
            commentId: commentId,
            type: 1
          })
        )
        .then(function(res) {
          if (res.data.code == '200') {
             window.location.reload();
          } else {
            this.errorNotice('res.data.code');
          }
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    errorNotice(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
      },
    ajaxComment: function(userId, articleId, pageNumber, pageSize) {
      var _this = this;
      // console.log('userIDd: '+ userId);
      axios
        .post(
          "http://localhost:8093/comment/listComment",
          qs.stringify({
            userId: userId,
            targetId: articleId,
            pageNumber: pageNumber,
            pageSize: pageSize,
            type: 2
          })
        )
        .then(function(res) {
          _this.initComment(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
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
    initArticle: function(res) {
      console.log("code" + res.data.code);
      this.articleDetail = res.data.article;
      // console.log('article: '+ this.articleDetail.articleContent)
      commentList = res.data.comment.list;
      this.commentList = commentList;
      // if (this.commentList==null || this.commentList.length==0) {

      // } else{
      // console.log('comment'+ this.commentList[0].userName);
      this.commentPages = res.data.comment.pages;
      this.articleCommentNumber = this.articleDetail.articleCommentNumber;
      console.log("number" + this.articleCommentNumber);
      console.log("size" + this.commentPages);
      this.commentTotal = res.data.comment.total;
      // }

      // var date = new Date(res.data.discussList.list[0].createTime);
      // console.log("data"+ formatDate(date, 'yyyy-MM-dd hh:mm'))
      // console.log(items[0].tagName)
    },
    initComment: function(res) {
      //    console.log(res.data.comment.pages)
      this.commentList = res.data.comment.list;
      // console.log('comment'+ commentList[0].discussCommentContent);
      this.commentPages = res.data.comment.pages;
      console.log("size" + this.commentPages);
      // var date = new Date(res.data.discussList.list[0].createTime);
      // console.log("data"+ formatDate(date, 'yyyy-MM-dd hh:mm'))
      // console.log(items[0].tagName)
    },
    commentHandleCurrentChange(val) {
      this.commentPagesNow = val;
      console.log("changeNumber" + val);
      this.ajaxComment(this.userId, this.articleId, val, 10);
    }
  },
  mounted() {
    console.log("statr");
    this.articleId = this.$route.params.articleId;

    this.userId = window.localStorage.getItem("userId");
    console.log(
      "userId: " +
        this.userId +
        " articleId: " +
        this.$route.params.articleId
    );
    console.log("userId: " + this.userId + " articleId: " + this.articleId);
    this.ajaxArticle(this.userId, this.$route.params.articleId);
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh : mm");
    }
  }
};

var items = [
  
];
</script>


<style scope>
#div1 {
  background-color: #f2f2f2;
  height: auto !important;
  height: 100px;
  min-height: 900px;
}
#div2 {
  /* background-color: blueviolet; */
  height: auto !important;
  min-height: 900px;
}
#div3 {
  background-color: #f2f2f2;
  min-height: 50px;
}
#div4 {
  /* background-color: burlywood; */
  min-height: 300px;
}
#div4 {
  /* background-color: aqua; */
  min-height: 300px;
}
#div5 {
  /* background-color: chartreuse; */
  min-height: 300px;
}
#div6 {
  /* background-color: cornflowerblue; */
  min-height: 100px;
}
#div7 {
  /* background-color: cornflowerblue; */
  height: 300px;
}
.sep5 {
  height: 15px;
}
.info {
  border-radius: 10px;
  line-height: 20px;
  padding: 10px;
  margin: 10px;
  /* background-color: #ffffff; */
}
</style>

