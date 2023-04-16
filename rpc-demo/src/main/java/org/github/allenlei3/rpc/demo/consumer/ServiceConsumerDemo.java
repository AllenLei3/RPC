package org.github.allenlei3.rpc.demo.consumer;

import org.github.allenlei3.rpc.config.RpcReferenceConfig;
import org.github.allenlei3.rpc.demo.IService;

/**
 * @author xulei
 * @version 0.0.1
 */
public class ServiceConsumerDemo {

    public static void main(String[] args) {
        RpcReferenceConfig<IService> referenceConfig = new RpcReferenceConfig<>();
        referenceConfig.setApplicationName("demo");
        referenceConfig.setInterfaceClass(IService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setTimeout(10000);

        IService service = referenceConfig.get();
        service.sayHello();
    }
}
