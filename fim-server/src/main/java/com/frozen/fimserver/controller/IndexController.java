package com.frozen.fimserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Frozen
 * @create: 2019-08-15 14:24
 * @description:
 **/
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "sendMsg")
    @ResponseBody
    public String sendMsg(){
        return "frozen" ;
    }
}
