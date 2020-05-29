package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.service.ItemService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    /**
     * 分页查询商品
     * @param itemId
     * @return
     * */

    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId){
        return this.itemService.selectItemInfo(itemId);
    }

    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @return
     * */
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(Integer page,Long rows){
        return itemService.selectTbItemAllByPage(page,rows);
    }

    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem, String desc, String itemParams){
        return itemService.insertTbItem(tbItem,desc,itemParams);
    }

    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestParam Long itemId){
        return itemService.deleteItemById(itemId);
    }

    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(Long itemId){
        return this.itemService.preUpdateItem(itemId);
    }

    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem,String desc,String itemParams){
        return itemService.updateTbItem(tbItem,desc,itemParams);
    }
}
