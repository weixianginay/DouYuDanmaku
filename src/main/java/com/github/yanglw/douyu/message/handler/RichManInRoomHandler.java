package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.RichManInRoom;

/**
 * Created by yanglw on 2016-4-6.
 */
public class RichManInRoomHandler extends FormatMessageHandler<RichManInRoom> {
    public RichManInRoomHandler() {
        super("uenter", RichManInRoom.class);
    }
}
