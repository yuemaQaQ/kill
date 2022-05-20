package com.yuemaz.kill.server.service.impl;

import com.yuemaz.kill.model.entity.ItemKill;
import com.yuemaz.kill.model.mapper.ItemKillMapper;
import com.yuemaz.kill.server.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: create by w5g
 * @date: 2022/5/16 18:17
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Resource
    private ItemKillMapper itemKillMapper;

    /**
     * 获取待秒杀商品列表
     * @return
     * @throws Exception
     */
    @Override
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAll();
    }

    /**
     * 获取待秒杀商品详情
     * @param id
     * @return
     */
    @Override
    public ItemKill getKillDetail(Integer id) throws Exception {
        ItemKill entity=itemKillMapper.selectById(id);
        if (entity==null){
            throw new Exception("获取秒杀详情-待秒杀商品记录不存在");
        }
        return entity;
    }
}
