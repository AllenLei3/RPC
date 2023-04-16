package org.github.allenlei3.rpc.demo.provider;

import org.github.allenlei3.rpc.config.RpcServiceConfig;
import org.github.allenlei3.rpc.demo.IService;
import org.github.allenlei3.rpc.demo.ServiceImpl;

/**
 * @author xulei
 * @version 0.0.1
 */
public class ServiceProviderDemo {

    public static void main(String[] args) {
        RpcServiceConfig<IService> serviceConfig = new RpcServiceConfig<>();
        serviceConfig.setApplicationName("demo");
        serviceConfig.setInterfaceClass(IService.class);
        serviceConfig.setServiceInstance(new ServiceImpl());
        serviceConfig.setVersion("1.0.0");
        serviceConfig.export();
    }
}
