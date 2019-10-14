package designstrategy.demo.config.handler;

import designstrategy.demo.config.annotation.HandlerType;
import designstrategy.demo.entity.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:29
 * @Version 1.0
 * @Discription
 */
@HandlerType(value = "2")
@Component
public class GroupHandler extends AbstractHandler{
    @Override
    public String handle(OrderDTO dto) {
        return "group";
    }
}
