package test.com.sim.server.processor;

import com.sim.common.utils.ByteBufUtils;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;
import test.com.sim.server.ImServerApplicationTest;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
public class LoginTest extends ImServerApplicationTest {

    @Test
    public void test() {
        EmbeddedChannel channel = getChannel();
        channel.writeInbound(ByteBufUtils.writeStringWithLineBreak(":login shun:1234"));

        //first return connection active message
        channel.readOutbound();
        String str = ByteBufUtils.fromByteBuf(channel.readOutbound());
        Assert.assertEquals("login success", str);
    }

}
