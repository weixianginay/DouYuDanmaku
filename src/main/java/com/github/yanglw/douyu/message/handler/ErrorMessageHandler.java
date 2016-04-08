package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Message;
import com.github.yanglw.douyu.socket.Connector;

/**
 * Created by yanglw on 2016-4-8.
 */
public class ErrorMessageHandler extends TargetTypeMessageHandler {
    private boolean mCloseGroup;

    public ErrorMessageHandler(boolean closeGroup) {
        super("error");
        mCloseGroup = closeGroup;
    }

    @Override
    public void handleMessage(Connector connector, Message message) {
        try {
            connector.close(mCloseGroup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
