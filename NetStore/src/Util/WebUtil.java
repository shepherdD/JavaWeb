package Util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class WebUtil {
    public static <T> T fillBean(HttpServletRequest request, Class<T> class1){
        try {
            T bean = class1.newInstance();
            BeanUtils.populate(bean,request.getParameterMap());
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
