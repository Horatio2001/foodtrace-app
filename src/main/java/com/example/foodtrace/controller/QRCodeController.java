package com.example.foodtrace.controller;

import com.example.foodtrace.util.QRCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Api("二维码相关")
@RequestMapping("/QRCode/")
public class QRCodeController {

    @ApiOperation("生成二维码")
    @PostMapping("generatingQRCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ID", required = true)
    })
    public Map<String, Object> generatingQRCode(String ID) {
        Map<String, Object> ret = new HashMap<>();
        String a = "http://101.43.206.180/#/AppTracing/index?Hash=" + ID;
        try {
            String path = "/www/wwwroot/101.43.206.180/QRCode";
            QRCodeUtils.createQRCode(path, a, ID);
            ret.put("code", 200);
            ret.put("description", "二维码生成成功");
        } catch (Exception e) {
            ret.put("code", -200);
            ret.put("description", "二维码生成错误");
        }
        return ret;
    }
}
