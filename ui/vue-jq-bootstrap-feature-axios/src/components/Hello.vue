<template>
  <div class="hello">
    <!-- <h1>{{ msg }}</h1>
    <br />
    <h3>已经引入了jQuery、BootStrap和ECharts</h3>
    <br />
    <div>
      <img src="../assets/icon_jquery.png" />
      <img src="../assets/icon_bootstrap.png" />
      <img src="../assets/icon_echarts.png" />
    </div>
    <h2>开始构建你的Web吧</h2>
    <br />
    <div>
      <div id="alert" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Success!</strong> 成功引入BootStrap Alert组件.
      </div>
    </div>
    <br>
    <button id="btn_show" type="button" class="btn btn-default" @click="showChart">show echarts demo</button>
     <TestChart v-bind:chartDis='chartDis' @hiddenChart='hiddenChart' />  -->
     <div></div>
     <router-view></router-view>
  </div>
</template>

<script>
  import TestChart from './TestChart'
  // import axios from 'axios'
  export default {
    name: 'hello',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
      }
    },
    methods:{
      showChart(){
        this.$router.push("/testchart");
      }
    },
    mounted(){
      axios.get('../../index.html')
      .then(function (data) {
        console.log(data);
      });
    },
    components: {
      TestChart: TestChart
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

  img {
    width: 160px;
  }

  #alert {
    margin: 0 auto;
    width: 500px;
  }

  #btn_show {
    border-color: #108ee9;
    color: #108ee9;
  }
  #btn_show:active,#btn_show:focus,#btn_show:hover {
    background-color: #108ee9;
    color: #fff;
    outline: none;
  }
</style>
