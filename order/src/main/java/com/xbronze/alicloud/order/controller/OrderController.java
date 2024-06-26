package com.xbronze.alicloud.order.controller;

import com.xbronze.alicloud.config.NacosLoadBalancerConfig;
import com.xbronze.alicloud.config.RandomLoadBalancerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: xbronze
 * @date: 2024-04-24 14:56
 * @description: TODO
 */
@RestController
@LoadBalancerClient(name = "stock-service", configuration = RandomLoadBalancerConfig.class)
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/say")
    public String sayHello() {
        System.out.println("hello world");
        return "hello world";
    }

    @GetMapping("/stockList")
    public String getOrder() {
        String message = restTemplate.getForObject("http://stock-service/stock/list", String.class);
        return "order " + message;
    }
}
