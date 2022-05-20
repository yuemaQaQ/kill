package com.yuemaz.kill.server.controller;

import com.yuemaz.kill.api.enums.StatusCode;
import com.yuemaz.kill.api.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: create by w5g
 * @date: 2022/5/16 16:45
 */
@Controller
@RequestMapping("base")
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @GetMapping(value = "/welcome")
    public String welcome(String name, ModelMap modelMap) {
        if (StringUtils.isBlank(name)) {
            name = "这是welcome";
        }
        modelMap.put("name", name);
        return "welcome";
    }

    @GetMapping("/data")
    @ResponseBody
    public String data(String name) {
        if (StringUtils.isBlank(name)) {
            name = "这是welcome";
        }
        return name;
    }

    @RequestMapping(value = "/response")
    @ResponseBody
    public BaseResponse response(String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        if (StringUtils.isBlank(name)){
            name="这是welcome!";
        }
        response.setData(name);
        return response;
    }

    @GetMapping(value = "/error")
    public String error(){
        return "error";
    }
}
