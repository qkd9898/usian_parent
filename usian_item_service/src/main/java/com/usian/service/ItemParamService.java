package com.usian.service;

import com.usian.pojo.TbItemParam;
import com.usian.utils.PageResult;

public interface ItemParamService {

    Integer insertItemParam(Long itemCatId, String paramDate);

    TbItemParam selectItemParamByItemCatId(Long itemCatId);

    PageResult selectItemParamAll(Integer page, Integer rows);

    Integer deleteItemParamById(Long id);
}
