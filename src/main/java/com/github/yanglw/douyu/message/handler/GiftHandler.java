package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Gift;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-6.
 */
public class GiftHandler extends CastMessageHandler<Gift> {
    public GiftHandler() {
        super("dgb", Gift.class);
    }

    @Override
    protected void handleMessage0(Connector connector, Gift gift) {
        LogUtils.printf(gift.toString());
    }
}
