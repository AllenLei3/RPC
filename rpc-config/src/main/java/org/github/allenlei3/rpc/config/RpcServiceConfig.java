package org.github.allenlei3.rpc.config;

/**
 * RPC服务提供方配置类
 *
 * @author xulei
 * @version 0.0.1
 */
public class RpcServiceConfig<SERVICE> extends AbstractRpcConfig<SERVICE> {

    /**
     * 服务真正提供者实例
     */
    private SERVICE serviceInstance;

    /**
     * 服务提供方地址
     */
    private String ip;
    private Integer port;

    protected RpcServiceConfig() {
    }

    public SERVICE getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(SERVICE serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private static class ServiceBuilder<SERVICE> {
        private Class<SERVICE> interfaceClass;
        private SERVICE serviceInstance;
        private String group = "DEFAULT_GROUP";
        private String version = "1.0.0";
        private RegistryConfig registryConfig;
        private String proxy;
        private String protocol;
        private String threadPool;
        private String serialization;

        public ServiceBuilder<SERVICE> interfaceClass(Class<SERVICE> interfaceClass) {
            this.interfaceClass = interfaceClass;
            return this;
        }

        public ServiceBuilder<SERVICE> serviceInstance(SERVICE serviceInstance) {
            this.serviceInstance = serviceInstance;
            return this;
        }

        public ServiceBuilder<SERVICE> group(String group) {
            this.group = group;
            return this;
        }

        public ServiceBuilder<SERVICE> version(String version) {
            this.version = version;
            return this;
        }

        public ServiceBuilder<SERVICE> registry(RegistryConfig registryConfig) {
            this.registryConfig = registryConfig;
            return this;
        }

        public ServiceBuilder<SERVICE> proxy(String proxy) {
            this.proxy = proxy;
            return this;
        }

        public ServiceBuilder<SERVICE> protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ServiceBuilder<SERVICE> threadPool(String threadPool) {
            this.threadPool = threadPool;
            return this;
        }

        public ServiceBuilder<SERVICE> serialization(String serialization) {
            this.serialization = serialization;
            return this;
        }

        public RpcServiceConfig<SERVICE> build() {
            RpcServiceConfig<SERVICE> serviceConfig = new RpcServiceConfig<>();
            serviceConfig.setInterfaceClass(interfaceClass);
            serviceConfig.setServiceInstance(serviceInstance);
            serviceConfig.setGroup(group);
            serviceConfig.setVersion(version);
            serviceConfig.setRegistryConfig(registryConfig);
            serviceConfig.setProxy(proxy);
            serviceConfig.setProtocol(protocol);
            serviceConfig.setThreadPool(threadPool);
            serviceConfig.setSerialization(serialization);
            return serviceConfig;
        }
    }
}
