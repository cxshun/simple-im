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
public class OnlineUserListTest extends ImServerApplicationTest {

    @Test
    public void test() {
        //register two onlineUser
        List<EmbeddedChannel> embeddedChannelList = Arrays.asList(getChannel(), getChannel());
        withLoginUser(embeddedChannelList);

        embeddedChannelList.get(0).writeInbound(":onlineUserList");
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("shun1\nshun2", message);

        embeddedChannelList.get(1).disconnect();
        embeddedChannelList.get(0).writeInbound(":onlineUserList");
        message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("shun1", message);
    }

    @After
    public void teardown() {
        getJdbcTemplate().execute("delete from `user`");
    }

}
