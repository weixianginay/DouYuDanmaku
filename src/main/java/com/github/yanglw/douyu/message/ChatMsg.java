package com.github.yanglw.douyu.message;

/**
 * Created by yanglw on 2016-4-6.
 */
public class ChatMsg extends Message {
    private String mRid;
    private String mUid;
    private String mNickName;
    private String mTxt;
    private String mCid;
    private String mLevel;
    private String mCt;
    private String mGt;
    private String mRg;

    public ChatMsg(Message message) {
        super(message);
        mRid = list.get("rid");
        mUid = list.get("uid");
        mNickName = list.get("nn");
        mTxt = list.get("txt");
        mCid = list.get("cid");
        mLevel = list.get("level");
        mCt = list.get("ct");
        mGt = list.get("gt");
        mRg = list.get("rg");
        if (mTxt != null) {
            // @S --> /
            // @A --> @
            // \\ --> \
            mTxt = mTxt.replace("@S","/").replace("@A","@").replace("\\\\","\\");
        } else {
            mTxt = "";
        }
    }

    public String getRid() {
        return mRid;
    }

    public String getUid() {
        return mUid;
    }

    public String getNickName() {
        return mNickName;
    }

    public String getTxt() {
        return mTxt;
    }

    public String getCid() {
        return mCid;
    }

    public String getLevel() {
        return mLevel;
    }

    public String getCt() {
        return mCt;
    }

    public String getGt() {
        return mGt;
    }

    public String getRg() {
        return mRg;
    }

    @Override
    public String toString() {
        return String.format("[弹幕]%1$s：%2$s", getNickName(), getTxt());
    }
}
