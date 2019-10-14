package designstrategy.demo.controller;

import designstrategy.demo.entity.OrderDTO;
import designstrategy.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:21
 * @Version 1.0
 * @Discription
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/handle")
    public String handle(OrderDTO dto){
        String str=orderService.handle(dto);
        System.out.println(str);
        return str;
    }
}
