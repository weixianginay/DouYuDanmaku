package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Message;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-04-09.
 */
public class FormatMessageHandler<T extends Message> extends CastMessageHandler<T> {
    public FormatMessageHandler(String type, Class<T> clz) {
        super(type, clz);
    }

    @Override
    protected void handleMessage0(Connector connector, T t) {
        if (t instanceof FormatMessage) {
            FormatMessage message = (FormatMessage) t;
            LogUtils.printf(message.getMessage());
        } else {
            LogUtils.printf(t.toString());
        }
    }

    public static interface FormatMessage {
        String getMessage();
    }
}
