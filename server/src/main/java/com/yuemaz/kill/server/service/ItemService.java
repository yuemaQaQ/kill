package com.yuemaz.kill.server.service;

import com.yuemaz.kill.model.entity.ItemKill;

import java.util.List;

/**
 * @author: create by w5g
 * @date: 2022/5/16 18:16
 */
public interface ItemService {

    List<ItemKill> getKillItems() throws Exception;

    ItemKill getKillDetail(Integer id) throws Exception;
}
