package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.ChatMsg;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-6.
 */
public class ChatMsgHandler extends FormatMessageHandler<ChatMsg> {
    public ChatMsgHandler() {
        super("chatmsg", ChatMsg.class);
    }
}
