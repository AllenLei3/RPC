package org.github.allenlei3.rpc.config;

import lombok.Getter;
import lombok.Setter;
import org.github.allenlei3.rpc.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author xulei
 * @version 0.0.1
 */
@Getter
@Setter
public abstract class AbstractRpcConfig<SERVICE> implements Serializable {

    private static final long serialVersionUID = 4267533505537413570L;
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRpcConfig.class);

    /**
     * 应用名称
     */
    protected String applicationName;

    /**
     * 服务接口
     */
    protected Class<SERVICE> interfaceClass;

    /**
     * 分组
     */
    protected String group = Constants.DEFAULT_GROUP;

    /**
     * 版本号
     */
    protected String version = Constants.DEFAULT_VERSION;

    /**
     * 注册中心配置
     */
    protected RegistryConfig registryConfig;

    /**
     * 代理策略
     */
    protected String proxy;

    /**
     * 协议策略
     */
    protected String protocol;

    /**
     * 线程池策略
     */
    protected String threadPool;

    /**
     * 序列化策略
     */
    protected String serialization;

    protected void checkConfig() {
        if (applicationName == null || applicationName.length() == 0) {
            throw new IllegalArgumentException("applicationName not allow null or blank!");
        }
        if (interfaceClass == null) {
            throw new IllegalArgumentException("interface not allow null!");
        }
        if (registryConfig == null) {
            throw new IllegalArgumentException("registry not allow null!");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalStateException("The interface class " + interfaceClass + " is not a interface!");
        }
    }

    /**
     * 获取服务唯一标识
     */
    public String getUniqueServiceName() {
        return group + "/" + interfaceClass.getName() + ":" + version;
    }

    @Override
    public String toString() {
        try {
            StringBuilder buf = new StringBuilder();
            buf.append("<");
            Method[] methods = getClass().getMethods();
            for (Method method : methods) {
                try {
                    String name = method.getName();
                    if ((name.startsWith("get") || name.startsWith("is"))
                            && !"get".equals(name) && !"is".equals(name)
                            && !"getClass".equals(name) && !"getObject".equals(name)
                            && Modifier.isPublic(method.getModifiers())
                            && method.getParameterTypes().length == 0
                            && isPrimitive(method.getReturnType())) {
                        int i = name.startsWith("get") ? 3 : 2;
                        String key = name.substring(i, i + 1).toLowerCase() + name.substring(i + 1);
                        Object value = method.invoke(this);
                        if (value != null) {
                            buf.append(" ");
                            buf.append(key);
                            buf.append("=\"");
                            buf.append(value);
                            buf.append("\"");
                        }
                    }
                } catch (Exception e) {
                    LOGGER.warn(e.getMessage(), e);
                }
            }
            buf.append(" />");
            return buf.toString();
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
            return super.toString();
        }
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive()
                || type == String.class
                || type == Character.class
                || type == Boolean.class
                || type == Byte.class
                || type == Short.class
                || type == Integer.class
                || type == Long.class
                || type == Float.class
                || type == Double.class
                || type == Object.class;
    }

}
