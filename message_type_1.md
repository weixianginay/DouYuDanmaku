# 第一步 Socket 通讯协议

首先通过网页源码，获取目标直播间的 room id 和第一步 Socket 的服务器地址和端口号。

可借助关键字 `room_id` 和 `server_config` 进行获取，具体方式请参照 [抓取斗鱼直播弹幕 - Brucezz's Blog](http://brucezz.github.io/articles/2016/01/11/douyu-crawler/) 。

## step 1

客户端连接服务器之后，需要发送验证信息，验证信息的生成方式，可以参照[代码段](src/main/java/com/github/yanglw/douyu/DouYu.java#L83)。

服务器接收到消息之后，会返回关于一个匿名用户，我们需要其中的 `username` 字段。

```
// 客户端发送内容
type@=loginreq/username@=/ct@=0/password@=/roomid@=56040/devid@=0C586D24974420E4E40D4670400339E6/rt@=1460093455/vk@=3f68859e4118cb19485306941cd2ed8d/ver@=20150929/ltkid@=/biz@=/stk@=/
// 服务器返回内容
type@=loginres/userid@=1303889541/roomgroup@=0/pg@=0/sessionid@=644721627/username@=visitor3389541/nickname@=visitor3389541/live_stat@=1/is_illegal@=0/ill_ct@=/ill_ts@=0/now@=1460093653/ps@=0/es@=0/npv@=0/best_dlev@=0/cur_lev@=0/
```

## step 2

接下来，我们需要获取弹幕组的信息，发送一下内容（`rid` 即 room id）：

```
// 客户端发送内容
type@=qrl/rid@=56040/et@=0/
type@=qtlnq/

// 服务器返回内容
type@=msgrepeaterlist/rid@=56040/list@=id@AA=75804@ASnr@AA=1@ASml@AA=10000@ASip@AA=danmu.douyutv.com@ASport@AA=8602@AS@Sid@AA=75803@ASnr@AA=1@ASml@AA=10000@ASip@AA=danmu.douyutv.com@ASport@AA=8601@AS@Sid@AA=76301@ASnr@AA=1@ASml@AA=10000@ASip@AA=danmu.douyutv.com@ASport@AA=12601@AS@Sid@AA=74802@ASnr@AA=1@ASml@AA=10000@ASip@AA=danmu.douyutv.com@ASport@AA=12602@AS@S/
type@=setmsggroup/rid@=56040/gid@=125/
type@=scl/cd@=0/maxl@=20/
type@=initcl/uid@=1303889541/cd@=1000/maxl@=20/
type@=memberinfores/silver@=0/gold@=0/strength@=0/weight@=456610088/exp@=0/curr_exp@=0/level@=1/up_need@=1000/fans_count@=2058493/fl@=0/list@=rpid@A=16595@Sstl@A=158@Setl@A=278@Ssid@A=1055754@Ssnk@A=放风筝的X伦@Sgid@A=59@Srpt@A=0@S|@S/glist@=sid@A=1055754@Sgid@A=59@Sctc@A=178@Shs@A=1@Sgs@A=6@Ssnk@A=放风筝的X伦@Sic@A=avatar@AS001@AS05@AS57@AS54_avatar@S|@S/
// 该房间的贡献榜。
type@=ranklist/rid@=56040/gid@=32542/list@=uid@AA=3386817@AScrk@AA=1@ASlrk@AA=2@ASrs@AA=0@ASnickname@AA=夏沫ペ柠萌ぃ@ASstrength@AA=13880@ASgold@AA=14716400@ASlevel@AA=26@AS@Suid@AA=15028158@AScrk@AA=2@ASlrk@AA=1@ASrs@AA=0@ASnickname@AA=你认识的阿静@ASstrength@AA=142060@ASgold@AA=13800300@ASlevel@AA=28@AS@Suid@AA=11012144@AScrk@AA=3@ASlrk@AA=4@ASrs@AA=1@ASnickname@AA=主播芒果c507794@ASstrength@AA=222880@ASgold@AA=2607100@ASlevel@AA=23@AS@Suid@AA=34362552@AScrk@AA=4@ASlrk@AA=3@ASrs@AA=-1@ASnickname@AA=晴天陌路l@ASstrength@AA=163400@ASgold@AA=2600000@ASlevel@AA=28@AS@Suid@AA=1055754@AScrk@AA=5@ASlrk@AA=6@ASrs@AA=1@ASnickname@AA=放风筝的X伦@ASstrength@AA=10014634@ASgold@AA=863600@ASlevel@AA=45@AS@Suid@AA=23806067@AScrk@AA=6@ASlrk@AA=5@ASrs@AA=-1@ASnickname@AA=世界上最美丽女人@ASstrength@AA=61240@ASgold@AA=863560@ASlevel@AA=17@AS@Suid@AA=1211626@AScrk@AA=7@ASlrk@AA=6@ASrs@AA=-1@ASnickname@AA=一言不合就XX@ASstrength@AA=42500@ASgold@AA=481600@ASlevel@AA=13@AS@Suid@AA=23019874@AScrk@AA=8@ASlrk@AA=7@ASrs@AA=-1@ASnickname@AA=我条就是刚灬阿飞@ASstrength@AA=18440@ASgold@AA=327340@ASlevel@AA=12@AS@Suid@AA=44480169@AScrk@AA=9@ASlrk@AA=8@ASrs@AA=-1@ASnickname@AA=烽火戏蜗牛@ASstrength@AA=16000@ASgold@AA=316000@ASlevel@AA=10@AS@Suid@AA=2233051@AScrk@AA=10@ASlrk@AA=9@ASrs@AA=-1@ASnickname@AA=kivien丶@ASstrength@AA=152020@ASgold@AA=310900@ASlevel@AA=13@AS@S/list_all@=uid@AA=3386817@AScrk@AA=1@ASlrk@AA=2@ASrs@AA=1@ASnickname@AA=夏沫ペ柠萌ぃ@ASstrength@AA=13880@ASgold@AA=14716500@ASlevel@AA=26@AS@Suid@AA=15028158@AScrk@AA=2@ASlrk@AA=1@ASrs@AA=-1@ASnickname@AA=你认识的阿静@ASstrength@AA=142060@ASgold@AA=14051840@ASlevel@AA=28@AS@Suid@AA=3643852@AScrk@AA=3@ASlrk@AA=3@ASrs@AA=0@ASnickname@AA=来自俄罗斯的魔头@ASstrength@AA=157599@ASgold@AA=12781500@ASlevel@AA=25@AS@Suid@AA=39504716@AScrk@AA=4@ASlrk@AA=4@ASrs@AA=0@ASnickname@AA=丹晴@ASstrength@AA=36180@ASgold@AA=6910180@ASlevel@AA=22@AS@Suid@AA=32878355@AScrk@AA=5@ASlrk@AA=5@ASrs@AA=0@ASnickname@AA=来看直播啦啦啦啦啦@ASstrength@AA=52340@ASgold@AA=6665100@ASlevel@AA=23@AS@Suid@AA=4107341@AScrk@AA=6@ASlrk@AA=6@ASrs@AA=0@ASnickname@AA=只爱公子的小内内@ASstrength@AA=273600@ASgold@AA=5161100@ASlevel@AA=35@AS@Suid@AA=4270484@AScrk@AA=7@ASlrk@AA=7@ASrs@AA=0@ASnickname@AA=熊不熊猫不猫@ASstrength@AA=780660@ASgold@AA=4323700@ASlevel@AA=24@AS@Suid@AA=14961525@AScrk@AA=8@ASlrk@AA=8@ASrs@AA=0@ASnickname@AA=洋妹是个小可爱@ASstrength@AA=174640@ASgold@AA=4284200@ASlevel@AA=20@AS@Suid@AA=20911498@AScrk@AA=9@ASlrk@AA=9@ASrs@AA=0@ASnickname@AA=Krysta10818@ASstrength@AA=116620@ASgold@AA=3397000@ASlevel@AA=23@AS@Suid@AA=11012144@AScrk@AA=10@ASlrk@AA=11@ASrs@AA=0@ASnickname@AA=主播芒果c507794@ASstrength@AA=222880@ASgold@AA=2724900@ASlevel@AA=23@AS@S/list_day@=/
// 服务器有时会返回下面的信息，消息内容是一个 A 站的链接，打开一看，是《斗鱼联播》。
type@=rsm/rid@=0/gid@=0/t@=2/bt@=1460037255/vt@=345600/c@=xxxxxxxxxxxxxxxxxxxxxxxx/url@=http:@S@Swww.acfun.tv@Sv@Sac2653697/
```

服务器会返回一系列的信息回来，我们需要的的内容如下：

- type@=msgrepeaterlist
    - list 元素中为弹幕服务器的地址和端口号列表，解析获取弹幕服务器信息。
- type@=setmsggroup
    - gid 弹幕组编号。

经过测试发现，即使不发送 `type@=qtlnq/` ，服务器也会返回弹幕组的信息。

## 其他

### 1

客户端会每隔一段时间发送一个心跳包，心跳包中，有验证的数据，暂时还不知道验证算法。具体的发送内容和返回内容如下：

```
// 客户端发送内容
type@=keeplive/tick@=1460093500/vbw@=0/k@=dbdd1ce53e274fa6197dbefdc7fd2c46/
// 服务器返回内容
type@=keeplive/tick@=1460093500/uc@=827847/
```

### 2

客户端每间隔一个心跳包的时间，会发送以下信息。估计与视频的信息有关，其中也有验证数据，验证算法依然未知。服务器接收到消息之后，无任何返回。

```
// 客户端发送内容
type@=ssr/uid@=1303889541/rid@=56040/ec@=0/surl@=http:@S@Shdla.douyutv.com@Slive@S56040r5oxGpj5PwT_550.flv?wsSecret=0b0671eddccc75258a70d0cea727fdff&wsTime=1460093653/cdn@=ws/isp2p@=0/did@=0C586D24974420E4E40D4670400339E6/ps@=2/ct@=0/
```