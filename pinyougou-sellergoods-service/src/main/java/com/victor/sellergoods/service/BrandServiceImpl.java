package com.victor.sellergoods.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.victor.common.PageResult;
import com.victor.dao.TbBrandMapper;
import com.victor.pojo.TbBrand;
import com.victor.pojo.TbBrandExample;
import com.victor.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    public PageResult findPage(int pageNum, int pageSize) {
        System.out.println("chazao page");
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    public void delete(Long[] ids) {
        for (Long id : ids) {
            System.out.println(id);
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    public PageResult search(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (brand.getName() != null && brand.getName().length() > 0) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
