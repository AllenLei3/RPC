package org.github.allenlei3.rpc.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author xulei
 * @version 0.0.1
 */
public class NetUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetUtils.class);

    public static final String LOCAL_HOST = "127.0.0.1";
    public static final String ANY_HOST = "0.0.0.0";
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 65535;
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static volatile InetAddress LOCAL_ADDRESS = null;

    private static final int RND_PORT_START = 30000;
    private static final int RND_PORT_RANGE = 10000;
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    /**
     * 获取本地可用端口
     */
    public static int getAvailablePort() {
        try (ServerSocket ss = new ServerSocket()) {
            ss.bind(null);
            return ss.getLocalPort();
        } catch (IOException e) {
            return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
        }
    }

    /**
     * 校验端口号是否非法
     */
    public static boolean isInvalidPort(int port) {
        return port <= MIN_PORT || port > MAX_PORT;
    }

    /**
     * 获取本机IP地址
     */
    public static String getLocalHost() {
        InetAddress address = getLocalAddress();
        return address == null ? LOCAL_HOST : address.getHostAddress();
    }

    /**
     * 获取本机IP地址
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            LOGGER.warn(e.getMessage(), e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            try {
                                InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                                LOGGER.warn(e.getMessage(), e);
                            }
                        }
                    } catch (Throwable e) {
                        LOGGER.warn(e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return localAddress;
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null
                && !ANY_HOST.equals(name)
                && !LOCAL_HOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }
}
