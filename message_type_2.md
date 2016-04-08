# 弹幕

需新开通一个 Socket 连接弹幕服务器，需要以下基础数据：

- 弹幕服务器地址与端口号
- room id
- gid
- username （可以写成固定的内容，密码一直都是固定的 `1234567890123456`）

以上数据，均可以从 [斗鱼 Socket 信息类型 1](message type 1.txt) 中获取。

## step 1

连接服务器成功之后，首先发送 `loginreq` 消息，进行登录。服务器返回的内容，没有意义，可以进行忽略。

```
// 客户端发送内容
type@=loginreq/username@=visitor3389541/password@=1234567890123456/roomid@=56040/
// 服务器返回内容
type@=loginres/userid@=0/roomgroup@=0/pg@=0/sessionid@=0/username@=/nickname@=/live_stat@=0/is_illegal@=0/ill_ct@=/ill_ts@=0/now@=0/ps@=0/es@=0/npv@=0/best_dlev@=0/cur_lev@=0/
```

接着，发送 `joingroup` 消息，加入弹幕组，之后便可以接收弹幕消息了。

```
// 客户端发送内容
type@=joingroup/rid@=56040/gid@=125/
// 服务器返回内容
type@=bc_buy_deserve/level@=22/lev@=3/rid@=56040/gid@=141/cnt@=1/hits@=1/sid@=11012144/sui@=id@A=11012144@Sname@A=qq_D1ZnkZGm@Snick@A=主播芒果c507794@Sicon@A=avatar@AS011@AS01@AS21@AS44_avatar@Srg@A=1@Spg@A=1@Srt@A=1425231633@Sbg@A=0@Sweight@A=2437360@Sstrength@A=222880@Scps_id@A=0@Sps@A=1@Ses@A=1@Sver@A=20150929@Sm_deserve_lev@A=3@Scq_cnt@A=1@Sbest_dlev@A=3@Sglobal_ban_lev@A=0@Sexp@A=7091680@Slevel@A=22@Scurr_exp@A=285580@Sup_need@A=1501320@Sgt@A=1@S/
```

## step 2

接入成功，服务器开始返回弹幕和礼物等消息。

具体的消息种类很多，我抓取了几个小时，具体获得了以下类型的消息：

1. 赠送礼物

    ```
    type@=dgb/rid@=56040/gfid@=57/gs@=3/uid@=41140541/nn@=我在丛中叫/ic@=avatar@Sface@S201603@S796e07e2f0aa0e0745d3f4ebd2ff9f28/str@=500/level@=7/dw@=455993068/hits@=98/
    // 少时诵诗书飒飒 赠送主播 [100 鱼丸] 3 连击
    type@=dgb/rid@=56040/gfid@=50/gs@=1/uid@=4477239/nn@=少时诵诗书飒飒/ic@=avatar@S004@S47@S72@S39_avatar/str@=4700/level@=2/dw@=456067568/hits@=3/
    // 用户等级：33[LD丶菊南翔级长樱桃]赠送给主播 [赞] 2 连击
    type@=dgb/rid@=56040/gfid@=57/gs@=3/uid@=13040322/nn@=LD丶菊南翔级长樱桃/ic@=avatar@S013@S04@S03@S22_avatar/str@=1292457/level@=33/dw@=456088968/hits@=2/bdlv@=3/rg@=4/
    // [伟大的香蕉] 赠送给主播 [666] 10 连击
    type@=dgb/rid@=56040/gfid@=52/gs@=4/uid@=16750476/nn@=伟大的香蕉/ic@=avatar@S016@S75@S04@S76_avatar/str@=15240/level@=16/dw@=456089868/hits@=10/
    // 用户等级：2 [白茅阿三哥] 赠送给主播 [520个鱼丸] 5 连击
    type@=dgb/rid@=56040/gfid@=53/gs@=2/uid@=44089224/nn@=白茅阿三哥/ic@=avatar@Sface@S201604@Sab361b29743488bbad7262c5da47b6e7/str@=4300/level@=2/dw@=456107168/hits@=5/
    ```

