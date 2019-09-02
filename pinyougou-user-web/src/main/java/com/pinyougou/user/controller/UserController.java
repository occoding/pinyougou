package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.user.service.UserService;
import com.victor.common.PageResult;
import com.victor.common.PhoneFormatCheckUtils;
import com.victor.common.Result;
import com.victor.pojo.TbUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbUser> findAll() {
        return userService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return userService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbUser user,String smsCode) {
        if(!checkSmsCode(user.getPhone(),smsCode)){
            return new Result(false,"验证码错误");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Password);
        try {
            userService.add(user);
            return new Result(true, "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "注册失败");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbUser user) {
        try {
            userService.update(user);
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
    public TbUser findOne(Long id) {
        return userService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            userService.delete(ids);
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
    public PageResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.findPage(user, page, rows);
    }

    @RequestMapping("/sendSmsCode")
    public Result sendSmsCode(String phone) {
        System.out.println(phone);
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
            System.out.println("进入contoller");
            return new Result(false, "手机号码格式不正确");
        }
        try {
            userService.createSmsCode(phone);
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "验证码发送失败");
        }
    }

    private boolean checkSmsCode(String phone,String code){
        System.out.println("checkSmsCode"+phone +code);
        if(StringUtils.isBlank(phone)||StringUtils.isBlank(code)){
            return false;
        }
        boolean checkResult=userService.checkSmsCode(phone,code);
        if(checkResult){
            return true;
        }
        return false;
    }
}
