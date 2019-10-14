package designstrategy.demo.config;

import designstrategy.demo.config.handler.AbstractHandler;
import designstrategy.demo.utils.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:28
 * @Version 1.0
 * @Discription 上下文
 */
public class HandleContext {

    private Map<String,Class> handlerMap;

    public HandleContext(Map<String,Class> handlerMap){
        this.handlerMap=handlerMap;
    }

    public AbstractHandler getInstance(String type){
        Class<? extends AbstractHandler> aClass = handlerMap.get(type);
        if (aClass==null) {
            throw new IllegalArgumentException();
        }
        return SpringContextUtil.getBean(aClass);
    }
}
