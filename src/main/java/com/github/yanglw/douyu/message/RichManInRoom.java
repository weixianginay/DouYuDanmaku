package com.github.yanglw.douyu.message;

/**
 * Created by yanglw on 2016-04-08.
 */
public class RichManInRoom extends Message {
    private String nn;

    public RichManInRoom(Message message) {
        super(message);
        nn = list.get("nn");
    }

    @Override
    public String toString() {
        return String.format("欢迎土豪 [%1$s] 进入房间。", nn);
    }
}
