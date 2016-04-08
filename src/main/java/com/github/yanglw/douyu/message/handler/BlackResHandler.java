package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.BlackRes;

/**
 * Created by yanglw on 2016-4-6.
 */
public class BlackResHandler extends FormatMessageHandler<BlackRes> {
    public BlackResHandler() {
        super("blackres", BlackRes.class);
    }
}
