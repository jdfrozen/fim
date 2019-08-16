package com.frozen.fimserver.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Function:
 *
 * @author frozen
 *         Date: 22/05/2018 18:33
 * @since JDK 1.8
 */
public class SessionSocketHolder {
    private static final Set<NioSocketChannel> ALLUSER_CHANNEL_SET = new HashSet<>();
    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    synchronized public static void   add(NioSocketChannel socketChannel) {
        ALLUSER_CHANNEL_SET.add( socketChannel);
    }

    synchronized public static void removeSet(NioSocketChannel socketChannel) {
        ALLUSER_CHANNEL_SET.remove( socketChannel);
    }

    public static void put(Long id, NioSocketChannel socketChannel) {
        CHANNEL_MAP.put(id, socketChannel);
    }

    public static NioSocketChannel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

    public static Map<Long, NioSocketChannel> getMAP() {
        return CHANNEL_MAP;
    }

    public static Set< NioSocketChannel> getSet() {
        return ALLUSER_CHANNEL_SET;
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }

}
