package com.sim.command.spec;

import com.sim.command.CommandProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Help命令的处理逻辑
 * @author xiaoshun.cxs
 * 2020/12/8
 **/
public class HelpCommandProcessor implements CommandProcessor {
    @Override
    public String process(String command) {
        List<String> commandList = Arrays.asList(
                ":chat[(uid)]",
                ":join(groupId)",
                ":quit(groupId)",
                ":ouList",
                ":groupList"
        );

        return String.join("\n", commandList);
    }
}
