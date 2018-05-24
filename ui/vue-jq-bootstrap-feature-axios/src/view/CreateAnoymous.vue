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
         
             
        </div>
    </div>
    <div class="col-md-3" id='div3'>
        <el-row class='text-left'>
            <el-button plain @click="createAnoymous" >发布匿名讨论</el-button>
            <p></p>
             <el-button plain @click="notCreate" >取消发布讨论</el-button>
             <p></p>
             <!-- <el-select v-model="value2" placeholder="请选择标签" id='tagSelect' @change="tagChangeValue">
                <el-option
                v-for="item in tags"
                :key="item.tagId"
                :label="item.tagName"
                :value="item.tagId">
                </el-option>
            </el-select> -->
        </el-row>
        <el-row>
            
        </el-row>
    </div>
   </div>
</template>

<script>
var tags;
var userId;
var discussType;
var tagType;
var input= '';

import Vue from "vue";
import UE from "../components/Ue.vue";
import qs from 'qs';
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
      input: this.input
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
    ajaxcreateAnoymous: function(tiltle, content) {
      var _this = this;
      console.log(tiltle)
      console.log(content)
      // console.log(tagId)
      console.log(this.userId)
      axios
        .post(
          "http://localhost:5555/sclp/anoymous/create",
          qs.stringify({
            userId: this.userId,
            tiltle: tiltle,
            content: content,
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
          _this.errorNotice('权限验证失败');
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
    errorNet(message){
        console.log("error")
        this.$notify({
          title: '出错',
          message: message,
          type: 'error'
        });
        this.$router.push({ name: "Login" });
      },
    createAnoymous(){
      let content  = this.$refs.ue.getUEContent();;
      console.log("1: "+ content)
      console.log('text: '+ this.input)
      console.log( this.discussType+ " "+  this.tagType)
      // if ( this.tagType==undefined) {
      //   console.log("fail")
      //   this.errorNotice('输入不完整');
       
      // } else{
        var title = this.input;
        this.ajaxcreateAnoymous(title, content);
        // this.$router.push({ name: "Main"});
      // }

    }
  },
  mounted() {
    this.initPage();
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

