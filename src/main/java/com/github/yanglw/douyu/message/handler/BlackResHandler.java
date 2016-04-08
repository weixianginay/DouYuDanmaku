package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.BlackRes;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-6.
 */
public class BlackResHandler extends CastMessageHandler<BlackRes> {
    public BlackResHandler() {
        super("blackres", BlackRes.class);
    }

    @Override
    protected void handleMessage0(Connector connector, BlackRes message) {
        LogUtils.printf(message);
    }
}
