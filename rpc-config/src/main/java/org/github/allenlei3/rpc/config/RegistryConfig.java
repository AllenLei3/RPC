package org.github.allenlei3.rpc.config;

import java.io.Serializable;

/**
 * @author xulei
 * @version 0.0.1
 */
public class RegistryConfig implements Serializable {

    private static final long serialVersionUID = 5508512956753757169L;

    /**
     * 注册中心类型
     */
    private String type;

    /**
     * 注册中心地址
     */
    private String address;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
