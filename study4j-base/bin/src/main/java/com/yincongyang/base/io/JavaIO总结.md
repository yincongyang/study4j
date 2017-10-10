# Java IO总结

## java IO 简介
java io系统的设计初衷，就是为了实现“文件、控制台、网络设备”这些io设置的通信。例如，对于一个文件，我们可以打开文件，然后进行读取和写入。
在java 1.0中，java提供的类都是以字节(byte)为单位，例如，FileInputStream和FileOutputStream。
而到了java 1.1，为了与国际化进行接轨，在java io中添加了许多以字符(char)为单位进行操作的类。

在java io的称呼中，我们经常会提到“输入流”、“输出流”等等概念。首先，什么是流呢？
所谓“流”，就是一种抽象的数据的总称，它的本质是能够进行传输。

-  "流"是不能够重复读取的，因为其内部指针不会重置。
-  按照“流”的数据流向，可以将其化分为：输入流和输出流。
-  按照“流”中处理数据的单位，可以将其区分为：字节流和字符流。在java中，字节是占1个Byte，即8位；而字符是占2个Byte，即16位。而且，需要注意的是，java的字节是有符号类型，而字符是无符号类型！

## java.io包下主要类结构
![](../../../../../resources/img/javaIO.png)

## 字节流简介
### InputStream
- InputStream 是以字节为单位的输入流的超类。InputStream提供了read()接口从输入流中读取字节数据。
- ByteArrayInputStream 是字节数组输入流。它包含一个内部缓冲区，该缓冲区包含从流中读取的字节；通俗点说，它的内部缓冲区就是一个字节数组，而ByteArrayInputStream本质就是通过字节数组来实现的。
- PipedInputStream 是管道输入流，它和PipedOutputStream一起使用，能实现多线程间的管道通信。
- FilterInputStream 是过滤输入流。它是DataInputStream和BufferedInputStream的超类。
- DataInputStream 是数据输入流。它是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
- BufferedInputStream 是缓冲输入流。它的作用是为另一个输入流添加缓冲功能。
- FileInputStream 是文件输入流。它通常用于对文件进行读取操作。
- ObjectInputStream 是对象输入流。它和ObjectOutputStream一起，用来提供对“基本数据或对象”的持久存储。

### OutputStream
- OutputStream 是以字节为单位的输出流的超类。OutputStream提供了write()接口从输出流中读取字节数据。
- ByteArrayOutputStream 是字节数组输出流。写入ByteArrayOutputStream的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。
- PipedOutputStream 是管道输出流，它和PipedInputStream一起使用，能实现多线程间的管道通信。
- FilterOutputStream 是过滤输出流。它是DataOutputStream，BufferedOutputStream和PrintStream的超类。
- DataOutputStream 是数据输出流。它是用来装饰其它输出流，它“允许应用程序以与机器无关方式向底层写入基本 Java 数据类型”。
- BufferedOutputStream 是缓冲输出流。它的作用是为另一个输出流添加缓冲功能。
- PrintStream 是打印输出流。它是用来装饰其它输出流，能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
- FileOutputStream 是文件输出流。它通常用于向文件进行写入操作。
- ObjectOutputStream 是对象输出流。它和ObjectInputStream一起，用来提供对“基本数据或对象”的持久存储。

## 字符流简介
### Reader
- Reader 是以字符为单位的输入流的超类。它提供了read()接口来取字符数据。
- CharArrayReader 是字符数组输入流。它用于读取字符数组，它继承于Reader。操作的数据是以字符为单位！
- PipedReader 是字符类型的管道输入流。它和PipedWriter一起是可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedWriter和PipedReader配套使用。
- FilterReader 是字符类型的过滤输入流。
- BufferedReader 是字符缓冲输入流。它的作用是为另一个输入流添加缓冲功能。
- InputStreamReader 是字节转字符的输入流。它是字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。
- FileReader 是字符类型的文件输入流。它通常用于对文件进行读取操作

### Writer
- Writer 是以字符为单位的输出流的超类。它提供了write()接口往其中写入数据。
- CharArrayWriter 是字符数组输出流。它用于读取字符数组，它继承于Writer。操作的数据是以字符为单位！
- PipedWriter 是字符类型的管道输出流。它和PipedReader一起是可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedWriter和PipedWriter配套使用。
- FilterWriter 是字符类型的过滤输出流。
- BufferedWriter 是字符缓冲输出流。它的作用是为另一个输出流添加缓冲功能。
- OutputStreamWriter 是字节转字符的输出流。它是字节流通向字符流的桥梁：它使用指定的 charset 将字节转换为字符并写入。
- FileWriter 是字符类型的文件输出流。它通常用于对文件进行读取操作。
- PrintWriter 是字符类型的打印输出流。它是用来装饰其它输出流，能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。

## 序列化
### Serializable
序列化，就是为了保存对象的状态；而与之对应的反序列化，则可以把保存的对象状态再读出来。
简言之：序列化/反序列化，是Java提供一种专门用于的保存/恢复对象状态的机制。