2. 飞机

    ```
    type@=spbc/sn@=我条就是刚灬阿飞/dn@=主播油条/gn@=飞机/gc@=1/drid@=56040/gs@=5/gb@=0/es@=2/gfid@=54/eid@=5/rid@=56040/gid@=141/
    ```

3. 火箭

    ```
    // [雕刻時光中] 赠送给 [June林诗洋] 一个火箭
    type@=spbc/sn@=雕刻時光中/dn@=June林诗洋/gn@=火箭/gc@=1/drid@=79420/gs@=6/gb@=1/es@=1/gfid@=59/eid@=7/rid@=56040/gid@=141/
    // 赠送当前主播火箭
    type@=spbc/sn@=主播芒果c507794/dn@=主播油条/gn@=火箭/gc@=1/drid@=56040/gs@=6/gb@=0/es@=1/gfid@=59/eid@=7/rid@=56040/gid@=141/
    type@=dgb/rid@=56040/gfid@=59/gs@=6/uid@=11012144/nn@=主播芒果c507794/ic@=avatar@S011@S01@S21@S44_avatar/str@=222880/level@=23/dw@=456395408/hits@=4/dlv@=3/dc@=10/bdlv@=3/rg@=4/rpid@=16575/slt@=180/elt@=300/
    ```

4. 在线领鱼丸

    ```
    type@=onlinegift/rid@=56040/gid@=141/uid@=6439962/sil@=298/nn@=开溜猫/level@=12/ur@=2/if@=3/btype@=2/ct@=0/
    // [547658755] 通过在线领鱼丸获得了 [236] 个酬勤专享鱼丸
    type@=onlinegift/rid@=56040/gid@=141/uid@=1316844/sil@=236/nn@=547658755/level@=13/ur@=1/if@=6/btype@=6/ct@=0/
    // [360549565] 通过在线领鱼丸获得了 [204] 个酬勤专享鱼丸
    type@=onlinegift/rid@=56040/gid@=141/uid@=14960970/sil@=204/nn@=360549565/level@=13/ur@=1/if@=6/btype@=6/ct@=0/
    // [高冷丶俊铘] 使用斗鱼手机APP领取了 [1000] 鱼丸
    type@=onlinegift/rid@=56040/gid@=141/uid@=39905175/sil@=1000/nn@=高冷丶俊铘/level@=1/ur@=1/if@=1/btype@=3/ct@=1/
    ```

5. 火箭抢鱼丸

    ```
    // [黑黑黑黑黑砂] 领取了 [主播芒果c507794] 派送的 [132] 个鱼丸
    type@=ggbb/rid@=56040/gid@=141/sl@=132/sid@=11012144/did@=15584000/snk@=主播芒果c507794/dnk@=黑黑黑黑黑砂/rpt@=0/
    ```

