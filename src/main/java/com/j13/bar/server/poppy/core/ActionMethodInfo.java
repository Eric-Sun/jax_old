package com.j13.bar.server.poppy.core;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.List;

public class ActionMethodInfo {
    private Object serviceObject;
    private Method actionMethod;
    private String actionName;
    private List<ParameterInfo> paramList = Lists.newLinkedList();

    public List<ParameterInfo> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParameterInfo> paramList) {
        this.paramList = paramList;
    }

    public Object getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(Object serviceObject) {
        this.serviceObject = serviceObject;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
