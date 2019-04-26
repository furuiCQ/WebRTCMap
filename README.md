# WebRTCMap

## 项目介绍 

本项目为https://blog.csdn.net/u011077027/article/details/86225524
对应的JAVA服务端版本。

端口配置如下：

```java
# 端口
server.port=3000 #这里是https的端口号
# 签名路径
#配置模SSL
server.ssl.key-store=keystore.p12 
server.ssl.key-store-password=19831004
server.ssl.keyStoreType=PKCS12
server.ssl.key-alias=tomcat
```



如需开启http端口号则配置在TomcatConfiguration中有提到

这里提出关键代码

```java
//配置监听http 8080
    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8080);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(3000);
        return connector;
    }
```

http和https的都支持。需要进行Nginx配置
https需要针对博客中的代理配置修改下

```java
  #wss 反向代理  
  location /wss {
     proxy_pass https://websocket/; # 原文中的http改成https代理到上面的地址去
     proxy_read_timeout 100s;
     proxy_set_header Host $host;
     proxy_set_header X-Real_IP $remote_addr;
     proxy_set_header X-Forwarded-for $remote_addr;
     proxy_set_header Upgrade $http_upgrade;
     proxy_set_header Connection 'Upgrade';	
  }
```


## Webrtc相关知识点
### Webrtc基础概念
#### 一、协议

[协议]: https://baike.baidu.com/item/nat/320024?fr=aladdin
https://www.cnblogs.com/mlgjb/p/8243690.html[P2P技术之STUN、TURN、ICE详解]
https://blog.csdn.net/andioss/article/details/44806873[STUN的白话解释]
https://blog.csdn.net/a1989a132/article/details/17139003[P2P在NAT和STUN]
1.NAT
NAT（Network Address Translation，网络地址转换）是1994年提出的。当在专用网内部的一些主机本来已经分配到了本地IP地址（即仅在本专用网内使用的专用地址），但现在又想和因特网上的主机通信（并不需要加密）时，可使用NAT方法。这种通过使用少量的公有IP 地址代表较多的私有IP 地址的方式，将有助于减缓可用的IP地址空间的枯竭。

NAT实现方式：即静态转换Static Nat、动态转换Dynamic Nat和端口多路复用OverLoad
NAT穿透方法：目前常用的针对UDP的NAT 穿透（NAT Traversal）方法主要有：STUN、TURN、ICE、uPnP等。其中ICE方式由于其结合了STUN和TURN的特点，所以使用最为广泛。针对TCP的NAT穿透技术目前仍为难点。实用的技术仍然不多。

NAT工作原理：NAT将自动修改IP报文的源IP地址和目的IP地址，Ip地址校验则在NAT处理过程中自动完成。有些应用程序将源IP地址嵌入到IP报文的数据部分中，所以还需要同时对报文的数据部分进行修改，以匹配IP头中已经修改过的源IP地址。否则，在报文数据部分嵌入IP地址的应用程序就不能正常工作。简单来讲就是通过一个转换头，将内网地址变为公网地址，接收的时候根据记录将公网地址变成内网，完成传输。

2.STUN

是一种网络协议，它一种网络协议，它允许位于NAT（或多重NAT）后的客户端找出自己的公网地址，查出自己位于哪种类型的NAT之后以及NAT为某一 个本地端口所绑定的Internet端端口。**这些信息被用来在两个同时处于NAT 路由器之后的主机之间建立UDP通信。**

3.TURN
4.SDP
5.

#### 二、待补充

