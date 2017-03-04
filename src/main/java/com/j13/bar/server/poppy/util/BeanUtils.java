package com.j13.bar.server.poppy.util;

import com.j13.bar.server.poppy.exceptions.CommonException;
import com.j13.bar.server.core.ErrorCode;

public class BeanUtils {

    public static void copyProperties(Object dest, Object origin) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, origin);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.System.REFLECT_ERROR);
        }
    }
}
