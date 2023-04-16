package org.github.allenlei3.rpc.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xulei
 * @version 0.0.1
 */
@Getter
@Setter
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

}
