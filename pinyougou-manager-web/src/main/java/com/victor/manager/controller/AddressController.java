package com.victor.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.victor.common.PageResult;
import com.victor.common.Result;
import com.victor.pojo.TbAddress;
import com.victor.sellergoods.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@Api
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbAddress> findAll() {
        return addressService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        return addressService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param address
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbAddress address) {
        try {
            addressService.add(address);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param address
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbAddress address) {
        try {
            addressService.update(address);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbAddress findOne(@RequestParam("id") Long id) {
        System.out.println(id);
        return addressService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ApiImplicitParam(value = "ids", required = true, paramType = "query", allowMultiple = true, dataType = "Long")
    public Result delete(@RequestParam("ids") Long[] ids) {
        try {
            addressService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbAddress address, int page, int rows) {
        return addressService.findPage(address, page, rows);
    }

}
