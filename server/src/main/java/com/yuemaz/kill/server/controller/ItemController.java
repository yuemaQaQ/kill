package com.yuemaz.kill.server.controller;

import com.yuemaz.kill.model.entity.ItemKill;
import com.yuemaz.kill.server.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: create by w5g
 * @date: 2022/5/16 18:07
 */
@Controller
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Resource
    private ItemService itemService;

    /**
     * 获取商品列表
     * @param modelMap
     * @return
     */
    @GetMapping(value = {"/", "/index", prefix+"/list", prefix+"/index.html"})
    public String list(ModelMap modelMap) {
        try {
            //获取秒杀商品列表
            List<ItemKill> list = itemService.getKillItems();
            modelMap.put("list", list);
            log.info("获取待秒杀商品列表：", list);
        }catch (Exception e) {
            log.error("获取带秒杀商品列表-发生异常：", e.fillInStackTrace());
            return "redirect:/base/error";
        }
        return "list";
    }

    /**
     * 获取待秒杀商品详情
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping(prefix + "/detail/{id}")
    public String detail(@PathVariable Integer id,
                         ModelMap modelMap) {
        if (id==null || id<=0){
            return "redirect:/base/error";
        }
        try {
            ItemKill detail=itemService.getKillDetail(id);
            modelMap.put("detail",detail);
        }catch (Exception e){
            log.error("获取待秒杀商品的详情-发生异常：id={}",id,e.fillInStackTrace());
            return "redirect:/base/error";
        }
        return "info";
    }
}
