<template>
  <div class="row form-inline" id='div1'>
    <div class="col-md-6 col-md-offset-3 form-inlin" id='div2'>
        <div class="col-md-12" id="div4">
            <div class="col-md-12 sep5"></div>
            <div class="col-md-12 sep5"></div>
             <div class="col-md-3">
                <el-select v-model="value7" placeholder="请选择">
                    <el-option-group
                    v-for="group in options3"
                    :key="group.label"
                    :label="group.label">
                    <el-option
                        v-for="item in group.options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                    </el-option-group>
                </el-select>
            </div>
            <div class="col-md-9">
                
                <el-input v-model="input" placeholder="请输入标题"></el-input>
            </div>
            
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
         
             
        </div>
    </div>
    <div class="col-md-3 text-left" id='div3'>
        <el-row >
            <el-button plain>发布</el-button>
             <el-button plain>取消发布</el-button>
             
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
      defaultMsg: '在这里输入讨论内容',
        config: {
          initialFrameWidth: null,
          initialFrameHeight: 500
        },
        fileList2: [],
        options3: [{
          label: '热门城市',
          options: [{
            value: 'Shanghai',
            label: '上海'
          }, {
            value: 'Beijing',
            label: '北京'
          }]
        }, {
          label: '城市名',
          options: [{
            value: 'Chengdu',
            label: '成都'
          }, {
            value: 'Shenzhen',
            label: '深圳'
          }, {
            value: 'Guangzhou',
            label: '广州'
          }, {
            value: 'Dalian',
            label: '大连'
          }]
        }],
        value7: ''
        
      
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

