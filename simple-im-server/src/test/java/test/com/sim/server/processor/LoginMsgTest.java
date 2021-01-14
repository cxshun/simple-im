package test.com.sim.server.processor;

import com.alibaba.fastjson.JSON;
import com.sim.common.utils.ByteBufUtils;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.user.LoginMsg;
import com.sim.server.modules.command.CommandType;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import test.com.sim.server.ImServerApplicationTest;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
public class LoginMsgTest extends ImServerApplicationTest {

    @Test
    public void test() {
        EmbeddedChannel channel = getChannel();
        channel.writeInbound(ByteBufUtils.writeStringWithLineBreak(
                JSON.toJSONString(
                        new MsgParams<>(CommandType.LOGIN.getType(), (LoginMsg)new LoginMsg().setPassword("1234").setLoginId("shun"))
                )
            )
        );

        //first return connection active message
        channel.readOutbound();
        String str = ByteBufUtils.fromByteBuf(channel.readOutbound());
        Assert.assertEquals("success", str);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
    }

}