6. 弹幕

    ```
    // 普通弹幕
    type@=chatmsg/rid@=56040/uid@=3487516/nn@=allenraotao/txt@=感谢金克斯送的火箭/cid@=bef2a2e1843c4b66654f0d0000000000/level@=1/
    // 弹幕 手机客户端
    type@=chatmsg/rid@=56040/uid@=15401666/nn@=大大大号/txt@=66666666666/cid@=bef2a2e1843c4b66bf630d0000000000/level@=5/ct@=1/
    // 弹幕 房管
    type@=chatmsg/rid@=56040/uid@=13040322/nn@=LD丶菊南翔级长樱桃/txt@=油条 明明有2个孩子/cid@=bef2a2e1843c4b660da10d0000000000/level@=33/gt@=3/rg@=4/bdlv@=3/
    // 弹幕 房管，文字有特殊颜色
    type@=chatmsg/rid@=56040/uid@=3386817/nn@=夏沫ペ柠萌ぃ/txt@=性别女、爱好男、没开直播、木有房间号、再问自杀[emot:dy110]/cid@=bef2a2e1843c4b6637640d0000000000/level@=25/gt@=3/col@=5/rg@=4/
    // 弹幕 土豪（一天内在当前房间赠送礼物达到 [10] 鱼翅 [vervs]）发送的弹幕
    type@=chatmsg/rid@=56040/uid@=19075577/nn@=vervs/txt@=3想/cid@=bef2a2e1843c4b6665720d0000000000/level@=7/gt@=1/ct@=2/
    ```

    弹幕的消息，为了防止字符含义混乱，使用的自定义的字符转移，具体对应的列表如下：

    消息内容|实际含义
    ---|---
    `@S` | `/`
    `@A` | `@`
    `\\` | `\`

7. 土豪进入房间

    ```
    // 一天内在当前房间赠送礼物达到 [10] 鱼翅 [他家隔壁王大叔] 来到本直播间
    type@=uenter/rid@=56040/uid@=13770345/nn@=他家隔壁王大叔/str@=16340/level@=6/gt@=1/
    // 酬勤用户用户等级：[27] [众里寻她赵萌笙gglike]来 到本直播间
    type@=uenter/rid@=56040/uid@=4982424/nn@=众里寻她赵萌笙gglike/str@=1423806/level@=27/bdlv@=3/
    ```

8. 贡献榜发生变化

    ```
    // [我条就是刚灬阿飞] 贡献榜上升至第 7 位
    type@=rankup/rid@=56040/gid@=141/drid@=56040/rt@=0/bt@=1/sz@=3/uid@=23019874/nk@=我条就是刚灬阿飞/rkt@=1/rn@=7/
    ```

9. 贡献榜列表

    ```
    type@=ranklist/rid@=56040/gid@=141/list@=uid@AA=3386817@AScrk@AA=1@ASlrk@AA=2@ASrs@AA=1@ASnickname@AA=夏沫ペ柠萌ぃ@ASstrength@AA=13880@ASgold@AA=14310400@ASlevel@AA=25@AS@Suid@AA=15028158@AScrk@AA=2@ASlrk@AA=1@ASrs@AA=-1@ASnickname@AA=你认识的阿静@ASstrength@AA=142060@ASgold@AA=13800300@ASlevel@AA=28@AS@Suid@AA=34362552@AScrk@AA=3@ASlrk@AA=3@ASrs@AA=0@ASnickname@AA=晴天陌路l@ASstrength@AA=163400@ASgold@AA=2600000@ASlevel@AA=28@AS@Suid@AA=23806067@AScrk@AA=4@ASlrk@AA=4@ASrs@AA=0@ASnickname@AA=世界上最美丽女人@ASstrength@AA=61240@ASgold@AA=763560@ASlevel@AA=17@AS@Suid@AA=1211626@AScrk@AA=5@ASlrk@AA=5@ASrs@AA=0@ASnickname@AA=一言不合就XX@ASstrength@AA=42500@ASgold@AA=481600@ASlevel@AA=13@AS@Suid@AA=44480169@AScrk@AA=6@ASlrk@AA=6@ASrs@AA=0@ASnickname@AA=烽火戏蜗牛@ASstrength@AA=16000@ASgold@AA=316000@ASlevel@AA=10@AS@Suid@AA=2233051@AScrk@AA=7@ASlrk@AA=7@ASrs@AA=0@ASnickname@AA=kivien丶@ASstrength@AA=151820@ASgold@AA=310700@ASlevel@AA=13@AS@Suid@AA=26615178@AScrk@AA=8@ASlrk@AA=8@ASrs@AA=0@ASnickname@AA=极度无情@ASstrength@AA=5100@ASgold@AA=154300@ASlevel@AA=12@AS@Suid@AA=11012144@AScrk@AA=9@ASlrk@AA=11@ASrs@AA=1@ASnickname@AA=主播芒果c507794@ASstrength@AA=222880@ASgold@AA=150700@ASlevel@AA=22@AS@Suid@AA=23019874@AScrk@AA=10@ASlrk@AA=9@ASrs@AA=-1@ASnickname@AA=我条就是刚灬阿飞@ASstrength@AA=18440@ASgold@AA=127340@ASlevel@AA=10@AS@S/list_all@=uid@AA=3386817@AScrk@AA=1@ASlrk@AA=2@ASrs@AA=1@ASnickname@AA=夏沫ペ柠萌ぃ@ASstrength@AA=13880@ASgold@AA=14310500@ASlevel@AA=25@AS@Suid@AA=15028158@AScrk@AA=2@ASlrk@AA=1@ASrs@AA=-1@ASnickname@AA=你认识的阿静@ASstrength@AA=142060@ASgold@AA=14051840@ASlevel@AA=28@AS@Suid@AA=3643852@AScrk@AA=3@ASlrk@AA=3@ASrs@AA=0@ASnickname@AA=来自俄罗斯的魔头@ASstrength@AA=157599@ASgold@AA=12781500@ASlevel@AA=25@AS@Suid@AA=39504716@AScrk@AA=4@ASlrk@AA=4@ASrs@AA=0@ASnickname@AA=丹晴@ASstrength@AA=36180@ASgold@AA=6910180@ASlevel@AA=22@AS@Suid@AA=32878355@AScrk@AA=5@ASlrk@AA=5@ASrs@AA=0@ASnickname@AA=来看直播啦啦啦啦啦@ASstrength@AA=52340@ASgold@AA=6665100@ASlevel@AA=23@AS@Suid@AA=4107341@AScrk@AA=6@ASlrk@AA=6@ASrs@AA=0@ASnickname@AA=只爱公子的小内内@ASstrength@AA=273600@ASgold@AA=5161100@ASlevel@AA=35@AS@Suid@AA=4270484@AScrk@AA=7@ASlrk@AA=7@ASrs@AA=0@ASnickname@AA=熊不熊猫不猫@ASstrength@AA=780660@ASgold@AA=4323700@ASlevel@AA=24@AS@Suid@AA=14961525@AScrk@AA=8@ASlrk@AA=8@ASrs@AA=0@ASnickname@AA=洋妹是个小可爱@ASstrength@AA=174640@ASgold@AA=4284200@ASlevel@AA=20@AS@Suid@AA=20911498@AScrk@AA=9@ASlrk@AA=9@ASrs@AA=0@ASnickname@AA=Krysta10818@ASstrength@AA=116620@ASgold@AA=3397000@ASlevel@AA=23@AS@Suid@AA=34362552@AScrk@AA=10@ASlrk@AA=10@ASrs@AA=0@ASnickname@AA=晴天陌路l@ASstrength@AA=163400@ASgold@AA=2600000@ASlevel@AA=28@AS@S/list_day@=/
    ```

10. 禁言

    ```
    // [覆水难忘] 被管理员 [夏沫ペ柠萌ぃ] 禁言
    type@=blackres/rescode@=0/rid@=56040/gid@=141/blacktype@=2/userid@=18714418/limittime@=356400/snick@=夏沫ペ柠萌ぃ/dnick@=覆水难忘/
    // [seeday] 被管理员 [夏沫ペ柠萌ぃ] 禁言
    type@=blackres/rescode@=0/rid@=56040/gid@=141/blacktype@=2/userid@=16695490/limittime@=356400/snick@=夏沫ペ柠萌ぃ/dnick@=seeday/
    ```

11. 未知

    ```
    type@=upgrade/uid@=2585323/rid@=56040/gid@=141/nn@=582175517/level@=5/
    ```

12. 未知

    ```
    type@=gift_title/uname@=主播芒果c507794/rname@=主播油条/gt@=2/uid@=11012144/rid@=56040/gid@=141/
    ```

## step 3

客户端需要没 45 秒想服务器发送心跳，服务器收到客户端的心跳之后，也会发送一次心跳。

```
// 客户端发送内容
type@=mrkl/
// 服务器返回内容
type@=mrkl/
```
