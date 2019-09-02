package com.victor.sellergoods.service;

import com.victor.common.PageResult;
import com.victor.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<TbBrand> findAll();

    PageResult findPage(int pageNum, int pageSize);

    void add(TbBrand brand);

    void update(TbBrand brand);

    TbBrand findOne(Long id);

    void delete(Long[] ids);

    public PageResult search(TbBrand brand, int pageNum, int pageSize);

    public List<Map> selectOptionList();


}
