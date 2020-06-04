package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/")
    public String getIndex(){
        return "a";
    }
   // @RequestMapping("/images/adult-beautiful-beauty-915051.jpg")
    //public String getImages(){
       // return "images/adult-beautiful-beauty-915051";
    //}

    @RequestMapping("/home")
    public String goHomePage(){
        return "home";
    }


}
