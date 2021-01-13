package com.sim.common.utils;

import com.sim.common.constant.CommonConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author xiaoshun.cxs
 * 2021/1/11
 **/
public class ByteBufUtils {

    public static final String SEPARATOR = "\n";

    /**
     * write String with specify separator, currently default to \n
     * @param message message that need to convert
     * @return ByteBuf after convert
     */
    public static ByteBuf writeStringWithLineBreak(String message) {
        message = message + "\n";
        return Unpooled.copiedBuffer(message, CharsetUtil.UTF_8);
    }

    /**
     * parse from bytebuf and remove separator
     * @param byteBuf request bytebuf
     * @return  String after parse
     */
    public static String fromByteBuf(ByteBuf byteBuf) {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str.substring(0, str.length() - CommonConstant.SEPARATOR.length());
    }

}
