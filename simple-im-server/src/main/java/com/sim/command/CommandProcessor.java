package com.sim.command;

/**
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
public interface CommandProcessor {

    /**
     * 处理用户输入的命令
     * @param command  用户输入的命令
     * @return 返回结果
     */
    String process(String command);

}
