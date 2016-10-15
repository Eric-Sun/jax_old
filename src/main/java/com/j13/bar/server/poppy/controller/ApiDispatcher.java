package com.j13.bar.server.poppy.controller;

import com.google.common.collect.Lists;
import com.j13.bar.server.core.log.LOG;
import com.j13.bar.server.poppy.ErrorResponse;
import com.j13.bar.server.poppy.PoppyResponseCode;
import com.j13.bar.server.poppy.RequestData;
import com.j13.bar.server.poppy.core.ActionMethodInfo;
import com.j13.bar.server.poppy.core.ActionServiceLoader;
import com.j13.bar.server.poppy.core.ParameterInfo;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class ApiDispatcher {
    private static Logger LOG = LoggerFactory.getLogger(ApiDispatcher.class);

    @Autowired
    ActionServiceLoader actionServiceLoader;


    public Object dispatch(String act, RequestData requestData) {

        Map<String, ActionMethodInfo> maps = actionServiceLoader.getActionInfoMap();
        ActionMethodInfo ami = maps.get(act);
        if (ami == null) {
            return new ErrorResponse(PoppyResponseCode.NOT_FOUND_ACTION);
        }

        Method actionMethod = ami.getActionMethod();
        LOG.info("Founded. method = {}", actionMethod.getName());
        Object beanObject = ami.getServiceObject();
        List<ParameterInfo> parameterInfoList = ami.getParamList();
        List<Object> inputParams = Lists.newLinkedList();

        for (ParameterInfo pi : parameterInfoList) {
            LOG.info(" type = {}, name = {}", pi.getType(), pi.getName());
            inputParams.add(convertByType(pi.getType(), requestData, pi.getName()));
        }

        try {
            return actionMethod.invoke(beanObject, inputParams.toArray());
        } catch (IllegalAccessException e) {
            return new ErrorResponse(PoppyResponseCode.ACTION_REFLECT_ERROR);
        } catch (InvocationTargetException e) {
            return new ErrorResponse(PoppyResponseCode.ACTION_REFLECT_ERROR);
        }
    }


    private Object convertByType(Type type, RequestData requestData, String name) {

        if (type.equals(FileItem.class)) {
            return requestData.getFileItem();
        }
        Object value = requestData.getData().get(name);
        if (type.equals(String.class)) {
            return value;
        } else if (type.equals(Integer.class)) {
            return new Integer(value.toString()).intValue();
        } else if (type.equals(Long.class)) {
            return new Long(value.toString()).longValue();
        } else if (type.equals(FileItem.class)) {
            return (FileItem) value;
        } else
            return null;
    }

}
