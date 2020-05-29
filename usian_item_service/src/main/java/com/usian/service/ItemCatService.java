package com.usian.service;

import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemParam;
import com.usian.utils.CatResult;
import com.usian.utils.PageResult;

import java.util.List;

public interface ItemCatService {

    CatResult selectItemCategoryAll();

    List<TbItemCat> selectItemCategoryByParentId(Long id);
}


