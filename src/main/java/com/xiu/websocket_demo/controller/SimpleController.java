package com.xiu.websocket_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;


@Controller
public class SimpleController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    HttpServletResponse response;
    @RequestMapping("/")
    public String index(){
        return "/v1/index";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "/v1/admin";
    }
    @RequestMapping("/v2/index")
    public String index2(){
        return "/v2/index";
    }

    @RequestMapping("/v3/index")
    public String index3(){
        return "/v3/index";
    }

    @RequestMapping("/test/index")
    public String test(){
        return "/test/test";
    }


    @RequestMapping("/websocket/{name}")
    public String webSocket(@PathVariable String name, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username",name);
            return "websocket";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/v6/index")
    public String index6(){
        return "/v6/index";
    }
    @RequestMapping("/v6/error")
    public String error6(){
        return "/v6/error";
    }
    @RequestMapping("/v6/aaa")
    public String chat6(){
        return "/v6/chat";
    }
}
