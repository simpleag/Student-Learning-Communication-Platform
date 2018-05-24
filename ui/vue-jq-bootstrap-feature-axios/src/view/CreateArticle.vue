<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class="col-md-12" id="div4">
            <div class="col-md-12 sep5"></div>
            <div class="col-md-12 sep5"></div>
             <div class="col-md-3">
                <!-- <el-row>
            
                  <el-select v-model="value1" placeholder="请选择分类" id='discussSelect' @change="discussChangeValue">
                      <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.label">
                      </el-option>
                  </el-select>
              </el-row> -->
            </div>
            <div class="col-md-12 text-left">
                
                <el-input v-model="input" placeholder="请输入标题"></el-input>
            </div>
            
            <div class="col-md-12 sep5"></div>
        </div>
        <div class="col-md-12 sep5"></div>
        <!-- <div class="col-md-12 sep5"></div>
        <div class="col-md-12 sep5"></div> -->
        <div class="col-md-12" id="div5">
            <div class='sep5'></div>
           <div class="components-container">
               <!-- <div class='text-right'>
                    <button type="button" class="btn btn-default text-right">发表回复</button> 
               </div> -->
                
                <button @click="getUEContent()" class='hide'>获取内容</button>
                <div class="editor-container">
                <UE :defaultMsg=defaultMsg :config=config ref="ue"></UE>
                </div>
            </div>
             
        </div>
        <div class="col-md-12 sep5"></div>
        
       <div class="col-md-12"id="div6">
         <!-- <el-upload
          class="upload-demo"
          action="http://localhost:8093/article/uploadPicFile"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :before-upload="beforeUpload"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList2">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload> -->
        <!-- <el-upload
          class="upload-demo"
          action="http://localhost:5555/sclp/article/uploadPicFile"
          :data="exterData"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload> -->
        <div class='col-md-12' id='div8'>
          <img :src="picUrl" width="100%" height="100%" class="center-block">
          <!-- <img :src="picUrl" width="100%" class="img-responsive" border="0" align="default" > -->
        </div>
        <p></p>
        
        <form enctype="multipart/form-data" method="post">
          <input type="file" @change="getFile($event)">
          <button class="button button-primary button-pill button-small" @click="uploadPic($event)">提交</button>
        </form>
        </div>
    </div>
    <div class="col-md-3" id='div3'>
        <el-row class='text-left'>
            <el-button plain @click="createArticle">发布文章</el-button>
            <p></p>
             <el-button plain @click="notCreate" >取消发布</el-button>
             <p></p>
             <el-select v-model="value2" placeholder="请选择标签" id='tagSelect' @change="tagChangeValue">
                <el-option
                v-for="item in tags"
                :key="item.tagId"
                :label="item.tagName"
                :value="item.tagId">
                </el-option>
            </el-select>
        </el-row>
        <el-row>
            
        </el-row>
    </div>
   </div>
</template>

<script>


import Vue from "vue";
import UE from "../components/Ue.vue";
import qs from 'qs';

var tags;
var userId;
var discussType;
var tagType;
var input= '';
var file= '';
var picUrl = '';
export default {
  components: { UE },
  data() {
    return {
      items: items,
      defaultMsg: "在这里输入讨论内容",
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 500
      },
      fileList2: [],
      tags: tags,
     
      value1: "",
      value2: "",
      input: this.input,
      userId: this.userId,
      exterData: {
        userId: this.userId
      },
      file: this.file,
      picUrl: this.picUrl
    };
  },
  methods: {
    getFile(event) {
        this.file = event.target.files[0];
        console.log(this.file);
      },
    uploadPic(event){
      // var file = event.target.files
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
          _this.createPic(res);
          // _this.initTags(res);
        })
        .catch(function(err) {
          console.log('error')
          _this.errorNotice('文件过大');
          console.log("error" + err);
        });
    // return false // 返回false不会自动上传
    },
    
    createPic(res){
      this.picUrl = res.data.imgUrl;
    },
   
    getUEContent() {
      let content = this.$refs.ue.getUEContent();
      this.$notify({
        title: "获取成功，可在控制台查看！",
        message: content,
        type: "success"
      });
      console.log(content);
    },
    discussChangeValue(value){
      this.discussType = value;
      console.log("value"+ value+" "+ this.discussType)
    },
    tagChangeValue(value){
      this.tagType = value;
      console.log("value"+ value+" "+ this.tagType)
    },
    notCreate(){
      this.$router.push({ name: "Main"});
    },
    ajaxCreateArticle: function(tiltle, content, tagId) {
      var _this = this;
      console.log(tiltle)
      console.log(content)
      console.log(tagId)
      console.log(this.userId)

      axios
        .post(
          "http://localhost:5555/sclp/article/createArticle",
          qs.stringify({
            userId: this.userId,
            articleTiltle: tiltle,
            articleContent: content,
            tagId: tagId,
            articlePicUrl: this.picUrl

          })
        )
        .then(function(res) {
          console.log(res.data.code)
          if (res.data.code == '200') {
            _this.$router.push({ name: "Main"});
            
          } else {
            _this.errorNotice('res.data.code');
          }
        })
        .catch(function(err) {
          console.log("error" + err);
          this.errorNotice('权限验证失败');
        });
    },
    ajaxTag: function() {
      var _this = this;
      axios
        .post(
          "http://localhost:5555/sclp/tag/allTags",
          qs.stringify({
            userId: this.userId,
            
          })
        )
        .then(function(res) {
          _this.initTags(res);
        })
        .catch(function(err) {
          console.log("error" + err);
        });
    },
    initTags(res){
      console.log("row: "+res.data.listTags.endRow)
      this.tags = res.data.listTags.list
      
    },
    initPage(){
      this.userId = window.localStorage.getItem("userId");
      console.log("userId: "+ this.userId)
      this.ajaxTag();
    },
    errorNotice(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
      },
   
    createArticle(){
      let content  = this.$refs.ue.getUEContent();;
      console.log("1: "+ content)
      console.log('text: '+ this.input)
      console.log( this.discussType+ " "+  this.tagType)
      if ( this.tagType==undefined) {
        console.log("fail")
        this.errorNotice('输入不完整');
        
      } else{
        var title = this.input;
        this.ajaxCreateArticle(title, content, this.tagType);
        // this.$router.push({ name: "Main"});
      }

    }
  },
  mounted() {
    this.initPage();
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
  /* background-color:black; */
  min-height: 200px;
}
#div4 {
  /* background-color: burlywood; */
  min-height: 80px;
}

#div5 {
  /* background-color: chartreuse; */
  min-height: 500px;
}
#div6 {
  /* background-color: cornflowerblue; */
  min-height: 110px;
}
#div8 {
  /* background-color: cornflowerblue; */
  /* min-height: 300px; */
  height: 300px;
  width: 100%;
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

