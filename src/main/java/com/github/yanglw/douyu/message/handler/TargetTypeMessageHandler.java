package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.socket.MessageHandler;

/**
 * Created by yanglw on 2016-4-8.
 */
public abstract class TargetTypeMessageHandler implements MessageHandler {
    private String mType;

    public TargetTypeMessageHandler(String type) {
        mType = type;
    }

    @Override
    public String getType() {
        return mType;
    }
}
