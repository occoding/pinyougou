package com.pinyougou.solrutil;

import com.alibaba.fastjson.JSON;
import com.victor.dao.TbItemMapper;
import com.victor.pojo.TbItem;
import com.victor.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SolrUtils {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    public void importItemData() {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        List<TbItem> itemList = itemMapper.selectByExample(example);
        for (TbItem item : itemList) {
            System.out.println(item.getTitle());
            Map specmap = JSON.parseObject(item.getSpec(), Map.class);
            item.setSpecMap(specmap);
        }
        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
        SolrUtils solrUtil = (SolrUtils) context.getBean("solrUtils");
        solrUtil.importItemData();
    }
}
