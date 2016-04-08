package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Gift;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-6.
 */
public class GiftHandler extends FormatMessageHandler<Gift> {
    public GiftHandler() {
        super("dgb", Gift.class);
    }
}
