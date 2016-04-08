package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.RichManInRoom;
import com.github.yanglw.douyu.socket.Connector;
import com.github.yanglw.douyu.util.LogUtils;

/**
 * Created by yanglw on 2016-4-6.
 */
public class RichManInRoomHandler extends CastMessageHandler<RichManInRoom> {
    public RichManInRoomHandler() {
        super("uenter", RichManInRoom.class);
    }

    @Override
    protected void handleMessage0(Connector connector, RichManInRoom message) {
        LogUtils.printf(message);
    }
}
