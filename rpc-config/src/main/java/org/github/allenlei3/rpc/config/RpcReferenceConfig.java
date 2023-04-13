package org.github.allenlei3.rpc.config;

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
    protected Integer timeout;

    /**
     * 失败重试次数
     */
    protected Integer retries;

    /**
     * 集群容错策略
     */
    protected String cluster;

    /**
     * 路由策略
     */
    protected String router;

    /**
     * 负载均衡策略
     */
    protected String loadBalance;

    /**
     * 是否缓存RPC调用的响应信息
     */
    protected Boolean enableCache;

    /**
     * 缓存策略
     */
    protected String cache;

    /**
     * 获取引用的RPC服务实例
     */
    public SERVICE getInstance() {
        return null;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getLoadBalance() {
        return loadBalance;
    }

    public void setLoadBalance(String loadBalance) {
        this.loadBalance = loadBalance;
    }

    public Boolean getEnableCache() {
        return enableCache;
    }

    public void setEnableCache(Boolean enableCache) {
        this.enableCache = enableCache;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }
}
