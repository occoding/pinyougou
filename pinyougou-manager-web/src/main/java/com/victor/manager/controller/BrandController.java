package com.victor.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.victor.common.PageResult;
import com.victor.common.Result;
import com.victor.pojo.TbBrand;
import com.victor.sellergoods.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
@Api("品牌接口")
public class BrandController {
    @Reference
    private BrandService brandService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @ApiOperation("找到所有品牌")
    @RequestMapping(value = "/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    /**
     * @param page（pageNum当前页码）
     * @param rows（pageSize页内含的记录数）
     * @return
     */
    @RequestMapping(value = "/findPage")
    @ApiOperation("分页查找所有品牌")
    public PageResult findPage(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        System.out.println(page + rows);
        return brandService.findPage(page, rows);
    }

    /**
     * 添加品牌
     */
    @RequestMapping(value = "/add")
    @ApiOperation("添加品牌")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }


    @RequestMapping(value = "/update")
    @ApiOperation("更新品牌")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ApiOperation("根据用户id数组集获得用户集合信息")
    @ApiImplicitParam(value = "ids", required = true, paramType = "query", allowMultiple = true, dataType = "Long")
    public Result delete(@RequestParam("ids") Long[] ids) {
        try {
            System.out.println("congtroller delete" + ids);
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }


    @RequestMapping("/findOne")
    public TbBrand findOne(@RequestParam("id") Long id) {
        return brandService.findOne(id);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, int page, int rows) {
        return brandService.search(brand, page, rows);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }
}
