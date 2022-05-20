package com.yuemaz.kill.server.controller;

import com.yuemaz.kill.api.enums.StatusCode;
import com.yuemaz.kill.api.response.BaseResponse;
import com.yuemaz.kill.model.dto.KillSuccessUserInfo;
import com.yuemaz.kill.model.mapper.ItemKillSuccessMapper;
import com.yuemaz.kill.server.dto.KillDto;
import com.yuemaz.kill.server.service.KillService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: create by w5g
 * @date: 2022/5/17 11:13
 */
@Controller
public class KillController {

    private static final Logger log = LoggerFactory.getLogger(KillController.class);

    private static final String prefix = "kill";

    @Resource
    private KillService killService;

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;

    /***
     * 商品秒杀核心业务逻辑
     * @param dto
     * @param result
     * @return
     */
    @PostMapping(value = prefix+"/execute")
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto dto,
                                BindingResult result){
        if (result.hasErrors() || dto.getKillId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //Boolean res=killService.killItem(dto.getKillId(),userId);
            Boolean res=killService.killItem(dto.getKillId(), dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /***
     * 商品秒杀核心业务逻辑-用于压力测试
     * @param dto
     * @param result
     * @return
     */
    @PostMapping(value = prefix+"/execute/lock")
    @ResponseBody
    public BaseResponse executeLock(@RequestBody @Validated KillDto dto,
                                    BindingResult result){
        if (result.hasErrors() || dto.getKillId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //Boolean res=killService.killItem(dto.getKillId(),userId);
            Boolean res=killService.killItemV5(dto.getKillId(), dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 查看订单详情
     * @return
     */
    @GetMapping(value = prefix+"/record/detail/{orderNo}")
    public String killRecordDetail(@PathVariable String orderNo, ModelMap modelMap){
        if (StringUtils.isBlank(orderNo)){
            return "error";
        }
        KillSuccessUserInfo info=itemKillSuccessMapper.selectByCode(orderNo);
        if (info==null){
            return "error";
        }
        modelMap.put("info",info);
        return "killRecord";
    }

    /**
     * 抢购成功跳转页面
     * @return
     */
    @GetMapping(prefix+"/execute/success")
    public String executeSuccess(){
        return "executeSuccess";
    }

    /**
     * 抢购失败跳转页面
     * @return
     */
    @GetMapping(prefix+"/execute/fail")
    public String executeFail(){
        return "executeFail";
    }
}
