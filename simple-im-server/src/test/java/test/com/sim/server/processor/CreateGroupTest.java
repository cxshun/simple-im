package test.com.sim.server.processor;

import com.alibaba.fastjson.JSON;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.CreateGroupMsg;
import com.sim.common.utils.ByteBufUtils;
import com.sim.server.modules.command.CommandType;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import test.com.sim.server.ImServerApplicationTest;

import java.util.Collections;
import java.util.List;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
public class CreateGroupTest extends ImServerApplicationTest {

    @Test
    public void test() {
        List<EmbeddedChannel> embeddedChannelList = Collections.singletonList(getChannel());
        withLoginUser(embeddedChannelList);
        embeddedChannelList.get(0).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<CreateGroupMsg>()
                                        .setAction(CommandType.CREATE_GROUP.getMsgType().getPrefix())
                                        .setMsg((CreateGroupMsg) new CreateGroupMsg().setGroupName("demoGroup"))
                        )
                )
        );
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("success", message);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
        getJdbcTemplate().execute("delete from `group`");
    }

}
