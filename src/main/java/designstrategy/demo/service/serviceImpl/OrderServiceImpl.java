package designstrategy.demo.service.serviceImpl;

import designstrategy.demo.config.HandleContext;
import designstrategy.demo.config.handler.AbstractHandler;
import designstrategy.demo.entity.OrderDTO;
import designstrategy.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:23
 * @Version 1.0
 * @Discription
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private HandleContext handleContext;

    @Override
    public String handle(OrderDTO dto) {
        AbstractHandler handler = handleContext.getInstance(dto.getType());
        return handler.handle(dto);
    }
}
