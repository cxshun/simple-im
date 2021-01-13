package test.com.sim.server.processor;

import com.sim.common.utils.ByteBufUtils;
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
public class JoinedGroupListTest extends ImServerApplicationTest {

    @Test
    public void test() {
        List<EmbeddedChannel> embeddedChannelList = Arrays.asList(getChannel(), getChannel());
        withLoginUser(embeddedChannelList);

        //create 2 groups
        embeddedChannelList.get(0).writeInbound(":createGroup demoGroup1");
        embeddedChannelList.get(0).readOutbound();

        embeddedChannelList.get(1).writeInbound(":createGroup demoGroup2");
        embeddedChannelList.get(1).readOutbound();

        //first user join the second group, now the first user in 2 group
        embeddedChannelList.get(0).writeInbound(":joinGroup demoGroup2");
        embeddedChannelList.get(0).readOutbound();

        embeddedChannelList.get(0).writeInbound(":joinedGroupList");
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("demoGroup2\ndemoGroup1", message);
    }

    @After
    public void tearDown() {
        getJdbcTemplate().execute("delete from `user`");
        getJdbcTemplate().execute("delete from `group`");
    }

}
