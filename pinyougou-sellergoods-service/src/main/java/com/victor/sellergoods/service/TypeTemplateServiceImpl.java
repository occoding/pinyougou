package com.victor.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.victor.common.PageResult;
import com.victor.dao.TbSpecificationOptionMapper;
import com.victor.dao.TbTypeTemplateMapper;
import com.victor.pojo.TbSpecificationOption;
import com.victor.pojo.TbSpecificationOptionExample;
import com.victor.pojo.TbTypeTemplate;
import com.victor.pojo.TbTypeTemplateExample;
import com.victor.sellergoods.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper typeTemplateMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询全部
     */
    @Override
    public List<TbTypeTemplate> findAll() {
        return typeTemplateMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbTypeTemplate typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbTypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbTypeTemplate findOne(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            typeTemplateMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
        saveToRedis();//存入数据到缓存
        PageHelper.startPage(pageNum, pageSize);

        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();

        if (typeTemplate != null) {
            if (typeTemplate.getName() != null && typeTemplate.getName().length() > 0) {
                criteria.andNameLike("%" + typeTemplate.getName() + "%");
            }
            if (typeTemplate.getSpecIds() != null && typeTemplate.getSpecIds().length() > 0) {
                criteria.andSpecIdsLike("%" + typeTemplate.getSpecIds() + "%");
            }
            if (typeTemplate.getBrandIds() != null && typeTemplate.getBrandIds().length() > 0) {
                criteria.andBrandIdsLike("%" + typeTemplate.getBrandIds() + "%");
            }
            if (typeTemplate.getCustomAttributeItems() != null && typeTemplate.getCustomAttributeItems().length() > 0) {
                criteria.andCustomAttributeItemsLike("%" + typeTemplate.getCustomAttributeItems() + "%");
            }

        }

        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据模板id获取商品规格选项信息列表
     *
     * @param id
     * @return
     */
    public List<Map> findSpecList(Long id) {
        //查询模板
        TbTypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);

        List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
        for (Map map : list) {
            //查询规格选项列表
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            com.victor.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(new Long((Integer) map.get("id")));
            List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
            map.put("options", options);
        }
        return list;
    }


    private void saveToRedis() {
        List<TbTypeTemplate> typeTemplatesList = findAll();
        for (TbTypeTemplate typeTemplate : typeTemplatesList) {
            List<Map> brandList = JSON.parseArray(typeTemplate.getBrandIds(), Map.class);
            redisTemplate.boundHashOps("brandList").put(typeTemplate.getId(), brandList);
            List<Map> specList = findSpecList(typeTemplate.getId());//根据模板ID查询规格列表
            redisTemplate.boundHashOps("specList").put(typeTemplate.getId(), specList);
        }
    }
}
