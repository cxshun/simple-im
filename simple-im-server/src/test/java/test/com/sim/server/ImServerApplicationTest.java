package test.com.sim.server;

import com.sim.server.modules.handler.ImServerMsgHandler;
import com.sim.server.modules.handler.ServerMsgDecoder;
import io.netty.channel.ChannelId;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Getter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@Getter
public class ImServerApplicationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmbeddedChannel getChannel() {
        return new EmbeddedChannel(
                new TestChannelId(UUID.randomUUID().toString()),
                new LoggingHandler(LogLevel.DEBUG),
                new ServerMsgDecoder(),
                new LineBasedFrameDecoder(4096, true, true),
                new ImServerMsgHandler()
        );
    }

    protected void withLoginUser(List<EmbeddedChannel> embeddedChannelList) {
        int i = 1;
        for (EmbeddedChannel embeddedChannel:embeddedChannelList) {
            embeddedChannel.writeInbound(":login shun" + (i ++) + ":1234");
            embeddedChannel.readOutbound();
            embeddedChannel.readOutbound();
        }
    }

    private static class TestChannelId implements ChannelId {
        private final String id;
        public TestChannelId(String id) {
            this.id = id;
        }

        @Override
        public String asShortText() {
            return id;
        }

        @Override
        public String asLongText() {
            return id;
        }

        @Override
        public int compareTo(ChannelId o) {
            if (o instanceof TestChannelId) {
                return 0;
            }

            return asLongText().compareTo(o.asLongText());
        }
    }

}
