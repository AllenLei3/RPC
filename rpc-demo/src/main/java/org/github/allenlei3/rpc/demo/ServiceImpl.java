package org.github.allenlei3.rpc.demo;

/**
 * @author xulei
 * @version 0.0.1
 */
public class ServiceImpl implements IService {

    @Override
    public String sayHello() {
        return "Hello RPC";
    }
}
