package com.liyan.controller;

import com.liyan.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DemoController {

    @RequestMapping("demo")
    public String Demo(@RequestParam(value = "name1") String name, @RequestParam(value = "password1") String password) {
        System.out.println("执行controller"+"+name+"+"password");
        return "index.jsp";
    }
    public String page(@RequestParam(defaultValue = "2")int pageSize,@RequestParam(defaultValue = "1") int pageNumber){
        System.out.println(pageSize+pageNumber);
        return "index.jsp";
    }
    @RequestMapping("demo1")
    public String Demo1(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,@RequestParam(value = "haver") List<String> hover){
        System.out.println(name+" "+password+" "+" "+hover);
        return "index.jsp";
    }
    @RequestMapping("demo2")
    public String Demo2(User user){
        System.out.println(user);
        return "index.jsp";
    }
    @RequestMapping("demo3")
    public String Demo3(){
        System.out.println("请求转发");
        return "index.jsp";
    }
    @RequestMapping("demo4/{id1}/{name}")
    public String Demo4(@PathVariable("name") String name,@PathVariable("password") String password){
        System.out.println(name+"  "+password);
        return "index.jsp";
    }

    @RequestMapping(value = "demo5",produces = "text/html;charset=utf-8")
    @ResponseBody
    public User Demo5()throws Exception{
        User user=new User();
        user.setName("root");
        user.setPassword("root");
        return  user;
    }
}
