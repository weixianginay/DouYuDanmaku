package com.github.yanglw.douyu.message.handler;

import com.github.yanglw.douyu.message.Message;
import com.github.yanglw.douyu.socket.Connector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yanglw on 2016-4-7.
 */
public abstract class CastMessageHandler<T extends Message> extends TargetTypeMessageHandler {
    private Constructor<T> mConstructor;

    public CastMessageHandler(String type, Class<T> clz) {
        super(type);
        try {
            mConstructor = clz.getConstructor(Message.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleMessage(Connector connector, Message msg) {
        T t;
        try {
            t = mConstructor.newInstance(msg);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        handleMessage0(connector, t);
    }

    protected abstract void handleMessage0(Connector connector, T t);
}
