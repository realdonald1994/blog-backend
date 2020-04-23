package com.donald.web;

import com.donald.NotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
//        int i =1/0;
//        String blog =null;
//        if(StringUtils.isBlank(blog)){
//            throw  new NotFoundException("blog is not exist");
//        }
//        System.out.println("-------index-----");
//        System.out.println(id+":"+name);
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }
}
