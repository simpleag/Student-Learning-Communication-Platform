import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import TestChart from '@/components/TestChart'
import Login from '@/view/Login'
import Register from '@/view/Register'
import Home from '@/view/Home'
import Main from '@/view/Main'
import TagList from '@/view/TagList'
import TagDetail from '@/view/TagDetail'
import Discuss from '@/view/Discuss'
import UserCenter from '@/view/UserCenter'
import UserEdit from '@/view/UserEdit'
import CreateArticle from '@/view/CreateArticle'
import CreateDiscuss from '@/view/CreateDiscuss'
import CreateAnoymous from '@/view/CreateAnoymous'
import Message from '@/view/Message'
import HitoryMessage from '@/view/HitoryMessage'
import Info from '@/view/Info'
import Article from '@/view/Article'
import Anoymous from '@/view/Anoymous'
Vue.use(Router)

export default new Router({
  mode:"history",
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello,
      children:[
        {
          path: '/testchart',
          name: 'TestChart',
          component: TestChart
        },{
          path: 'Login',
          name: 'Login',
          component: Login
        },{
          path: 'Register',
          name: 'Register',
          component : Register     
        }
        ]
    },{
      path: '/Home',
      name: 'Home',
      component : Home,
      children:[
        {
          path: 'Main',
          name: 'Main',
          component: Main
        },{
          path: 'TagList',
          name: 'TagList',
          component: TagList
        },{
          path: 'TagDetail',
          name: 'TagDetail',
          component: TagDetail
        },{
          path: 'Discuss/:discussId',
          name: 'Discuss',
          component: Discuss
        },{
          path: 'Article/:articleId',
          name: 'Article',
          component: Article
        },{
          path: 'Anoymous/:anoymousId',
          name: 'Anoymous',
          component: Anoymous
        },{
          path: 'UserCenter',
          name: 'UserCenter',
          component : UserCenter     
        },{
          path: 'UserEdit',
          name: 'UserEdit',
          component : UserEdit     
        },{
          path: 'CreateArticle',
          name: 'CreateArticle',
          component : CreateArticle     
        },{
          path: 'CreateDiscuss',
          name: 'CreateDiscuss',
          component : CreateDiscuss     
        },{
          path: 'CreateAnoymous',
          name: 'CreateAnoymous',
          component : CreateAnoymous     
        },{
          path: 'Message',
          name: 'Message',
          component : Message     
        },{
          path: 'HitoryMessage',
          name: 'HitoryMessage',
          component : HitoryMessage     
        },{
          path: 'Info',
          name: 'Info',
          component : Info     
        }
      ]    
    }
  ]
})
