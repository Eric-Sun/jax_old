package com.j13.bar.server.poppy.core;

import com.google.common.collect.Maps;
import com.j13.bar.server.poppy.anno.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

@Service
public class ActionServiceLoader implements ApplicationContextAware {

    private static Logger LOG = LoggerFactory.getLogger(ActionServiceLoader.class);

    private ApplicationContext applicationContext;

    public Map<String, ActionMethodInfo> getActionInfoMap() {
        return actionInfoMap;
    }

    private Map<String, ActionMethodInfo> actionInfoMap = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void loadActionInfos() {
        try {
            LOG.info("start loading actions.");
            Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(Service.class);
            for (String serviceName : beansMap.keySet()) {
                Class clazz2 = beansMap.get(serviceName).getClass();
                Object beanObject = beansMap.get(serviceName);
                Method[] methods2 = clazz2.getDeclaredMethods();
                LOG.info("Loading service . name = {}", serviceName);
                for (int i = 0; i < methods2.length; i++) {
                    Method m2 = methods2[i];

                    Action anno = (Action) m2.getAnnotation(Action.class);
                    if (anno != null) {
                        String name = anno.name();

                        ActionMethodInfo ami = new ActionMethodInfo();
                        ami.setActionMethod(m2);
                        ami.setActionName(name);
                        ami.setServiceObject(beanObject);
                        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
                        String[] parameterNames = pnd.getParameterNames(m2);
                        Class[] types = m2.getParameterTypes();

                        String[] paramNames = new String[parameterNames.length];
                        LOG.info("Loading method . action = {},name = {}, params size = {}",
                                new Object[]{name, m2.getName(), paramNames.length});
                        for (int i2 = 0; i2 < paramNames.length; i2++) {
                            String pn = parameterNames[i2];
                            ParameterInfo pi = new ParameterInfo();
                            pi.setName(pn);
                            pi.setType(types[i2]);
                            ami.getParamList().add(pi);
                            LOG.info("loadding params. name={}, type={}, pos={}", new Object[]{pn, types[i2], i2});
                        }

                        if (actionInfoMap.get(name) != null) {
                            LOG.info("action has been existed. name={}", name);
                        }
                        actionInfoMap.put(name, ami);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


