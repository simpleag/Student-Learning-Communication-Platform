<template>
    <div id="chartsBox">
        <button id="btn_close" @click="hiddenChart">
            <span>×</span>
        </button>
        <div id="main"  :style="{width:'100%',height:'100%'}"></div>
    </div>
</template>

<script>
import echarts from "echarts"
export default {
    name:"TestChart",
    props:["chartDis"],
    data(){
        return {
        }
    },
    methods:{
        hiddenChart(){
            this.$router.push("/");
        }
    },
    mounted(){
        var myChart = echarts.init(document.getElementById('main'));
        var data = [];
        for (var i = 0; i <= 100; i++) {
            var theta = i / 100 * 360;
            var r = 5 * (1 + Math.sin(theta / 180 * Math.PI));
            data.push([r, theta]);
        }
        myChart.setOption({
            title:{
                left: 'center',
                text: 'Love & Peace',
                textStyle: {
                    color: '#fff',
                    fontSize: 32,
                },
                subtext: "成功加载ECharts line chart.",
                subtextStyle: {
                    color: "yellow",
                    fontSize: 16
                },
            },
            color: ["red"],
            textStyle:{
                color: "#fff",
            },
            polar: {
                lineStyle: {
                    color: "yellow"
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            angleAxis: {
                type: 'value',
                startAngle: 0
            },
            radiusAxis: {
            },
            series: [{
                coordinateSystem: 'polar',
                name: 'line',
                type: 'line',
                data: data,
            }]
        })
    }
}
</script>

<style scoped>
    #chartsBox {
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color: #000;
    }
    #btn_close {
        position: absolute;
        right: 10px;
        top: 0;
        background-color: 0;
        padding: 0;
        border: none;
        background-color: rgba(0, 0, 0, 0);
        z-index: 99
    }
    #btn_close:active,#btn_close:focus {
        outline: none;
    }
    #btn_close span {
        font-size: 32px;
        color: #fff;
        line-height: 32px;
    }
    #btn_close :hover {
        color: red;
    }
</style>

