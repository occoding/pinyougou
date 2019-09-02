package com.pinyougou.search.service;

import com.victor.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {
    public Map<String, Object> search(Map searchMap);

    void importList(List<TbItem> list);

    void deleteByGoodsIds(List<Long> asList);
}
