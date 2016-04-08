package com.github.yanglw.douyu.codec;

import com.github.yanglw.douyu.util.EmptyUtils;

import java.util.LinkedList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by yanglw on 2016-04-05.
 */
public class MessageHandlerAdapter extends ChannelInboundHandlerAdapter {
    private List<SocketMessageHandler> mListenerList = new LinkedList<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String message = (String) msg;

            if (!EmptyUtils.isEmpty(mListenerList)) {
                for (SocketMessageHandler listener : mListenerList) {
                    listener.handleMessage(ctx, message);
                }
            }
        } else {
            super.channelRead(ctx, msg);
        }
    }

    public void addMessageReadListener(SocketMessageHandler listener) {
        if (listener == null) {
            return;
        }
        mListenerList.add(listener);
    }

    public void removeMessageReadListener(SocketMessageHandler listener) {
        if (listener == null) {
            return;
        }
        mListenerList.remove(listener);
    }

    public static interface SocketMessageHandler {
        void handleMessage(ChannelHandlerContext ctx, String msg);
    }
}