一般在以下几种情况下，我们可能会用到序列化：
  a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候； 
  b）当你想用套接字在网络上传送对象的时候； 
  c）当你想通过RMI传输对象的时候。

特点：
- java“基本类型”、“java自带的支持Serializable接口的类”和“自定义实现Serializable接口的类”都能支持序列化。
- 以下两种类成员变量是不会被序列化的
  - 序列化对static和transient变量，是不会自动进行状态保存的。
    transient的作用就是，用transient声明的变量，不会被自动序列化。
  - 对于Socket, Thread类，不支持序列化。若实现序列化的接口中，有Thread成员；在对该类进行序列化操作时，编译会出错！
    这主要是基于资源分配方面的原因。如果Socket，Thread类可以被序列化，但是被反序列化之后也无法对他们进行重新的资源分配；再者，也是没有必要这样实现。
    若需要序列化Thread类，则需要将其声明成static||transient
- 在类中重写两个方法writeObject()和readObject()，则可以实现自定义保存的对象，如static或transient变量

### Externalizable(不常用)
如果一个类要完全负责自己的序列化，则实现Externalizable接口，而不是Serializable接口。
Externalizable接口定义包括两个方法writeExternal()与readExternal()。
需要注意的是：声明类实现Externalizable接口会有重大的安全风险。writeExternal()与readExternal()方法声明为public，恶意类可以用这些方法读取和写入对象数据。如果对象包含敏感信息，则要格外小心。

特点：
- 实现Externalizable接口的类，不会像实现Serializable接口那样，会自动将数据保存。
- 实现Externalizable接口的类，必须实现writeExternal()和readExternal()接口！否则，程序无法正常编译！
- 实现Externalizable接口的类，必须定义不带参数的构造函数！否则，程序无法正常编译！
- writeExternal() 和 readExternal() 的方法都是public的，不是非常安全！

## 文件操作
### File
- File 是“文件”和“目录路径名”的抽象表示形式。
- File 直接继承于Object，实现了Serializable接口和Comparable接口。实现Serializable接口，意味着File对象支持序列化操作。
- 而实现Comparable接口，意味着File对象之间可以比较大小；File能直接被存储在有序集合(如TreeSet、TreeMap中)。

### FileDescriptor
- FileDescriptor 是“文件描述符”。
- FileDescriptor 可以被用来表示开放文件、开放套接字等。
- 以FileDescriptor表示文件来说：当FileDescriptor表示某文件时，我们可以通俗的将FileDescriptor看成是该文件。
- 但是，我们不能直接通过FileDescriptor对该文件进行操作；若需要通过FileDescriptor对该文件进行操作，则需要新创建FileDescriptor对应的FileOutputStream，再对文件进行操作。


### RandomAccessFile
RandomAccessFile 是随机访问文件(包括读/写)的类。它支持对文件随机访问的读取和写入，即我们可以从指定的位置读取/写入文件数据。
需要注意的是，RandomAccessFile 虽然属于java.io包，但它不是InputStream或者OutputStream的子类；它也不同于FileInputStream和FileOutputStream。 FileInputStream 只能对文件进行读操作，而FileOutputStream 只能对文件进行写操作；但是，RandomAccessFile 同时支持文件的读和写，并且它支持随机访问。

RandomAccessFile共有4种模式："r", "rw", "rws"和"rwd"。
- "r"    以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。  
- "rw"   打开以便读取和写入。
- "rws"  打开以便读取和写入。相对于 "rw"，"rws" 还要求对“文件的内容”或“元数据”的每个更新都同步写入到基础存储设备。  
- "rwd"  打开以便读取和写入，相对于 "rw"，"rwd" 还要求对“文件的内容”的每个更新都同步写入到基础存储设备。


## 分词器
### StreamTokenizer
- StreamTokenizer默认按照空格进行文字的分割，可以根据其进行英文字符分割。统计字数，词频等
- 默认情况下，StreamTokenizer认为下列内容是Token: 字母、数字、除C和C++注释符号以外的其他符号。
  如符号"/"不是Token，注释后的内容也不是，而"\"是Token。单引号和双引号以及其中的内容，只能算是一个Token。
  统计文章字符数的程序，不是简单的统计Token数就万事大吉，因为字符数不等于Token。按照Token的规定，
  引号中的内容就算是10页也算一个Token。如果希望引号和引号中的内容都算作Token，应该调用下面的代码：
  st.ordinaryChar('\'');
  
### StringTokenizer
效果等同于split,不推荐使用

## IOException
- 在进行流操作时，需要时刻注意对于IOException的处理。标准异常处理见[代码](./exception/IOExceptionExample.java)
- 及时进行close()操作。
- 对于使用了缓存类，需要及时进行flush()操作


参考文章:
- [Java IO系列](http://www.cnblogs.com/skywang12345/p/io_01.html)
- [Java IO教程](http://ifeve.com/java-io/)
