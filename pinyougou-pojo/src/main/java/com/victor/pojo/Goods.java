package com.victor.pojo;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

    private TbGoods goods;
    private TbGoodsDesc goodsDesc;
    private List<TbItem> goodsItems;

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<TbItem> goodsItems) {
        this.goodsItems = goodsItems;
    }
}
