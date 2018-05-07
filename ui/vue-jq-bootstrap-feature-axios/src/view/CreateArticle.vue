<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class="col-md-12" id="div4">
            <div class="col-md-12 sep5"></div>
            <div class="col-md-12 sep5"></div>
            <el-input v-model="input" placeholder="请输入标题"></el-input>
            <div class="col-md-12 sep5"></div>
        </div>
        <div class="col-md-12 sep5"></div>
        <div class="col-md-12 sep5"></div>
        <div class="col-md-12 sep5"></div>
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
          <el-upload class="upload-demo" action="" :auto-upload='false' :on-change='changeUpload'>  
            <el-button size="small" type="primary">点击上传</el-button>  
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>  
        </el-upload>     
             
        </div>
    </div>
    <div class="col-md-3" id='div3'>
        <el-row>
            <el-button plain>发布</el-button>
             <el-button plain>取消发布</el-button>
             <el-select v-model="value" placeholder="请选择标签">
                <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
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
import UE from '../components/Ue.vue';
export default {
  components: {UE},
  data() {
    return {
      items: items,
      defaultMsg: '在这里输入文章内容',
        config: {
          initialFrameWidth: null,
          initialFrameHeight: 500
        },
        fileList2: [],
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: ''
      
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
      changeUpload: function(file, fileList) {  
        this.fileList = fileList;  
        this.$nextTick(  
          () => {  
            let upload_list_li = document.getElementsByClassName('el-upload-list')[0].children;  
            for (let i = 0; i < upload_list_li.length; i++) {  
              let li_a = upload_list_li[i];  
              let imgElement = document.createElement("img");  
              imgElement.setAttribute('src', fileList[i].url);  
              imgElement.setAttribute('style', "max-width:100%;max-height:50%;padding-left:25%");  
              if (li_a.lastElementChild.nodeName !== 'IMG') {    
                li_a.appendChild(imgElement);  
              }    
            }  
          });  
      }  
  },
  mounted() {}
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
  background-color: blueviolet;
  height: auto !important;
  min-height: 900px;
}
#div3 {
  /* background-color:black; */
  min-height: 200px;
}
#div4 {
  background-color: burlywood;
  min-height: 80px;
}

#div5 {
  background-color: chartreuse;
  min-height: 500px;
}
#div6 {
  background-color: cornflowerblue;
  min-height: 110px;
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

