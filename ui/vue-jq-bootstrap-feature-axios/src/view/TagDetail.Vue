<template>
<div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class='col-md-12 ' id='div3'>
            <h2>{{tagName}}</h2>
            <strong>{{tagInfo}}</strong>
        </div>
        <div class='col-md-12 ' id='div5'>
            <!-- <button type="button" class="btn btn-default col-md-offset-11">取消关注</button>   -->
            <el-button plain class='pull-right' @click='ajaxUpdateAttentionTags()'>关注/取消关注</el-button>
        </div>
        <div id='div4' class='col-md-12 '>
            <!-- 标签页 -->
            <el-tabs v-model="activeName">
            
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
                    <div v-for="item in discussList">
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
                                            <img :src="item.userPicUrl" width="100%" height="100%" class="img-circle" border="0" align="default" >
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
                    :current-page.sync="discussPageNow"
                    :page-size="10"
                    layout="prev, pager, next, jumper"
                    :total="discussPages">
                    </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="文章" name="article">
                <div id="example-1">
                    <div v-for="item in articleList">
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
                    :current-page.sync="articlePageNow"
                    :page-size="10"
                    layout="prev, pager, next, jumper"
                    :total="articlePages">
                    </el-pagination>
                </div>
            </el-tab-pane>
                 <!-- <div class="block">
                   <el-pagination
                    @current-change="anoymousHandleCurrentChange"
                    :current-page.sync="anoymousNow"
                    :page-size="10"
                    layout="prev, pager, next, jumper"
                    :total="anoymousPages">
                    </el-pagination>
                </div> -->
            </el-tab-pane>
        </el-tabs>
        </div>
       
        </div>


</div>
  
</template>

<script>
import Vue from "vue";
import UE from '../components/Ue.vue';
import qs from 'qs';
import {formatDate} from '../common/date.js';  
var tagId,tagName,tagInfo,userId;
var discussList;
var articleList;
var discussPages,articlePages,discussPageNow,articlePageNow;
var activeName;
    export default{
        data(){
            return{
                items:items,
                tagName: this.tagName,
                tagInfo: this.tagInfo,
                userId: this.userId,
                discussList: discussList,
                articleList: articleList,
                discussPages: discussPages,
                articlePages: articlePages,
                discussPageNow: discussPageNow,
                articlePageNow: articlePageNow,
                activeName: activeName,
            }
        },methods:{
            ajaxArticle(pageNumber){
                var _this = this;
            // console.log('userIDd: '+ userId);
            axios.post('http://localhost:5555/sclp/article/listTagsArticleOrderByTime',qs.stringify(
                    {
                        userId: _this.userId,
                            tagId: _this.tagId,
                            pageNumber: pageNumber,
                            pageSize: '10',
                    }
                )).then(function(res) {
                    
                _this.initArticle(res);
               
            }).catch(function(err) {
                console.log('error'+err);
            });
            },
            handleCurrentChange(val) {
                var _this = this;
                _this.discussPageNow = val;
                _this.ajaxDiscuss(val);
            },
            articleHandleCurrentChange(val) {
                 var _this = this;
                _this.articlePageNow = val;
                _this.ajaxArticle(val);
            },
            initArticle(res){
                 var _this = this;
                _this.articleList = res.data.articleList.list;
                _this.articlePages = res.data.articleList.total;
                _this.articlePageNow = res.data.articleList.pageNum;
            },
            ajaxUpdateAttentionTags(){
                var _this = this;
            // console.log('userIDd: '+ userId);
                axios.post('http://localhost:5555/sclp/tag/updateUserAttentionTag',qs.stringify(
                        {
                            userId: _this.userId,
                            tagId: _this.tagId,
                        }
                    )).then(function(res) {
                        console.log(res.data.message)
                        this.$router.push({ name: "TagDetail", params: { tagId: _this.userId,  tagName: _this.tagName, tagInfo: _this.tagInfo} });
                    // _this.initDiscuss(res);
                
                }).catch(function(err) {
                    console.log('error'+err);
                });
            },
            ajaxDiscuss(pageNumber){
                 var _this = this;
            // console.log('userIDd: '+ userId);
                axios.post('http://localhost:5555/sclp/discuss/listTagsDiscussOrderByTime',qs.stringify(
                        {
                            userId: _this.userId,
                            tagId: _this.tagId,
                            pageNumber: pageNumber,
                            pageSize: '10',
                        }
                    )).then(function(res) {
                        
                    _this.initDiscuss(res);
                
                }).catch(function(err) {
                    console.log('error'+err);
                });
            },
            initDiscuss(res){
                var _this = this;
                _this.discussList = res.data.discussList.list;
                _this.discussPages = res.data.discussList.total;
                _this.discussPageNow = res.data.discussList.pageNum;
            },
            initPage(){
                var _this = this;
                _this.tagId = this.$route.params.tagId;
                _this.tagName = this.$route.params.tagName;
                _this.tagInfo = this.$route.params.tagInfo;
                _this.userId = window.localStorage.getItem("userId");
                _this.discussPageNow = 1;
                _this.articlePageNow = 1;
                _this.activeName = "discuss";
                _this.ajaxDiscuss(_this.discussPageNow);
                _this.ajaxArticle(_this.articlePageNow);
            }
        },mounted(){
            this.initPage();
        },filters: {
            formatDate(time) {
            var date = new Date(time);
            return formatDate(date, "yyyy-MM-dd hh : mm");
            }
        }
    }
     var items = [
        
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
