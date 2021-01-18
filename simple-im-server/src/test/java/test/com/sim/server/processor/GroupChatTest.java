package test.com.sim.server.processor;

import com.alibaba.fastjson.JSON;
import com.sim.common.msg.format.MsgParams;
import com.sim.common.msg.format.spec.group.CreateGroupMsg;
import com.sim.common.msg.format.spec.group.GroupChatMsg;
import com.sim.common.msg.format.spec.group.GroupMemberListMsg;
import com.sim.common.msg.format.spec.group.JoinGroupMsg;
import com.sim.common.utils.ByteBufUtils;
import com.sim.server.modules.command.CommandType;
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

        embeddedChannelList.get(0).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<CreateGroupMsg>()
                                        .setAction(CommandType.CREATE_GROUP.getMsgType().getPrefix())
                                        .setMsg((CreateGroupMsg) new CreateGroupMsg().setGroupName("demoGroup"))
                        )
                )
        );
        embeddedChannelList.get(0).readOutbound();

        TimeUnit.MILLISECONDS.sleep(1000);
        embeddedChannelList.get(1).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<JoinGroupMsg>()
                                        .setAction(CommandType.JOIN_GROUP.getMsgType().getPrefix())
                                        .setMsg((JoinGroupMsg) new JoinGroupMsg().setGroupName("demoGroup"))
                        )
                )
        );
        embeddedChannelList.get(1).readOutbound();
        TimeUnit.MILLISECONDS.sleep(1000);
        embeddedChannelList.get(2).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<JoinGroupMsg>()
                                        .setAction(CommandType.JOIN_GROUP.getMsgType().getPrefix())
                                        .setMsg((JoinGroupMsg) new JoinGroupMsg().setGroupName("demoGroup"))
                        )
                )
        );
        embeddedChannelList.get(2).readOutbound();

        embeddedChannelList.get(0).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<GroupMemberListMsg>()
                                        .setAction(CommandType.GROUP_MEMBER_LIST.getMsgType().getPrefix())
                                        .setMsg((GroupMemberListMsg) new GroupMemberListMsg().setGroupName("demoGroup"))
                        )
                )
        );
        String message = ByteBufUtils.fromByteBuf(embeddedChannelList.get(0).readOutbound());
        Assert.assertEquals("shun3\nshun2\nshun1", message);

        //check if other 2 member in the group can received the message
        embeddedChannelList.get(0).writeInbound(
                ByteBufUtils.writeStringWithLineBreak(
                        JSON.toJSONString(
                                new MsgParams<GroupChatMsg>()
                                        .setAction(CommandType.GROUP_CHAT.getMsgType().getPrefix())
                                        .setMsg((GroupChatMsg) new GroupChatMsg().setMsg("hello from shun1 4 demoGroup").setGroupName("demoGroup"))
                        )
                )
        );
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
