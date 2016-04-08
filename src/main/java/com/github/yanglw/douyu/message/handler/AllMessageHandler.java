package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Message;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-8.
 */
public class AllMessageHandler extends TargetTypeMessageHandler {
    public AllMessageHandler() {
        super(null);
    }

    @Override
    public void handleMessage(Connector connector, Message message) {
        LogUtils.printf(message.getMsg());
    }
}
