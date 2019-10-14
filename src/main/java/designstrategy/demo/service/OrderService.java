package designstrategy.demo.service;

import designstrategy.demo.entity.OrderDTO;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:23
 * @Version 1.0
 * @Discription
 */
public interface OrderService {
    String handle(OrderDTO dto);
}
