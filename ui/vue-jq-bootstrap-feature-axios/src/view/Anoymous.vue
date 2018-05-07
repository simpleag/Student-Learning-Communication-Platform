<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class="col-md-9 text-left">
                <h2 class="text-left">{{discussDetail.discussTitle}}</h2>
            </div>
            <div class="col-md-3 text-center text-right">
                <div class='sep5'></div>
                
                <button @click="clickDiscussApprove()" type="button" class="btn btn-default" >{{discussDetail.approveNumber}}个赞同</button>
                <button @click="clickDiscussFavorite()" type="button" class="btn btn-default">{{discussDetail.favoriteNumber}}个收藏</button>
            </div>
        </div>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class="col-md-3" id='div4'>
                <img :src="discussDetail.userPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                <div class='sep5'></div>
                <strong>{{discussDetail.userHonor}}</strong>
                <div class='sep5'></div>
                <strong>{{discussDetail.userName}}</strong>
            </div>
             <div class="col-md-9" id='div5'>
                 <p class='text-justify' v-html='discussDetail.discussContent'></p>
                 <!-- {{discussDetail.discussContent}} -->
            </div>
            
        </div>
        <div class="col-md-12 form-inlin" id='div3'>
            <div class='sep5'></div>
            <p class="text-right">发表于{{discussDetail.createTime | formatDate}} 共<strong>{{discussDetail.discussCommentNumber}}</strong>条回复</p>
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
                                            <a href="wwww.baidu.com">
                                            <img :src="item.userPicUrl" width="100%" class="img-responsive img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-8 pull-left">
                                        <p class="text-left"><strong>{{item.userName}}</strong>发表于: {{item.createTime | formatDate}}</strong></p>
                                    </span>
                                    <div class="sep5"></div>
                                    <p class="text-left">
                                    <span >
                                        <strong v-html="item.discussCommentContent"></strong>
                                    </span>
                                    </p>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                       <strong>#{{item.discussListNumber}}</strong>
                                       <p></p>
                                        
                                    </span>
                                    <span>
                                    <button @click="clickCommentApprove(item.discussCommentId)" type="button" class="btn btn-default">{{item.approveNumber}}个赞同</button>
                                    <button @click="clickReplayComment(item.userName, item.discussCommentAuthorId)" type="button" class="btn btn-default">回复评论</button>
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
                <UE :defaultMsg=defaultMsg :config=config ref="ue"></UE>
                </div>
            </div>
             
        </div>
       
        
    </div>
   </div>
</template>

