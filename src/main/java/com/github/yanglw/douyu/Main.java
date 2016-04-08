package com.github.yanglw.douyu;

import com.github.yanglw.douyu.message.handler.BlackResHandler;
import com.github.yanglw.douyu.message.handler.ChatMsgHandler;
import com.github.yanglw.douyu.message.handler.GiftHandler;
import com.github.yanglw.douyu.message.handler.RichManInRoomHandler;
import com.github.yanglw.douyu.util.EmptyUtils;

/**
 * Created by yanglw on 2016-4-7.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (!EmptyUtils.isEmpty(args)) {
            new DouYu().addMessageHandler(new ChatMsgHandler())
                       .addMessageHandler(new GiftHandler())
                       .addMessageHandler(new BlackResHandler())
                       .addMessageHandler(new RichManInRoomHandler())
                       .start(args[0]);
        }
    }
}
