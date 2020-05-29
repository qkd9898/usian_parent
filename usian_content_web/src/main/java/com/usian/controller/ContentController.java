package com.usian.controller;

import com.usian.feign.ContentServiceFeign;
import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/content")
public class ContentController {

    @Autowired
    private ContentServiceFeign contentServiceFeign;

    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(Long categoryId, @RequestParam(defaultValue = "1") Integer Page, @RequestParam(defaultValue = "30") Integer rows){

       PageResult pageResult =contentServiceFeign.selectTbContentAllByCategoryId(categoryId,Page,rows);
       if (pageResult.getResult().size()>0){
           return Result.ok(pageResult);
       }
        return Result.error("显示错误");
    }

    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent) {

        Integer  insertTbContent = contentServiceFeign.insertTbContent(tbContent);
        if (insertTbContent==1){
            return Result.ok();
        }
        return Result.error("添加错误");
    }

    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){

        Integer deleteContentByIds =contentServiceFeign.deleteContentByIds(ids);
        if (deleteContentByIds==1){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
