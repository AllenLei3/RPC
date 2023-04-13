package org.github.allenlei3.rpc.config;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xulei
 * @version 0.0.1
 */
public abstract class AbstractRpcConfig<SERVICE> implements Serializable {

    private static final long serialVersionUID = 4267533505537413570L;

    /**
     * 服务接口
     */
    protected Class<SERVICE> interfaceClass;

    /**
     * 分组
     */
    protected String group;

    /**
     * 版本号
     */
    protected String version;

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

    public Class<SERVICE> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<SERVICE> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(String threadPool) {
        this.threadPool = threadPool;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }
}
