package com.sim.common.msg.parser;

import com.sim.common.exception.BizException;
import com.sim.common.exception.MessageCode;
import com.sim.common.msg.MsgType;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.MsgSpec;
import com.sim.common.utils.StringUtils;

/**
 * @author xiaoshun.cxs
 * 2021/1/18
 **/
public class ParserFactory {

    public static MsgParams<MsgSpec> parse(String message) throws BizException, IllegalAccessException, InstantiationException {
        String[] strs = message.split(" ");

        MsgType msgType = MsgType.getByPrefix(strs[0]);
        if (msgType == null) {
            throw new BizException(MessageCode.CUSTOM_ERROR, StringUtils.format("message:{} not recognize", message));
        }
        MsgParams<MsgSpec> msgParams = new MsgParams<>();
        msgParams.setAction(msgType.getPrefix());
        msgParams.setMsg(msgType.getMsgSpec().newInstance().parse(message));
        return msgParams;
    }

}
