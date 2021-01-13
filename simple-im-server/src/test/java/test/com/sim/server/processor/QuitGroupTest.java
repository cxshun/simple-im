package test.com.sim.server.processor;

import com.sim.common.utils.ByteBufUtils;
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
public class QuitGroupTest extends ImServerApplicationTest {

    @Test
    public void test() {
        List<EmbeddedChannel> embeddedChannelList = Collections.singletonList(getChannel());
        withLoginUser(embeddedChannelList);

        //create 2 groups, meaning the current user is in 2 groups
        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":createGroup demoGroup1"));
        embeddedChannelList.get(0).readOutbound();
        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":createGroup demoGroup2"));
        embeddedChannelList.get(0).readOutbound();

        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":joinedGroupList"));
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("demoGroup2\ndemoGroup1", message);

        //quit demoGroup2, now leaving demoGroup1
        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":quitGroup demoGroup2"));
        embeddedChannelList.get(0).readOutbound();
        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":joinedGroupList"));
        message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("demoGroup1", message);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
        getJdbcTemplate().execute("delete from `group`");
    }

}
