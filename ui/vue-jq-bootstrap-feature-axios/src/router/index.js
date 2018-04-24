import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import TestChart from '@/components/TestChart'
import Login from '@/view/Login'
import Register from '@/view/Register'
import Home from '@/view/Home'
import Main from '@/view/Main'

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
        }
      ]    
    }
  ]
})