<script>
import Vue from "vue";
import UE from '../components/Ue.vue';
import qs from 'qs';
import {formatDate} from '../common/date.js';  
var commentList = [];
var discussDetail;
var commentPages;
var pageSize = 10;
var commentPagesNow = 1;
var defaultMsg = '在这里输入回复'
var replayUserId = 0;
var discussCommentNumber = 0;
var commentTotal = 0;
var userId;
export default {
  components: {UE,
  formatDate:formatDate},
  data() {
    return {
      items: items,
      defaultMsg: defaultMsg,
        config: {
          initialFrameWidth: null,
          initialFrameHeight: 350,
          maximumWords:255
        },
        discussDetail: discussDetail,
        commentList: commentList,
        commentPages: commentPages,
        commentPagesNow: commentPagesNow,
        pageSize:pageSize,
        commentTotal:commentTotal,
        replayUserId: replayUserId
    
      
    };
  },
  methods: {
       getUEContent() {
        let content = this.$refs.ue.getUEContent();
        this.$notify({
          title: '获取成功，可在控制台查看！',
          message: content,
          type: 'success'
        });
        console.log(content)
      },
      
      clickReplayComment(userName, userId){
          this.defaultMsg = '回复'+userName+': ';
          this.replayUserId = userId;
          console.log('set')
          
          var ueTemp = this.$refs.ue;
          console.log(ueTemp.setUEContent(this.defaultMsg));
          
      },

      clickCommentApprove(discussCommentId){
          console.log('atteath'+discussCommentId)
        //   var item;
            // console.log('length'+this.commentList.length)
          for(var i=0;i<this.commentList.length;i++) {
              var item = this.commentList[i];
            //   console.log('atteath2'+item.discussCommentId)
              if (item.discussCommentId == discussCommentId) {
                  this.ajaxApproveDiscussComment(this.userId, discussCommentId);
                  if (item.userApproveType==null || item.userApproveType==0) {
                      item.userApproveType=1;
                      item.approveNumber += 1;
                      
                  } else {
                      item.userApproveType = 0;
                      item.approveNumber -= 1;
                  }
              }
          }
      },
      clickDiscussApprove(){
          if (this.discussDetail.userApproveType==null || this.discussDetail.userApproveType==0) {
              this.discussDetail.approveNumber += 1;
              this.discussDetail.userApproveType = 1;
          }else {
              this.discussDetail.approveNumber -= 1;
              this.discussDetail.userApproveType = null;
          }
          this.ajaxApproveDiscuss(this.userId, this.$route.params.discussId);
      },
      clickDiscussFavorite(){
          
          if (this.discussDetail.userFavoriteType==null || this.discussDetail.userFavoriteType==0) {
              this.discussDetail.favoriteNumber += 1;
              this.discussDetail.userFavoriteType = 1;
          }else {
              this.discussDetail.favoriteNumber -= 1;
              this.discussDetail.userFavoriteType = null;
          }
          this.ajaxFavoriteDiscuss(this.userId, this.$route.params.discussId);
      },
      ajaxDiscuss: function(userId, articleId) {
            var _this = this;
            console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/discuss/detail',qs.stringify(
                    {
                        userId: userId,
                        articleId: articleId
                    }
                )).then(function(res) {
                    
                _this.initDiscuss(res);
               
            }).catch(function(err) {
                console.log('error'+err);
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
        this.discussCommentNumber = this.discussCommentNumber+1
        this.ajaxCreateDiscussComment(this.userId, content,this.discussCommentNumber, this.replayUserId , this.$route.params.discussId)
        
      },
      ajaxCreateDiscussComment: function(userId, content, number, replayUserId, discussId) {
            var _this = this;
            console.log('userId: '+ userId);
            console.log('conetent: '+ content);
            console.log('number: '+ number);
            console.log('replayUserId: '+ replayUserId);
            console.log('discussId: '+ discussId);
            axios.post('http://localhost:5555/sclp/comment/createDiscussComment',qs.stringify(
                    {
                        userId: userId,
                        discussCommentAuthorId: userId,
                        discussCommentContent: content,
                        discussListNumber: number,
                        discussReplayUserid: replayUserId,
                        discussId: discussId,
                    }
                )).then(function(res) {
                console.localhost(res.data.code+" "+res.data.message)
                // _this.initDiscuss(res);
            //    this.ajaxComment(this.$route.params.userId, this.$route.params.discussId,1,10)
            }).catch(function(err) {
                console.log('error'+err);
            });

           },
           ajaxFavoriteDiscuss: function(userId, articleId) {
            var _this = this;
            console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/discuss/favorite',qs.stringify(
                    {
                        userId: userId,
                        articleId: articleId
                    }
                )).then(function(res) {
                    console.log(res.data.code)
               
            }).catch(function(err) {
                console.log('error'+err);
            });

           },
           ajaxApproveDiscuss: function(userId, articleId) {
            var _this = this;
            console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/discuss/approve',qs.stringify(
                    {
                        userId: userId,
                        articleId: articleId
                    }
                )).then(function(res) {
                    console.log(res.data.code)
               
            }).catch(function(err) {
                console.log('error'+err);
            });

           },
           ajaxApproveDiscussComment: function(userId, commentId) {
            var _this = this;
            console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/comment/approveComment',qs.stringify(
                    {
                        userId: userId,
                        commentId: commentId,
                        type: 2
                    }
                )).then(function(res) {
                    console.log(res.data.code+ res.data.message)
               
            }).catch(function(err) {
                console.log('error'+err);
            });

           },
           ajaxComment: function(userId, articleId,pageNumber,pageSize) {
            var _this = this;
            // console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/comment/listComment',qs.stringify(
                    {
                        userId: userId,
                        targetId: articleId,
                        pageNumber: pageNumber,
                        pageSize: pageSize,
                        type: 1
                    }
                )).then(function(res) {
                    
                _this.initComment(res);
               
            }).catch(function(err) {
                console.log('error'+err);
            });

           },
            initDiscuss: function(res) {
                this.discussDetail = res.data.discuss;
                console.log('discuss: '+ this.discussDetail.discussContent)
                this.commentList = res.data.comment.list;
                // if (this.commentList==null || this.commentList.length==0) {

                // } else{
                    // console.log('comment'+ this.commentList[0].userName);
                    this.commentPages = res.data.comment.pages;
                    this.discussCommentNumber = this.discussDetail.discussCommentNumber;
                    console.log('number'+ this.discussCommentNumber);
                    console.log('size'+ this.commentPages);
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
                console.log('size'+ this.commentPages);
                // var date = new Date(res.data.discussList.list[0].createTime);
                // console.log("data"+ formatDate(date, 'yyyy-MM-dd hh:mm'))
                // console.log(items[0].tagName)
                
                
                
            },
            commentHandleCurrentChange(val) {
                this.commentPagesNow = val;
                console.log('changeNumber'+ val)
                this.ajaxComment(this.userId, this.$route.params.discussId,val,10)
            }
  },
  mounted() {
      console.log('statr')
      var id = this.$route.params.discussId;
      console.log(id)
      this.userId = window.localStorage.getItem("userId");
    //   console.log(this.$route.params.userId)
      this.ajaxDiscuss(this.userId, this.$route.params.discussId);
  },filters: {
        formatDate(time) {
        var date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh : mm');
        }
  },
};

var items = [
  {
    discussId: "Foo",
    discussTitle: "一直不会微积分，今后生活会比别人困难吗？",
    authorId: "1",
    userName: "用户1",
    authorPicUrl:
      "https://cdn.v2ex.com/avatar/c2db/c77d/146640_normal.png?m=1452582980",
    createTime: "20180410 12:05:00",
    approveNumber: "0",
    commentNumber: "0",
    TagId: "TagId",
    TagName: "微积分",
    Info:
      "微积分（Calculus）是高等数学中研究函数的微分（Differentiation）、积分(Integration)以及有关概念和应用的数学分支。它是数学的一个基础学科。"
  }
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
  min-height: 30px;
}
.sep5 {
  height: 15px;
}
.info{
    border-radius: 10px;
    line-height: 20px;
    padding: 10px;
    margin: 10px;
    /* background-color: #ffffff; */
  }
</style>

