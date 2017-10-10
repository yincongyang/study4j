# java个人学习项目

本项目记录一些个人学习java的总结和项目实践,作为自己的技术积累。

该项目主要用maven管理。

## 项目结构
```
study4j/(父工程)
  |--study4j-base/(java基础)
     |--algorithm/(算法)
     |--concept/(Java基础概念和特性)
     |--structure/(数据结构)
        |--basetype/(基本数据类型总结)
        |--container/(Java集合总结)
     |--io/(java io)
     |--nio/(java nio)
  |--study4j-design/(设计模式)
  |--study4j-network/(网络编程)
     |--httpclient/(httpclient4.5.x的使用)
  |--study4j-thread/(多线程)
     |--atomic/(原子类)
     |--base/(多线程基础)
     |--concept/(基础概念)
     |--container(线程安全的集合类)
     |--lock(JUC包中的锁)
     |--pool(线程池)
  |--study4j-utils/(常用工具类的使用：如guava)
```


## 文档和代码
- [Java 基础类型总结](./study4j-base/src/main/java/com/yincongyang/base/structure/基本数据类型.md)：[相关代码](./study4j-base/src/main/java/com/yincongyang/base/structure/basetype)
- [Java 集合总结](./study4j-base/src/main/java/com/yincongyang/base/structure/Java集合总结.md)：[相关代码](./study4j-base/src/main/java/com/yincongyang/base/structure/container)
- [Java IO总结](./study4j-base/src/main/java/com/yincongyang/base/io/readme.md)：[相关代码](https://github.com/yincongyang/study4j/blob/master/study4j-base/src/main/java/com/yincongyang/base/io)
- Java Thread总结
    - [基础概念](./study4j-thread/src/main/java/com/yincongyang/thread/concept)
    - [多线程基础](./study4j-thread/src/main/java/com/yincongyang/thread/多线程基础.md)：[相关代码](./study4j-thread/src/main/java/com/yincongyang/thread/base)
    - [原子类](./study4j-thread/src/main/java/com/yincongyang/thread/原子类.md)：[相关代码](./study4j-thread/src/main/java/com/yincongyang/thread/atomic)
    - [并发容器](./study4j-thread/src/main/java/com/yincongyang/thread/并发容器.md)：[相关代码](./study4j-thread/src/main/java/com/yincongyang/thread/container)
    - [并发锁](./study4j-thread/src/main/java/com/yincongyang/thread/并发锁.md)：[相关代码](./study4j-thread/src/main/java/com/yincongyang/thread/lock)
    - [线程池](./study4j-thread/src/main/java/com/yincongyang/thread/线程池.md)：[相关代码](./study4j-thread/src/main/java/com/yincongyang/thread/pool)


## 读书笔记
- [Java并发编程实战](./study4j-thread/src/main/java/com/yincongyang/thread/《Java并发编程实战》读书笔记.md)
- Think in java
- Effective Java2
- Guava官方文档


## TODO LIST

### TODO
- java基础：NIO
- 缓存：redis/codis,内存缓存,文件缓存
- guava：集合，IO，常用工具类
- 网络编程：TCP/IP，HttpClient，webservice
- 并发编程：多线程基础，线程安全，调试，线程监控

### DOING
HttpClient，TCP/IP

### DONE
- [ ] java基础
  - [x] 基础类型学习
  - [x] java集合类
  - [x] IO
  - [ ] NIO
  - [x] 多线程
- [ ] 缓存
  - [x] 缓存管理器
  - [x] redis/codis
  - [x] 文件缓存
  - [ ] 内存缓存
- [ ] guava
  - [ ] 集合
  - [ ] IO
  - [ ] 常用工具类
- [ ] 网络编程
  - [ ] TCP/IP
  - [ ] HttpClient
  - [ ] webservice
- [ ] 并发编程