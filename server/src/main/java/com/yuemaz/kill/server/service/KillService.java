package com.yuemaz.kill.server.service;

/**
 * @author: create by w5g
 * @date: 2022/5/17 11:02
 */
public interface KillService {

    Boolean killItem(Integer killId,Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

    Boolean killItemV5(Integer killId, Integer userId) throws Exception;
}
