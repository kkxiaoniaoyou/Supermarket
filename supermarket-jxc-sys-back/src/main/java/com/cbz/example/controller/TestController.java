package com.cbz.example.controller;

import cn.hutool.http.HttpResponse;
import com.cbz.example.service.impl.ThirdPartyApiCaller;
import com.cbz.example.service.impl.ThirdPartyLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 11272
 * @description: TODO
 * @date 2024/1/10 14:36
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ThirdPartyApiCaller thirdPartyApiCaller;

    @RequestMapping("/demo1")
    public Map<String, Object> test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "成功");
        return map;
    }

    @RequestMapping("/demo2")
    public Map<String, Object> test2() {
        HttpResponse h = thirdPartyApiCaller.sendGetRequest("http://jsonplaceholder.typicode.com/posts", "", "测试");
        System.out.println(h.body());
        return null;
    }
}
