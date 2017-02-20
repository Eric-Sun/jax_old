package com.j13.bar.server.utils;

import com.j13.bar.server.exceptions.CommonException;
import com.j13.bar.server.exceptions.ErrorCode;

public class BeanUtils {

    public static void copyProperties(Object dest, Object origin) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, origin);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.System.REFLECT_ERROR);
        }
    }
}
