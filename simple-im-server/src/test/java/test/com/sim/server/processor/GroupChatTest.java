package test.com.sim.server.processor;

import com.sim.common.utils.ByteBufUtils;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import test.com.sim.server.ImServerApplicationTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
public class GroupChatTest extends ImServerApplicationTest {

    @Test
    public void test() throws InterruptedException {
        List<EmbeddedChannel> embeddedChannelList = Arrays.asList(getChannel(), getChannel(), getChannel());
        withLoginUser(embeddedChannelList);

        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":createGroup demoGroup"));
        embeddedChannelList.get(0).readOutbound();

        TimeUnit.MILLISECONDS.sleep(1000);
        embeddedChannelList.get(1).writeInbound(ByteBufUtils.writeStringWithLineBreak(":joinGroup demoGroup"));
        embeddedChannelList.get(1).readOutbound();
        TimeUnit.MILLISECONDS.sleep(1000);
        embeddedChannelList.get(2).writeInbound(ByteBufUtils.writeStringWithLineBreak(":joinGroup demoGroup"));
        embeddedChannelList.get(2).readOutbound();

        embeddedChannelList.get(0).writeInbound(ByteBufUtils.writeStringWithLineBreak(":gMemberList demoGroup"));
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("shun3\nshun2\nshun1", message);

        //check if other 2 member in the group can received the message
        embeddedChannelList.get(0).writeInbound(":gChat demoGroup hello from shun1 4 demoGroup");
        embeddedChannelList.get(0).readOutbound();
        message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(1).readOutbound());
        Assert.assertEquals("hello from shun1 4 demoGroup", message);

        message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(2).readOutbound());
        Assert.assertEquals("hello from shun1 4 demoGroup", message);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
        getJdbcTemplate().execute("delete from `group`");
    }

}
