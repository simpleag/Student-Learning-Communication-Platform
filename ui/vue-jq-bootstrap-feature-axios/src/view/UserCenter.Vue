<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-8 col-md-offset-2 form-inlin" id='div2'>
        <div class="col-md-12 text-center" id='div3'>
            <img :src="targetUser.userPicUrl" class="img-circle  center-block" height='100%'width="200px"alt="Responsive image">
        </div>
        <div class="col-md-12" id='div4'>
            <h1>{{targetUser.userName}}</h1>
        </div>
        <div class="col-md-12" id='div5'>
            <strong>{{targetUser.userIntroduce}}</strong>
        </div>
        <div class="col-md-12" id='div6'>
            <div class='sep5'></div>
            <p>获得<strong>{{targetUser.userGetApproveNumber}}</strong>条赞 
            <!-- <strong>{{targetUser.userFollowNumber}}</strong>个关注者</p> -->
            <div class='sep5'></div>
            <el-button type="success" plain :disabled='isAttention || !notIsTargetUser' @click='ajaxAttentionUser()'>关注用户</el-button>
            <el-button type="success" plain :disabled='!isAttention || !notIsTargetUser'@click='ajaxUnAttentionUser()'>取消关注用户</el-button>
            <div class='sep5'></div>
            <!-- <button type="button" class="btn btn-default btn-sm">修改个人主页</button> -->
        </div>
        <div class="col-md-12" id='div7'>
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
            <el-tab-pane label="动态" name="active">
              <div v-for="item in activites">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:150px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.targetId, item.activeType)'>{{item.activeContent}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-button type="text" @click='moreActive()'>更早的动态</el-button>
                </div>
              
            </el-tab-pane>
            <el-tab-pane label="讨论" name="discuss">
              <span slot="label"> <strong>{{targetUser.userDiscussNumber}}</strong> 讨论</span>
              <div v-for="item in discuss">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.discussId, 3)'>{{item.discussTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="回复" name="comment">
              <span slot="label"> <strong>{{targetUser.userCommentNumber}}</strong> 回复</span>
               <el-tabs v-model="commentName" @tab-click="commentHandleClick" tab-position="left" >
                  <el-tab-pane label="讨论回复" name="discussComment">
                    <div v-for="item in comments">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <!-- <el-button type="text" @click='jump(item.discussId, 3)'>{{item.discussTitle}}</el-button> -->
                                        <p class='text-justify' v-html='item.discussCommentContent'></p>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <button @click="jump(item.discussId,4)" type="button" class="btn btn-default" >查看</button> 
                                    <!-- <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button>  -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="commentHandleClick"
                      :current-page.sync="commentPageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="commentPages">
                      </el-pagination>
                  </div>
                  </el-tab-pane>
                  <el-tab-pane label="文章评论" name="articleComment">
                    <div v-for="item in comments">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <!-- <el-button type="text" @click='jump(item.discussId, 3)'>{{item.discussTitle}}</el-button> -->
                                        <p class='text-justify' v-html='item.articleCommentContent'></p>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <button @click="jump(item.articleId,1)" type="button" class="btn btn-default" >查看</button> 
                                    <!-- <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button>  -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="commentHandleClick"
                      :current-page.sync="commentPageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="commentPages">
                      </el-pagination>
                  </div>
                  </el-tab-pane>
                  <el-tab-pane label="匿名评论" name="anoymousComment">
                    <div v-for="item in comments">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <!-- <el-button type="text" @click='jump(item.discussId, 3)'>{{item.discussTitle}}</el-button> -->
                                        <p class='text-justify' v-html='item.anoymousCommentContent'></p>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <button @click="jump(item.anoymousId,9)" type="button" class="btn btn-default" >查看</button> 
                                    <!-- <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button>  -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="commentHandleClick"
                      :current-page.sync="commentPageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="commentPages">
                      </el-pagination>
                  </div>
                  </el-tab-pane>
                </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="文章" name="article">
              <span slot="label"> <strong>{{targetUser.userArticleNumber}}</strong> 文章</span>
              <div v-for="item in discuss">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.articleId, 1)'>{{item.articleTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <!-- <el-tab-pane label="文章评论" name="articleComment">

            </el-tab-pane> -->
            <el-tab-pane label="匿名讨论" name="anoymous" :disabled='notIsTargetUser'>
              <span slot="label"> <strong>{{targetUser.userAnoymouseNumber}}</strong> 匿名讨论</span>
              <div v-for="item in discuss">
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
                                            <img :src="targetUser.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.anoymousId, 1)'>{{item.anoymousTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <!-- <el-tab-pane label="匿名评论" name="anoymousComment">匿名评论</el-tab-pane> -->
            <el-tab-pane label="关注" name="attention">
              <span slot="label"> <strong>{{targetUser.userAttentionNumber}}</strong> 关注</span>
              <div v-for="item in attentionUsers">
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
                                             <router-link :to="{ name: 'UserCenter', params : {targetUserId: item.userId}}">
                                            <img :src="item.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                             </router-link>
                                            <!-- </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.userId, 13)'>{{item.userName}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <!-- <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p> -->
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="粉丝" name="follow">
              <span slot="label"> <strong>{{targetUser.userFollowNumber}}</strong> 粉丝</span>
              <div v-for="item2 in followUsers">
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
                                            <router-link :to="{ name: 'UserCenter', params : {targetUserId: item2.userId}}">
                                            <img :src="item2.userPicUrl" style="width:100%; height:130px;" class="img-circle" border="0" align="default" >
                                            </router-link>
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                           
                                        <el-button type="text" @click='jump(item2.userId, 13)'>{{item2.userName}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <!-- <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p> -->
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="兴趣" name="tags">
              <!-- <el-tabs v-model="activeName" type="card" @tab-click="handleClick"> -->
              <el-tabs v-model="tagName" tab-position="left" @tab-click="tagHandClick">
                <el-tab-pane label="关注的标签" name="attentionTags">
                  <div v-for="item in attentionTags">
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
                                            <el-button type="text" @click='jump(item.tagId, 11)'>{{item.tagName}}</el-button>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                          <strong>{{item.tagInfo}}</strong>
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <!-- <el-button type="text" @click='jump(item.userId, 10)'>{{item.userName}}</el-button> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <!-- <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p> -->
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>-->
                                    <button @click="clickTag(item.tagId)" type="button" class="btn btn-default">取消收藏</button> 
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
                </el-tab-pane>
                <el-tab-pane label="未关注的标签" name="unAttentionTags">
                  <div v-for="item in unAttentionTags">
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
                                            <el-button type="text" @click='jump(item.tagId, 11)'>{{item.tagName}}</el-button>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                          <strong>{{item.tagInfo}}</strong>
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <!-- <el-button type="text" @click='jump(item.userId, 10)'>{{item.userName}}</el-button> -->
                                    </span>
                                    <div class="sep5"></div>
                                    <!-- <span>
                                        <strong>{{targetUser.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p> -->
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>-->
                                    <button @click="clickTag(item.tagId)" type="button" class="btn btn-default">收藏</button> 
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="收藏" name="favoriteArticle">
              <span slot="label"> <strong>{{targetUser.userFavoriteNumber}}</strong> 收藏</span>
              <el-tabs v-model="favoriteName" @tab-click="favoriteHandleClick" tab-position="left" >
                <el-tab-pane label="收藏的文章" name="article">
                  <div v-for="item in favoriteList">
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
                                            <img :src="item.userPicUrl" style="width:100%; height:120px;" class="img-circle" border="0" align="default" >
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.articleId, 3)'>{{item.articleTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userName}}</strong>发表于:{{item.createTime | formatDate}} </strong>来自<strong>{{item.tagName}}</strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
                </el-tab-pane>
                <el-tab-pane label="收藏的讨论" name="discuss">
                  <div v-for="item in favoriteList">
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
                                            <img :src="item.userPicUrl" style="width:100%; height:120px;" class="img-circle" border="0" align="default" >
                                            </a>
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.discussId, 4)'>{{item.discussTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        <strong>{{item.userName}}</strong>发表于:<strong>{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
                </el-tab-pane>
                <el-tab-pane label="收藏的匿名讨论" name="anoymous" :disabled='notIsTargetUser'>
                  <div v-for="item in favoriteList">
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
                                            <!-- <img :src="targetUser.userPicUrl" width="100%" height="100%" class="img-circle" border="0" align="default" >
                                            </a> -->
                                        </div>
                                    </td>
                                    <td class="col-md-6">
                                        <span class="item_title">
                                            <!-- 带查询参数，下面的结果为 /register?plan=private -->
                                        <!-- <router-link :to="{ name: 'Discuss', params : {discussId: item.discussId, userId: userId}}">{{item.discussTitle}}</router-link> -->
                                        <!-- <a href="/Home/Discuss?"> {{item.discussTitle}}</a> -->
                                        <el-button type="text" @click='jump(item.anoymousId, 6)'>{{item.anoymousTitle}}</el-button>
                                    </span>
                                    <div class="sep5"></div>
                                    <span>
                                        发表于:{{item.createTime | formatDate}} </strong>
                                        <p></p>
                                        
                                    </span>

                                    </td>
                                    <td class="col-md-3">
                                        <div class="sep5"></div>
                                   <span>
                                        <!-- <el-tag type="danger"><strong>{{item.discussCommentNumber}}</strong>条回复</el-tag> -->
                                        <!-- <strong>{{item.discussCommentNumber}}</strong>条回复 -->
                                        <p></p>
                                    </span>
                                    <span>
                                    <!-- <el-tag type="success">{{item.approveNumber}}个赞同</el-tag> -->
                                    <p></p>
                                    <!-- <el-tag type="warning">{{item.favoriteNumber}}个收藏</el-tag> -->
                                    <!-- <button @click="clickDiscussApprove(item.discussId,1)" type="button" class="btn btn-default" >{{item.approveNumber}}个赞同</button>
                                    <button @click="clickDiscussFavorite(item.discussId,1)" type="button" class="btn btn-default">{{item.favoriteNumber}}个收藏</button> -->
                                    </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="block">
                      <el-pagination
                      @current-change="HandleCurrentChange"
                      :current-page.sync="pageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="pages">
                      </el-pagination>
                </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <!-- <el-tab-pane label="收藏讨论" name="favoriteDiscuss">收藏</el-tab-pane>
            <el-tab-pane label="收藏匿名讨论" name="favoriteAnoymous">收藏</el-tab-pane> -->
            <!-- <el-tab-pane label="私信" name="message" :disabled='notIsTargetUser'>私信</el-tab-pane> -->
            <el-tab-pane label="消息" name="info" :disabled='notIsTargetUser'>
              <el-table
                :data="infos"
                style="width: 100%">
                <el-table-column
                  prop="intoContent"
                  label="内容"
                  width="700px">
                </el-table-column>
                <!-- <el-table-column
                  prop="name"
                  label="姓名"
                  width="180">
                </el-table-column>-->
                
                 <el-table-column
                    fixed="right"
                    label="操作"
                    width="300px">
                    <template slot-scope="scope">
                      <el-button @click="infoTableHandleClick(scope.row)" type="text" size="small">查看</el-button>
                    </template>
                  </el-table-column>
              </el-table>
              <div class="block">
                      <el-pagination
                      @current-change="infoHandleCurrentChange"
                      :current-page.sync="infoPageNow"
                      :page-size="10"
                      layout="prev, pager, next, jumper"
                      :total="infoPages">
                      </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="设置" name="setting" :disabled='notIsTargetUser'>
              <el-tabs tab-position="left" >
                <el-tab-pane label="修改头像">
                  <span>选择一张图片上传作为头像</span>
                 <form enctype="multipart/form-data" method="post">
                  <input type="file" @change="getFile($event)">
                  <button class="button button-primary button-pill button-small" @click="uploadPic($event)">提交</button>
                </form>
                </el-tab-pane>
                <el-tab-pane label="修改基本信息">
                  <el-input placeholder="登录id" v-model="targetUser.userLoginId" :disabled="true">
                    <template slot="prepend" style="width: 20%">登录id</template>
                  </el-input>
                  <p></p>
                  <el-input placeholder="用户名" v-model="targetUser.userName">
                    <template slot="prepend" style="width: 20%">用户名</template>
                  </el-input>
                  <p></p>
                  <el-input placeholder="普通头衔" v-model="targetUser.userHonor">
                    <template slot="prepend" >普通头衔</template>
                  </el-input>
                  <p></p>
                  <el-input placeholder="匿名头衔" v-model="targetUser.userAnonymouseHonor">
                    <template slot="prepend" >匿名头衔</template>
                  </el-input>
                  <p></p>
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入介绍"
                    v-model="targetUser.userIntroduce">
                  </el-input>
                  <p></p>
                  <el-button type="primary" @click="updateUserInfo()">提交修改</el-button>
                </el-tab-pane>
                <el-tab-pane label="帐号安全">
                  <el-input type='password' placeholder="旧密码" v-model="oldPwd">
                    <template slot="prepend" style="width: 20%">旧密码</template>
                  </el-input>
                  <p></p>
                  <el-input  type='password' placeholder="新密码" v-model="newPwd">
                    <template slot="prepend" style="width: 20%">新密码</template>
                  </el-input>
                  <p></p>
                  <el-input  type='password' placeholder="新密码" v-model="newPwd2">
                    <template slot="prepend" >新密码</template>
                  </el-input>
                  <p></p>
                  <el-button type="primary" @click="updateUserPwd()">修改密码</el-button>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
        </el-tabs>
        </div>    
        
    </div>
   </div>
</template>

<script>
import Vue from "vue";
import qs from "qs";
import { formatDate } from "../common/date.js";
var targetUserId;
var targetUser;
var userId;
var activites;
var discuss;
var discussComments;
var comments;
var article;
var articleComment;
var anoymous;
var anoymousComment;
var attentionUsers;
var followUsers;
var activeName='';
var pageNow;
var commentPageNow;
var pages;
var commentPages;
var isAttention;
var notIsTargetUser;
var commentName = 'discussComment';
var attentionTags;
var unAttentionTags;
var infos,infoPageNow,infoPages;
var messages;
var tagName;
var favoriteName;
var favoriteList;
var file = '';
var oldPwd,newPwd,newPwd2;
export default {
  components: {
    formatDate: formatDate
  },
  data() {
    return {
      items: items,
      targetUserId: this.targetUserId,
      targetUser: this.targetUser,
      userId: this.userId,
      activites: this.activites,
      discuss: this.discuss,
      discussComments: this.discussComments,
      article: this.article,
      articleComment: this.articleComment,
      anoymous: this.anoymous,
      anoymousComment: this.anoymousComment,
      attentionUsers: this.attentionUsers,
      followUsers: this.followUsers,
      activeName: this.activeName,
      pageNow: this.pageNow,
      pages: this.pages,
      commentName: this.commentName,
      commentPageNow: this.commentPageNow,
      commentPages: this.commentPages,
      comments: this.comments,
      isAttention: this.isAttention,
      notIsTargetUser: this.notIsTargetUser,
      attentionTags: this.attentionTags,
      unAttentionTags: this.unAttentionTags,
      infos: this.infos,
      messages: this.messages,
      tagName: this.tagName,
      favoriteName: this.favoriteName,
      favoriteList: this.favoriteList,
      infoPageNow: this.infoPageNow,
      infoPages: this.infoPages,
      file: this.file,
      oldPwd: this.oldPwd,
      newPwd: this.newPwd,
      newPwd2: this.newPwd2
    };
  },
  methods: {
    getFile(event) {
      var _this = this;
      console.log('get');
        _this.file = event.target.files[0];
        console.log(_this.file);
        console.log(_this.file.size)
        // if (this.file.size>1048576) {
        //   _this.errorNotice('文件过大');
        // }
      },
      updateUserInfo(){
        var _this = this;
        console.log('updateUserInfo')
        console.log(_this.targetUser.userName)
        console.log(_this.targetUser.userHonor)
        console.log(_this.targetUser.userAnonymouseHonor)
        console.log(_this.targetUser.userIntroduce)
        _this.ajaxUpdateUserInfo(_this.targetUser.userName, _this.targetUser.userHonor, _this.targetUser.userAnonymouseHonor
                                , _this.targetUser.userIntroduce);
        window.location.reload();
      },
      updateUserPwd(){
        var _this = this;
        console.log('updateUserPwd')
        console.log(_this.oldPwd)
        console.log(_this.newPwd)
        console.log(_this.newPwd2)
        if (_this.oldPwd == _this.newPwd) {
          console.log('一致')
          _this.errorNotice('新旧密码不能一致')
          
        } else if (_this.newPwd == _this.newPwd2) {
          console.log('OK')
          _this.ajaxUpdatePwd();
          window.location.reload();
        } else {
          console.log('不一致')
           _this.errorNotice('新密码不一致')
        }
      },
      uploadPic(event){
        //避免自动提交表单
        event.preventDefault();
        var _this = this;
        if (this.file.size>1000000) {
          console.log('文件过大');
          _this.errorNotice('文件过大');
        } else {
          _this.ajaxUploadPic(event);
        }
      },
      ajaxAttentionUser(){
        var _this = this
        console.log('ajaxUpdatePwd')
          axios
        .post(
          "http://localhost:5555/sclp/user/attentionUser",
         qs.stringify({
            userId: _this.userId,
            targetUserId: _this.targetUser.userId,
          })
      
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.data.message)
          window.location.reload();
      
        })
        .catch(function(err) {
          console.log('error')
          console.log("error" + err);
        });
      },
      ajaxUnAttentionUser(){
        var _this = this
        console.log('ajaxUpdatePwd')
          axios
        .post(
          "http://localhost:5555/sclp/user/unAttentionUser",
         qs.stringify({
            userId: _this.userId,
            targetUserId: _this.targetUser.userId,
          })
  
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.data.message)
          _this.successNotice(res.data.message)
          window.location.reload();
      ;
        })
        .catch(function(err) {
          console.log('error')
          console.log("error" + err);
          _this.errorNotice(err.data.message)
        });
      },
      ajaxUpdatePwd(){
        var _this = this
        console.log('ajaxUpdatePwd')
          axios
        .post(
          "http://localhost:5555/sclp/user/changePassword",
         qs.stringify({
            userId: _this.userId,
            orignPwd: _this.oldPwd,
            newPwd1: _this.newPwd,
            newPwd2: _this.newPwd2
          })
        //  config
        //  {headers: header}
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.data.message)
          // _this.initPage()
          // _this.initTags(res);
        })
        .catch(function(err) {
          console.log('error')
          console.log("error" + err);
        });
      },
      ajaxUpdateUserInfo(userName, userHonor, userAnonymouseHonor, userIntroduce){
        var _this = this
        console.log('ajaxUpdateUserInfo')
          axios
        .post(
          "http://localhost:5555/sclp/user/updateUserInfos",
         qs.stringify({
            userId: _this.userId,
            userName: userName,
            userHonor: userHonor,
            userAnoymouseHonor: userAnonymouseHonor,
            introduce: userIntroduce
          })
        //  config
        //  {headers: header}
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.message)
          // _this.initPage()
          // _this.initTags(res);
        })
        .catch(function(err) {
          console.log('error')
          console.log("error" + err);
        });
      },
      ajaxUploadPic(event){
      // var file = event.target.files
      console.log("ajaxUploadPic")
      var _this = this;
      event.preventDefault();
      let parms = new URLSearchParams();
      // let fd = new window.Formdata()
      // fd.append('key', file, 'fileName')+
       let fd = new FormData()
      
      console.log("file")
      console.log(this.file)
      console.log("fileForm"+ fd)
      parms.append('userId', this.userId);
      parms.append('file',this.file)
      console.log('name: ', this.file.name);
      // console.log("test: "+ formData.getAll)
      fd.append('userId', this.userId)
      fd.append('file',this.file,this.file.name)
      console.log("test: "+ fd.get('userId'))
      let config = {
        headers: {'Content-Type': "Multipart/form-data"}
      }
      let header = {
			'content-type': "multipart/form-data"
       }
       
      axios
        .post(
          "http://localhost:8093/article/uploadPicFile",
         fd
        //  config
        //  {headers: header}
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.message)
          _this.ajaxUpdataUserPic(res);
          // _this.initTags(res);
        })
        .catch(function(err) {
          console.log('error')
          // _this.errorNotice('文件过大');
          console.log("error" + err);
        });
    },
    ajaxUpdataUserPic(res){
      var _this = this;
      var picUrl = res.data.imgUrl;;
      axios
        .post(
          "http://localhost:5555/sclp/user/updateUserPicUrl",
         qs.stringify({
            userId: this.userId,
            newUserPicUrl: picUrl
          })
        //  config
        //  {headers: header}
        )
        .then(function(res) {
          console.log('successs')
          console.log(res.message)
          // _this.initPage()
          // _this.initTags(res);
        })
        .catch(function(err) {
          console.log('error')
          console.log("error" + err);
        });
    },
    infoTableHandleClick(row) {
        console.log(row.infoId);
        var _this = this;
        _this.jump(row.infoTargetId, row.infoType);
      },
    moreActive(){
      this.pageNow += 1;
      console.log("more"+ this.pageNow);
      this.ajaxUser(this.userId, this.targetUserId, this.pageNow)
    },
    clickTag(tagId){
      this.ajaxUpdateAttentionTag(this.userId, tagId);
      window.location.reload();
    },
    infoHandleCurrentChange(val) {
      var _this = this;
      _this.infoPageNow = val;
      _this.ajaxInfo(_this.userId, val);
    },
    HandleCurrentChange(val){
      
      var _this = this;
      _this.pageNow = val;
      if (this.activeName == "article") {
       console.log(this.activeName);
       _this.ajaxUsersArticle(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.activeName == "discuss") {
        console.log(this.activeName);
         _this.ajaxUsersDiscuss(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.activeName == "anoymous") {
       console.log(this.activeName);
      } else if (this.activeName == "comment") {
        console.log(this.activeName);
        this.commentName = 'discussComment';
        _this.ajaxUsersComment(_this.userId, _this.targetUserId,_this.commentPageNow,1)
      } else if (this.activeName == "active") {
        this.ajaxUser(this.userId, this.targetId, this.pageNow)
        console.log(this.activeName);
      } else if (this.activeName == "articleComment") {
       console.log(this.activeName);
        this.ajaxUserAnoymous(anoymousNow);
      } else if (this.activeName == "anoymousComment") {
       console.log(this.activeName);
       
      } else if (this.activeName == "favoriteArticle") {
        console.log(this.activeName);
      } else if (this.activeName == "favoriteDiscuss") {
       console.log(this.activeName);
      } else if (this.activeName == "tags") {
        console.log(this.activeName);
        this.ajaxUserTags(_this.userId, _this.targetUserId,_this.pageNow);
      } else if (this.activeName == "favoriteAnoymous") {
       console.log(this.activeName);
      } else if (this.activeName == "message") {
        console.log(this.activeName);
      } else if (this.activeName == "info") {
        console.log(this.activeName);
      } else if (this.activeName == "setting") {
        console.log(this.activeName);
      } else if (this.activeName == "attention") {
        console.log(this.activeName);
        this.ajaxUserAttentions(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.activeName == "follow") {
        console.log(this.activeName);
        this.ajaxUserFollow(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.activeName == "favorite") {
        console.log(this.activeName);
      }
    },
    commentHandleClick(tab, event){
      var _this = this;
      _this.commentName = tab.name;
      if (_this.commentName == 'discussComment') {
        console.log(_this.commentName)
         _this.ajaxUsersComment(_this.userId, _this.targetUserId,_this.commentPageNow,1)
      } else if (_this.commentName == 'articleComment') {
        console.log(_this.commentName)
        _this.ajaxUsersComment(_this.userId, _this.targetUserId,_this.commentPageNow,2)
      } else if (_this.commentName == 'anoymousComment') {
        console.log(_this.commentName)
        _this.ajaxUsersComment(_this.userId, _this.targetUserId,_this.commentPageNow,3)
      }
    },
    tagHandClick(tab, event){
      var _this = this;
      _this.tagName = tab.name;
      this.ajaxTag(this.userId, this.targetUserId,pageNow)
      if (this.tagName == "attentionTags") {
        console.log(this.tagName);
        // this.ajaxUserFollow(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.tagName == "unAttentionTags") {
        console.log(this.tagName);
        // this.ajaxUserFollow(_this.userId, _this.targetUserId,_this.pageNow)
      }
    },
    favoriteHandleClick(tab, event){
      var _this = this;
      console.log('favoriteHandleClick');
      _this.favoriteName = tab.name;
      if (_this.favoriteName  == 'article') {
        console.log(_this.favoriteName);
        _this.ajaxUsersFavoriteArticle(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (_this.favoriteName  == 'discuss') {
        console.log(_this.favoriteName);
         _this.ajaxUsersFavoriteDiscuss(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (_this.favoriteName  == 'anoymous'){
        console.log(_this.favoriteName);
         _this.ajaxUsersFavoriteAnoymous(_this.userId, _this.targetUserId,_this.pageNow)
      }
    },
    handleClick(tab, event) {
      var _this = this;
      _this.activeName = tab.name;
      console.log("tab:" + tab.name + "+" + event);
      if (tab.name == "article") {
       console.log(tab.name);
       _this.ajaxUsersArticle(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "discuss") {
        console.log(tab.name);
        _this.ajaxUsersDiscuss(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "anoymous") {
        console.log(tab.name);
        _this.ajaxUsersAnoymous(_this.userId, _this.targetUserId,_this.pageNow)
       
      } else if (tab.name == "commentName") {
        console.log(tab.name);
        _this.ajaxUsersDiscussComment(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "active") {
        console.log(tab.name);
        _this.ajaxUser(_this.userId, _this.targetUserId,_this.pageNow)
        
      } else if (tab.name == "articleComment") {
       console.log(tab.name);
        _this.ajaxUsersArticleComment(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "anoymousComment") {
       console.log(tab.name);
      //  _this.ajaxUsersAnoymousComment(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "favoriteArticle") {
        console.log(tab.name);
        _this.ajaxUsersFavoriteArticle(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "favoriteDiscuss") {
       console.log(tab.name);
       _this.ajaxUserFavoriteDiscuss(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "tags") {
        console.log(tab.name);
        this.ajaxUserTags(_this.userId, _this.targetUserId,_this.pageNow);
      } else if (tab.name == "favoriteAnoymous") {
       console.log(tab.name);
       _this.ajaxUserFavoriteAnoymous(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (tab.name == "message") {
        console.log(tab.name);

      } else if (tab.name == "info") {
        console.log(tab.name);
        _this.ajaxInfo(this.userId, _this.pageNow)
      } else if (tab.name == "setting") {
        console.log(tab.name);
      } else if (this.activeName == "attention") {
        console.log(this.activeName);
        this.ajaxUserAttentions(_this.userId, _this.targetUserId,_this.pageNow)
      } else if (this.activeName == "follow") {
        console.log(this.activeName);
        this.ajaxUserFollow(_this.userId, _this.targetUserId,_this.pageNow)
      } 
    },
    jump(targetId, type){
      //13 为关注用户
      window.localStorage.setItem("userId", this.userId);
      console.log('jump')
      console.log('targetId'+ targetId)
      console.log('type'+ type)
      if (type <=3 || type==7) {
        this.$router.push({ name: "Article", params: { articleId: targetId, userId: this.userId } });
      } else if (type<=4 || type==8) {
        this.$router.push({ name: "Discuss", params: { discussId: targetId, userId: this.userId } });
      } else if (type <=6 || type==9) {
        this.$router.push({ name: "Anoymous", params: { anoymousId: targetId, userId: this.userId } });
      } else if (type == 10) {
        this.$router.push({ name: "Article", params: { articleId: targetId, userId: this.userId } });
      } else if (type == 11) {
        this.$router.push({ name: "Discuss", params: { discussId: targetId, userId: this.userId } });
      } else if (type == 12) {
        this.$router.push({ name: "Article", params: { articleId: targetId, userId: this.userId } });
      } else if(type == 13) {
        this.$router.push({ name: "UserCenter", params: { targetUserId: targetId, userId: this.userId } });
        window.location.reload();
      }
      
      // this.location.reload();
    },
    ajaxUpdateAttentionTag(userId, tagId){
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/tag/updateUserAttentionTag",
          qs.stringify({
            userId: userId,
            tagId: tagId
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.ajaxTag(this.userId, this.targetUserId,pageNow)
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersComment(userId, targetUserId,commentPageNow, type){
      var _this = this;
      console.log('ajaxUserscomment')
      console.log('ajaxUserscomment'+ targetUserId)
      console.log('ajaxUserscomment'+ userId)
      console.log('ajaxUserscomment'+ commentPageNow)
       console.log('ajaxUserscomment'+ type)
      axios
        .post(
          "http://localhost:5555/sclp/comment/listUsersCommentByType",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: commentPageNow,
            pageSize: 10,
            type: type
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiComment(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersFavoriteDiscuss(userId, targetUserId, pageNumber){
      var _this = this;
      console.log('ajaxUsersFavoriteDiscuss')
      console.log('ajaxUsersFavoriteDiscuss'+ targetUserId)
      console.log('ajaxUsersFavoriteDiscuss'+ userId)
      console.log('ajaxUsersFavoriteDiscuss'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/discuss/userFavorite",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiFavoriteDiscuss(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersFavoriteAnoymous(userId, targetUserId, pageNumber){
      var _this = this;
      console.log('ajaxUsersFavoriteAnoymous')
      console.log('ajaxUsersFavoriteAnoymous'+ targetUserId)
      console.log('ajaxUsersFavoriteAnoymous'+ userId)
      console.log('ajaxUsersFavoriteAnoymous'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/anoymous/userFavorite",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiFavoriteAnoymous(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersFavoriteArticle(userId, targetUserId, pageNumber){
      var _this = this;
      console.log('ajaxUsersFavoriteArticle')
      console.log('ajaxUsersFavoriteArticle'+ targetUserId)
      console.log('ajaxUsersFavoriteArticle'+ userId)
      console.log('ajaxUsersFavoriteArticle'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/article/userFavoriteArticle",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiFavoriteArticle(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersArticle(userId, targetUserId, pageNumber){
      var _this = this;
      console.log('ajaxUsersArticle')
      console.log('ajaxUsersArticle'+ targetUserId)
      console.log('ajaxUsersArticle'+ userId)
      console.log('ajaxUsersArticle'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/article/authors",
          qs.stringify({
            userId: userId,
            authorId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiArticle(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersAnoymous(userId, targetUserId, pageNumber){
      var _this = this;
      console.log('ajaxUsersAnoymous')
      console.log('ajaxUsersAnoymous'+ targetUserId)
      console.log('ajaxUsersAnoymous'+ userId)
      console.log('ajaxUsersAnoymous'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/anoymous/listByAuthorId",
          qs.stringify({
            userId: userId,
            authorId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiAnoymous(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUsersDiscuss(userId, targetUserId,pageNumber){
      var _this = this;
      console.log('ajaxUsersDiscuss')
      console.log('ajaxUsersDiscuss'+ targetUserId)
      console.log('ajaxUsersDiscuss'+ userId)
      console.log('ajaxUsersDiscuss'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/discuss/authors",
          qs.stringify({
            userId: userId,
            authorId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          
          console.log(res.data.code)
          // _this.initElPage(res);
          _this.intiDiscuss(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxTag: function(userId, targetUserId,pageNumber) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/tag/allUserAttentionAndUnattention",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          _this.initTags(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxMessage: function(userId,pageNumber) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/message/receiveMessage",
          qs.stringify({
            userId: userId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          _this.initMessage(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxInfo: function(userId,pageNumber) {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/user/infos",
          qs.stringify({
            userId: userId,
            pageNumber: pageNumber,
            pageSize: 10,
            isRead: 1,
          })
        )
        .then(function(res) {
          _this.initInfo(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    initInfo(res){
      console.log('initInfos')
      this.infos = res.data.infoList.list;
      
      this.infoPages = res.data.infoList.total;
      console.log(this.infoPages)
      console.log(this.infoPageNow)
      this.infoPageNow = res.data.infoList.pageNum;
      console.log(this.infoPageNow)
    },
    initMessage(res){
      this.messages = res.data.messageList.list;
      this.pages = res.data.messageList.pages;
      this.pageNow = 1;
    },
    initTags(res){
      this.attentionTags = res.data.attentionTags.list;
      this.commentPages = res.data.attentionTags.total;
      this.unAttentionTags = res.data.unAttentionTags.list;
      this.pageNow = 1;
    },
    intiComment(res) {
      this.comments = res.data.commentList.list;
      this.commentPages = res.data.commentList.total;
      this.commentPageNow = res.data.commentList.pageNum;
    },
    intiAnoymous(res) {
      this.pages = res.data.anoymousList.total;
      this.discuss = res.data.anoymousList.list;
      this.pageNow = res.data.anoymousList.pageNum;
    },
    intiArticle(res) {
      this.pages = res.data.articleList.total;
      this.discuss = res.data.articleList.list;
      this.pageNow = res.data.articleList.pageNum;
    },
    intiDiscuss(res) {
      this.pages = res.data.discussList.total;
      this.discuss = res.data.discussList.list;
      this.pageNow = res.data.discussList.pageNum;
    },
    intiFavoriteDiscuss(res) {
      this.pages = res.data.discussList.total;
      this.favoriteList = res.data.discussList.list;
      this.pageNow = res.data.discussList.pageNum;
    },
    intiFavoriteAnoymous(res) {
      this.pages = res.data.anoymousList.total;
      this.favoriteList = res.data.anoymousList.list;
      this.pageNow = res.data.anoymousList.pageNum;
    },
    intiFavoriteArticle(res) {
      this.pages = res.data.articleList.total;
      this.favoriteList = res.data.articleList.list;
      console.log('initArticleFavorite')
      console.log(this.favoriteList[0].articleTitle)
      this.pageNow = res.data.articleList.pageNum;
    },
    errorNotice(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
      },
       successNotice(message){
        console.log("error")
        this.$notify({
          title: '成功',
          message: message,
          type: 'success'
        });
      },
    ajaxIsAttention(userId, targetUserId) {
      var _this = this;
      console.log('ajaxIsAttention'+ targetUserId)
      console.log('ajaxIsAttention'+ userId)
      axios
        .post(
          "http://localhost:5555/sclp/user/isAttention",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            
          })
        )
        .then(function(res) {
          // _this.initElPage(res);
          if (res!=null) {
            if (res.data.mapUser.userAttentionType == 1) {
              
              _this.isAttention = true
            } else {
              _this.isAttention = false
            }
          }
          console.log('_this.isAttention')
          console.log(_this.isAttention)
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUserAttentions: function(userId, targetUserId,pageNumber) {
      var _this = this;
      console.log('ajaxUser'+ targetUserId)
      console.log('ajaxUser'+ userId)
      console.log('ajaxUser'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/user/attentionUserList",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          // _this.initElPage(res);
          _this.initUserAttentions(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUserFollow: function(userId, targetUserId,pageNumber) {
      var _this = this;
      console.log('ajaxUser'+ targetUserId)
      console.log('ajaxUser'+ userId)
      console.log('ajaxUser'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/user/userFollowList",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          // _this.initElPage(res);
          _this.initUserFollow(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    ajaxUser: function(userId, targetUserId,pageNumber) {
      var _this = this;
      console.log('ajaxUser'+ targetUserId)
      console.log('ajaxUser'+ userId)
      console.log('ajaxUser'+ pageNumber)
      axios
        .post(
          "http://localhost:5555/sclp/user/center",
          qs.stringify({
            userId: userId,
            targetUserId: targetUserId,
            pageNumber: pageNumber,
            pageSize: 10
          })
        )
        .then(function(res) {
          // _this.initElPage(res);
          _this.initUserANDactives(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    // initElPage(res){
    //   this.pages = res.data.pages
    // },
    initUserFollow(res) {
      this.followUsers = res.data.userPageInfo.list;
      this.pages = res.data.userPageInfo.total;
      this.pageNow = res.data.userPageInfo.pageNum;
    },
    initUserAttentions(res) {
      this.attentionUsers = res.data.userPageInfo.list;
      this.pages = res.data.userPageInfo.total;
      this.pageNow = res.data.userPageInfo.pageNum;
    },
    initUserANDactives(res){
        console.log("code"+ res.data.code)
        this.targetUser = res.data.user;
        
        this.activites = res.data.actives;
    },
    initPage(){
        this.targetUserId = this.$route.params.targetUserId;
        console.log('start'+ this.$route.params.targetUserId)
        this.userId = window.localStorage.getItem("userId");
        console.log('activeName'+ this.$route.params.activeName)
        this.activeName = this.$route.params.activeName;
        this.tagName = 'attentionTags'
        this.commentName = 'discussComment';
        this.commentPageNow = 1;
        this.ajaxTag(this.userId, this.targetUserId,1)
        this.ajaxUsersComment(this.userId, this.targetUserId,this.commentPageNow,1);
        if (this.activeName == null) {
          this.activeName = 'active';
        }
        this.pageNow = 1;
        console.log("boolean: ")
        var boolean  = this.userId == this.targetUserId;
        console.log(boolean)
        this.notIsTargetUser = true;
        if (boolean == true) {
          this.notIsTargetUser = false;
          console.log("booleana")
          this.isAttention = true
        } else {
          this.ajaxIsAttention(this.userId, this.targetUserId);
        }
        console.log('userId: '+ this.userId)
        console.log('page: '+ this.pageNow)
        this.ajaxUser(this.userId, this.targetUserId, this.pageNow);
        this.ajaxInfo(this.userId, 1)
        
    }
  },
  mounted() {
    // this.location.reload();
      this.initPage();
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh : mm");
    }
  }
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


<style scoped>
.sep100{
  height: 130px
}
#div1 {
  background-color: #f2f2f2;

  height: auto !important;
  height: 100px;
  min-height: 900px;
}
#div2 {
  /* background-color: darkslateblue; */
  height: auto !important;
  min-height: 900px;
}
#div3 {
  /* background-color:darkgreen; */
  height: 200px;
}
#div4 {
  /* background-color:crimson; */
  height: auto !important;
  min-height: 80px;
}
#div5 {
  /* background-color:chartreuse; */
  height: auto !important;
  min-height: 30px;
}
#div6 {
  /* background-color:yellow; */
  height: auto !important;
  min-height: 50px;
}
#div7 {
  /* background-color:violet; */
  height: auto !important;
  min-height: 500px;
}
.sep5 {
  height: 5px;
}
</style>

