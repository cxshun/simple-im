package test.com.sim.server.processor;

import com.alibaba.fastjson.JSON;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.user.SingleChatMsg;
import com.sim.common.utils.ByteBufUtils;
import com.sim.server.modules.command.CommandType;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import test.com.sim.server.ImServerApplicationTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
public class SingleChatTest extends ImServerApplicationTest {

    @Test
    public void test() {
        List<EmbeddedChannel> embeddedChannelList = Arrays.asList(getChannel(), getChannel());
        withLoginUser(embeddedChannelList);

        embeddedChannelList.get(0).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<SingleChatMsg>()
                                        .setAction(CommandType.SINGLE_CHAT.getMsgType().getPrefix())
                                        .setMsg((SingleChatMsg) new SingleChatMsg().setMsg("hello from shun1").setLoginId("shun2"))
                        )
                )
        );
        embeddedChannelList.get(0).readOutbound();
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(1).readOutbound());
        Assert.assertEquals("hello from shun1", message);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
        getJdbcTemplate().execute("delete from `group`");
    }

}
