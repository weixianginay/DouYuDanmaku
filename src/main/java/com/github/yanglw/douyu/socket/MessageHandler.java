package com.github.yanglw.douyu.socket;

import com.github.yanglw.douyu.message.Message;

/**
 * Created by yanglw on 2016-4-7.
 */
public interface MessageHandler {
    String getType();

    void handleMessage(Connector connector, Message message);
}
