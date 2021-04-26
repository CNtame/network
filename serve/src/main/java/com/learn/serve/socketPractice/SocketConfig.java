package com.learn.serve.socketPractice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/25
 */
@Component
@ConfigurationProperties(prefix = "socket.serve")
public class SocketConfig {

    private static Integer port;

    public static Integer getPort() {
        return port;
    }


}
