package com.github.yanglw.douyu.message;

/**
 * Created by yanglw on 2016-04-08.
 */
public class BlackRes extends Message {
    private String blacktype;
    private String snick;
    private String dnick;

    public BlackRes(Message message) {
        super(message);

        blacktype = list.get("blacktype");
        snick = list.get("snick");
        dnick = list.get("dnick");
    }

    public String getBlacktype() {
        switch (blacktype) {
            case "2":
                return "禁言";
            default:
                return "未知操作";
        }
    }

    public String getSnick() {
        return snick;
    }

    public String getDnick() {
        return dnick;
    }

    @Override
    public String toString() {
        return String.format("[%1$s] 被 [%2$s] %3$s。", getSnick(), getDnick(), getBlacktype());
    }
}
