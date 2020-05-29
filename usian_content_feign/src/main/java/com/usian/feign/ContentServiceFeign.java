package com.usian.feign;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.ResponseWrapper;
import java.util.List;

@FeignClient("usian-content-service")
public interface ContentServiceFeign {

    @RequestMapping("/service/content/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long id);

    @RequestMapping("/service/content/insertContentCategory")
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    @RequestMapping("/service/content/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam Long categoryId);

    @RequestMapping("/service/content/updateContentCategory")
    Integer updateContentCategory(TbContentCategory tbContentCategory);

    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam Long categoryId, @RequestParam Integer page,@RequestParam Integer rows);

    @RequestMapping("/service/content/insertTbContent")
    Integer insertTbContent(TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam Long ids);

    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<AdNode> selectFrontendContentByAD();
}
