package com.usian.service;

import com.usian.mapper.TbContentCategoryMapper;
import com.usian.pojo.TbContentCategory;
import com.usian.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long id) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        return tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
    }

    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        Date date = new Date();
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        Integer insertSelective=tbContentCategoryMapper.insertSelective(tbContentCategory);
        TbContentCategory tbContentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if (!tbContentCategory1.getIsParent()){
            tbContentCategory1.setIsParent(true);
            tbContentCategory1.setUpdated(new Date());
            int i = tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);
        }

        return insertSelective;
    }

    @Override
    public Integer deleteContentCategoryById(Long categoryId) {
        //查询当前节点
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        //如果是父节点不能删除
        if(tbContentCategory.getIsParent()==true){
            return 0;
        }
        //删除当前节点
        tbContentCategoryMapper.deleteByPrimaryKey(categoryId);
        //与如果他不是父节点 就把他改成不是父节点
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if(tbContentCategoryList==null || tbContentCategoryList.size()==0){
            TbContentCategory parentTbContentCategory = new TbContentCategory();
            parentTbContentCategory.setId(tbContentCategory.getParentId());
            parentTbContentCategory.setIsParent(false);
            parentTbContentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKeySelective(parentTbContentCategory);
        }

        return 200;
    }

    @Override
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
}
