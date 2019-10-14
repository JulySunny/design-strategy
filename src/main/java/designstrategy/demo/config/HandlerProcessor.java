package designstrategy.demo.config;

import designstrategy.demo.config.annotation.HandlerType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 杨强
 * @Date: 2019/10/14 10:33
 * @Version 1.0
 * @Discription
 */
@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Class> handlerMap = new HashMap<>();
        //获取指定报下的所有类
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory();
        List<Class<?>> list = new ArrayList();
        try {
            Resource[] resources = resolver.getResources("classpath*:designstrategy/demo/config/handler/*.class");

            ClassLoader loader = ClassLoader.getSystemClassLoader();
            for (Resource resource : resources) {
                MetadataReader reader = metaReader.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> clazz = loader.loadClass(className);
                HandlerType annotation = clazz.getAnnotation(HandlerType.class);
                // System.out.println("格式化前:"+clazz); class designstrategy.demo.config.handler.AbstractHandler
                System.err.println("格式化后:" + ClassUtils.getQualifiedName(clazz));
                //获取所有接口
                ClassUtils.getAllInterfaces(clazz);
                //判断是一个类是不是抽象类
                boolean anAbstract = Modifier.isAbstract(clazz.getModifiers());
//                System.err.println("判断一个类是不是抽象类:" + anAbstract);如果不是抽象类,添加到集合
                if (!anAbstract) {
                    list.add(clazz);
                }
            }
            System.out.println("集合" + list);
            //将类添加到Map中
            for (Class<?> aClass : list) {
                Annotation[] annotations = aClass.getAnnotations();
//              这里有个坑  Annotation annotation = aClass.getAnnotation(HandlerType.class);获取到的annotation为null
                for (Annotation annotation : annotations) {
                    if (annotation instanceof HandlerType) {
                        String value = ((HandlerType) annotation).value();
                        handlerMap.put(value, aClass);
                        System.out.println(value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HandleContext handleContext = new HandleContext(handlerMap);
        //将上下文添加到spring bean容器中
        configurableListableBeanFactory.registerSingleton(HandleContext.class.getName(), handleContext);
    }
}
