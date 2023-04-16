package org.github.allenlei3.rpc.config;

import lombok.Getter;
import org.github.allenlei3.rpc.common.Constants;
import org.github.allenlei3.rpc.common.utils.NetUtils;

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
    @Getter
    private SERVICE serviceInstance;

    /**
     * 服务提供方地址
     */
    @Getter
    private Integer port = Constants.DEFAULT_PORT;
    @Getter
    private final String ip = NetUtils.getLocalHost();

    private transient volatile boolean exported;

    /**
     * 发布服务
     */
    public synchronized void export() {
        if (exported) {
            return;
        }
        checkConfig();
        exported = true;

        // 动态代理生产Invoker
        // 发布到注册中心
    }

    @Override
    protected void checkConfig() {
        super.checkConfig();
        setServiceInstance(serviceInstance);
        setPort(port);
    }

    public void setServiceInstance(SERVICE serviceInstance) {
        if (serviceInstance == null) {
            throw new IllegalArgumentException("serviceInstance not allow null!");
        }
        if (!interfaceClass.isInstance(serviceInstance)) {
            throw new IllegalStateException("The class " + serviceInstance.getClass().getName() +
                    " unimplemented interface " + interfaceClass + "!");
        }
        this.serviceInstance = serviceInstance;
    }

    public void setPort(Integer port) {
        if (port > 0 && NetUtils.isInvalidPort(port)) {
            throw new IllegalArgumentException("Specified invalid port :" + port);
        }
        if (port < 0) {
            port = NetUtils.getAvailablePort();
        }
        this.port = port;
    }
}
