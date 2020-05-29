package com.usian.feign;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemParam;
import com.usian.utils.CatResult;
import com.usian.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "usian-item-service")
public interface ItemServiceFeign {

    @RequestMapping("/service/itemParam/insertItemParam")
    Integer inserItemParam(@RequestParam Long itemCatId, @RequestParam String paramDate);

    /**
     * 查询商品信息
     * @param itemId
     * @return TbItem
     * */

    @RequestMapping("/service/item/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

    /**
     * 分页查询商品列表
     * @param page
     * @Parm rows
     * @return PageRusult
     *
     * */
    @GetMapping("/service/item/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam("page") Integer page,
                                     @RequestParam("rows") Long rows);

    /**
     * 分页查询商品列表
     * @param id
     *@return
     *
     * */
    @RequestMapping("/service/itemCat/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);


    /**
     * 分页查询商品列表
     * @param itemCatId
     *@return
     *
     * */
    @RequestMapping("/service/itemParam/selectItemParamByItemCatId/{itemCatId}")
    TbItemParam selectItemParamByItemCatId(@PathVariable Long itemCatId);

    @RequestMapping("/service/item/insertTbItem")
    Integer insertTbItem(TbItem tbItem,@RequestParam String desc,@RequestParam String itemParams);

    @RequestMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam Integer page, @RequestParam Integer rows);

    @RequestMapping("/service/itemParam/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam Long id);

    @RequestMapping("/service/itemCat/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    @RequestMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestParam Long itemId);

    @RequestMapping("/service/item/preUpdateItem")
    Map<String,Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    @RequestMapping("/service/item/updateTbItem")
    Integer updateTbItem(TbItem tbItem, @RequestParam String desc, @RequestParam String itemParams);
}
