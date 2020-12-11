package com.sim.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoshun.cxs
 * 2020/12/10
 **/
@Slf4j
public class StringUtils {

    private static final Pattern PATTERN = Pattern.compile("\\{\\}");

    /**
     * format message with placeholder {}
     * @param message message that waiting for formatting
     * @param args    args waiting for replace
     * @return formatted message
     */
    public static String format(String message, Object... args) {
        Matcher matcher = PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        while(matcher.find()) {
            if (idx >= args.length) {
                log.error("current message:[{}] with placeholder at idx:[{}] doesn't have replacement, args:[{}]",
                        message, idx, args);
                continue;
            }
            matcher.appendReplacement(sb, args[idx ++].toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
