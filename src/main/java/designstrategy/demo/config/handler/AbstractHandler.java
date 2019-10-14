package designstrategy.demo.config.handler;

import designstrategy.demo.entity.OrderDTO;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:31
 * @Version 1.0
 * @Discription
 */
public abstract class AbstractHandler {

    public  abstract String handle(OrderDTO dto);
}
