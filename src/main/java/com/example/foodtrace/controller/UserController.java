package com.example.foodtrace.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.foodtrace.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Api("用户登陆相关")
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ID", required = true),
            @ApiImplicitParam(name = "PWD", value = "PWD", required = true),
    })
    public Map<String, Object> register(@RequestParam("ID") String ID, @RequestParam("PWD")String PWD) {
        Map<String, Object> map = new HashMap<>();
        userService.register(ID, PWD);
        map.put("code", 200);
        map.put("description", "注册成功");
        return map;
    }

    @ApiOperation(value = "登陆")
    @PostMapping("doLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ID", required = true),
            @ApiImplicitParam(name = "PWD", value = "PWD", required = true),
    })
    public SaResult doLogin(@RequestParam("ID") String ID, @RequestParam("PWD")String PWD) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        Map<String, Object> ret = new HashMap<>();
        String tmp = userService.logIn(ID);
        if (tmp.equals(PWD)) {
            StpUtil.login(ID);
            return SaResult.data(StpUtil.getTokenInfo().getTokenValue());
        }
        return SaResult.error("登录失败");
    }

    @ApiOperation(value = "测试登陆")
    @GetMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    @ApiOperation(value = "查询token")
    @GetMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    @ApiOperation(value = "注销")
    @PostMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ID", required = true)
    })
    public Map<String, Object> getUserInfo(String ID) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data",userService.getUserInfo(ID));
        ret.put("code",200);
        return ret;
    }

}
