package org.github.allenlei3.rpc.config;

import lombok.Getter;
import lombok.Setter;
import org.github.allenlei3.rpc.common.Constants;

/**
 * RPC服务引用方配置类
 *
 * @author xulei
 * @version 0.0.1
 */
public class RpcReferenceConfig<SERVICE> extends AbstractRpcConfig<SERVICE> {

    /**
     * PRC调用超时时间
     * 单位:毫秒
     */
    @Getter
    @Setter
    private Integer timeout = Constants.DEFAULT_TIMEOUT;

    /**
     * 失败重试次数
     */
    @Getter
    @Setter
    private Integer retries = Constants.DEFAULT_RETRIES;

    /**
     * 集群容错策略
     */
    @Getter
    @Setter
    private String cluster;

    /**
     * 路由策略
     */
    @Getter
    @Setter
    private String router;

    /**
     * 负载均衡策略
     */
    @Getter
    @Setter
    private String loadBalance;

    /**
     * 是否缓存RPC调用的响应信息
     */
    @Getter
    @Setter
    private Boolean enableCache = Constants.DEFAULT_ENABLE_CACHE;

    /**
     * 缓存策略
     */
    @Getter
    @Setter
    private String cache;

    private transient volatile boolean initialized;
    private transient volatile SERVICE reference;

    /**
     * 获取服务引用实例
     */
    public synchronized SERVICE get() {
        checkConfig();
        if (reference == null) {
            init();
        }
        return reference;
    }

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
    }

    @Override
    protected void checkConfig() {
        super.checkConfig();
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout must more than 0!");
        }
    }
}
