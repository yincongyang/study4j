# httpClient4.5.X 教程与示例

## Http 基础
HTTP协议是TCP/IP协议簇中的一个，在经典的4层网络架构中，HTTP属于应用层。
下面列出与HTTP有关的协议。

- 应用层：HTTP，DNS：根据域名查询对应IP地址
- 传输层：TCP：保证数据传输的完整性和可靠性，3次握手，数据分包等
- 网络层：IP：网际协议，使用ARP协议根据IP地址反查出MAC地址，然后传给链路层
- 链路层：物理传输层，

## HTTP报文组成

### 请求报文组成
- 报文首部
    - 请求行：请求方法+请求URI+协议版本
    - 请求首部(可选)
    - 通用首部(可选)
    - 实体首部(可选)
- 空行【CR+LF】
- 报文主体：待发送的报文内容

### 响应报文组成
- 报文首部
    - 状态行：协议版本+状态码+状态原因短语
    - 响应首部(可选)
    - 通用首部(可选)
    - 实体首部(可选)
- 空行【CR+LF】
- 报文主体：待发送的报文内容

## Http Request
所有`HTTP`请求都有一个请求起始行，这个起始行由`方法名`、`请求URI`和`HTTP协议版本`组成。

### 方法名
`HttpClient`很好地支持了`HTTP/1.1`规范中所有的HTTP方法：`GET，HEAD， POST，PUT， DELETE， TRACE 和 OPTIONS`。
每个方法都有一个特别的类：`HttpGet，HttpHead， HttpPost，HttpPut，HttpDelete，HttpTrace和HttpOptions`。
```
HttpGet httpget = new HttpGet("http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
```

### 请求URI
HttpClient提供了URIBuilder工具类来简化创建、修改请求 URIs。
```
URI uri = new URIBuilder()  
          .setScheme("http")  
          .setHost("www.google.com")  
          .setPath("/search")  
          .setParameter("q", "httpclient")  
          .setParameter("btnG", "Google Search")  
          .setParameter("aq", "f")  
          .setParameter("oq", "")  
          .build();  
HttpGet httpget = new HttpGet(uri);  
System.out.println(httpget.getURI());</span> 
```

### HTTP协议版本
基本均使用HTTP/1.1版本。


## Http Response
`HTTP Response`是服务器接收并解析请求信息后返回给客户端的信息，它的起始行包含了`协议版本`，`状态码`，`描述状态的短语`。
```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1  
                             ,HttpStatus.SC_OK, "OK");  
System.out.println(response.getProtocolVersion());  
System.out.println(response.getStatusLine().getStatusCode());  
System.out.println(response.getStatusLine().getReasonPhrase());  
System.out.println(response.getStatusLine().toString()); 
```
输出：
```
HTTP/1.1  
200  
OK  
HTTP/1.1 200 OK  
```

## Headers

- 一个HTTP报文包含了许多描述报文的首部，比如内容长度，内容类型等。HttpClient提供了一些方法来取出，添加，移除，枚举首部。
```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK, "OK");  
response.addHeader("Set-Cookie","c1=a; path=/; domain=localhost");  
response.addHeader("Set-Cookie","c2=b; path=\"/\", c3=c; domain=\"localhost\"");  
Header h1 = response.getFirstHeader("Set-Cookie");  
System.out.println(h1);  
Header h2 = response.getLastHeader("Set-Cookie");  
System.out.println(h2);  
Header[] hs = response.getHeaders("Set-Cookie");  
System.out.println(hs.length); 
```
输出：
```
Set-Cookie: c1=a; path=/; domain=localhost
Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
2
```

- `HttpClient`也提供了更方便的方法遍历`Headers`，即使用`HeaderIterator`
```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 
    HttpStatus.SC_OK, "OK");
response.addHeader("Set-Cookie", 
    "c1=a; path=/; domain=localhost");
response.addHeader("Set-Cookie", 
    "c2=b; path=\"/\", c3=c; domain=\"localhost\"");

HeaderIterator it = response.headerIterator("Set-Cookie");

while (it.hasNext()) {
    System.out.println(it.next());
}
```
输出：
```
Set-Cookie: c1=a; path=/; domain=localhost
Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
```

- `HttpClient`也可以将`Headers`拆分成单个元素。
```
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 
    HttpStatus.SC_OK, "OK");
response.addHeader("Set-Cookie", 
    "c1=a; path=/; domain=localhost");
response.addHeader("Set-Cookie", 
    "c2=b; path=\"/\", c3=c; domain=\"localhost\"");

HeaderElementIterator it = new BasicHeaderElementIterator(
    response.headerIterator("Set-Cookie"));

while (it.hasNext()) {
    HeaderElement elem = it.nextElement(); 
    System.out.println(elem.getName() + " = " + elem.getValue());
    NameValuePair[] params = elem.getParameters();
    for (int i = 0; i < params.length; i++) {
        System.out.println(" " + params[i]);
    }
}
```
输出：
```
c1 = a
path=/
domain=localhost
c2 = b
path=/
c3 = c
domain=localhost
```

## HTTP Entity
`Entity`分为请求实体和响应实体。

- 请求实体：PUT，POST里面会包含请求实体
- 响应实体：大部分响应都会包含响应实体，但有些响应不符合这一规则，比如，对HEAD方法的响应和状态为204 No Content, 304 Not Modified, 205 Reset Content的响应。

HttpClient将实体分为3种：
- 流式实体(streamed)：不可重复读取，如：
- 自包含实体(self-contained)：可以重复读取，如：ByteArrayEntity,StringEntity
- 包装实体(wrapping)：

`Entity`有`实体首部`和`实体报文`两部分组成
```
StringEntity myEntity = new StringEntity("important message",  
                          ContentType.create("text/plain", "UTF-8"));  
System.out.println(myEntity.getContentType());  
System.out.println(myEntity.getContentLength());  
System.out.println(EntityUtils.toString(myEntity));  
System.out.println(EntityUtils.toByteArray(myEntity).length);  
```
输出
```
Content-Type: text/plain; charset=utf-8
17
important message
17
```


### 正确关闭流
一次http请求不仅需要关闭响应本身，还需要确保关闭内容流。
```
CloseableHttpClient httpclient = HttpClients.createDefault();  
HttpGet httpget = new HttpGet("http://localhost/");  
CloseableHttpResponse response = httpclient.execute(httpget);  
try {  
     HttpEntity entity = response.getEntity();  
     if (entity != null) {  
        InputStream instream = entity.getContent();  
        try {  
            // do something useful  
        } finally {  
            instream.close();//关闭内容流
        }  
   }  
} finally {  
     response.close();//关闭响应本身
} 
```

### EntityUtils
HttpClient提供了`EntityUtils`来操作实体内容，但是不推荐使用？？

### BufferedHttpEntity
HttpClient提供了`BufferedHttpEntity`可以缓存实体内容，以便于多次读取
```
CloseableHttpResponse response = <...>  
HttpEntity entity = response.getEntity();  
if (entity != null) {  
    entity = new BufferedHttpEntity(entity);//该entity可被重复读取
}  
```

### 不同实体类型
HttpClient提供了几个类，用来通过HTTP连接高效地传输内容。
这些类的实例均与内含实体请求有关，比如POST和PUT，它们能够把实体内容封装进友好的HTTP请求中。
对于基本的数据容器`String, byte array, input stream,file，表单`，
HttpClient为它们提供了几个对应的类：`StringEntity, ByteArrayEntity, InputStreamEntity, FileEntity，UrlEncodedFormEntity`。

- StringEntity，ByteArrayEntity，FileEntity，UrlEncodedFormEntity是可重复读取的实体
- InputStreamEntity是不可重复读取的实体

文件实体：
```
File file = new File("somefile.txt");  
FileEntity entity = new FileEntity(file,ContentType.create("text/plain", "UTF-8"));  
HttpPost httppost = new HttpPost("http://localhost/action.do");  
httppost.setEntity(entity); 
```
表单实体：可以模仿成一个HTML表单
```
List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
formparams.add(new BasicNameValuePair("param1", "value1"));  
formparams.add(new BasicNameValuePair("param2", "value2"));  
UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,Consts.UTF_8);  
HttpPost httppost = new HttpPost("http://localhost/handler.do");  
httppost.setEntity(entity); 
```

### HttpClient支持内容分块

### ResponseHandler（响应处理器）
使用`ResponseHandler`可以不用担心连接的正确释放，使用ResponseHandler时，无论是请求执行成功亦或出现异常，HttpClient将会自动地确保连接释放回连接管理器中。
推荐使用`ResponseHandler`来处理`response`。
```
CloseableHttpClient httpclient = HttpClients.createDefault();  
HttpGet httpget = new HttpGet("http://localhost/json");  
ResponseHandler<MyJsonObject> rh = new ResponseHandler<MyJsonObject>() {  
    @Override  
    public JsonObject handleResponse(final HttpResponse response) throws IOException {  
        StatusLine statusLine = response.getStatusLine();  
        HttpEntity entity = response.getEntity();  
        if (statusLine.getStatusCode() >= 300) {  
            throw new HttpResponseException(statusLine.getStatusCode(),  
                statusLine.getReasonPhrase());  
        }  
        if (entity == null) {  
             throw new ClientProtocolException("Response contains no content");  
        }  
        Gson gson = new GsonBuilder().create();  
        ContentType contentType = ContentType.getOrDefault(entity);  
        Charset charset = contentType.getCharset();  
        Reader reader = new InputStreamReader(entity.getContent(), charset);  
        return gson.fromJson(reader, MyJsonObject.class);  
    }  
};  
MyJsonObject myjson = client.execute(httpget, rh); 
```

## HttpContext（HTTP请求执行上下文）
- 最初，HTTP是被设计成无状态的，面向请求-响应的协议。然而，现实世界中的应用程序经常需要通过一些逻辑相关的请求-响应交换来保持状态信息。 为了使应用程序能够维持一个过程状态， HttpClient允许HTTP请求在一个特定的执行上下文中来执行--称为HTTP上下文。
- 如果相同的上下文在连续请求之间重用，那么多种逻辑相关的请求可以参与到一个逻辑会话中。HTTP上下文功能和java.util.Map<String,Object>很相似。 它仅仅是任意命名参数值的集合。应用程序可以在请求之前填充上下文属性，也可以在执行完成之后来检查上下文属性。
- `HttpContext`不是线程安全的，不应该在多线程中共享一个`HttpContext`。

在HTTP请求执行的这一过程中， HttpClient添加了下列属性到执行上下文中：
- `HttpConnection`实例代表连接到目标服务器的当前连接。
- `HttpHost`实例代表连接到目标服务器的当前连接。
- `HttpRoute`实例代表了完整的连接路由。
- `HttpRequest`实例代表了当前的HTTP请求。最终的HttpRequest对象在执行上下文中总是准确代表了状态信息，因为它已经发送给了服务器。每个默认的HTTP/1.0 和 HTTP/1.1使用相对的请求URI，可是，请求以non-tunneling模式通过代理发送时，URI会是绝对的。
- `HttpResponse`实例代表当前的HTTP响应。
- `java.lang.Boolean`对象是一个标识，它标志着当前请求是否完整地传输给连接目标。
- `RequestConfig`代表当前请求配置。
- `java.util.List<URI>`对象代表一个含有执行请求过程中所有的重定向地址。

```
HttpContext context = <...>  
HttpClientContext clientContext = HttpClientContext.adapt(context);  
HttpHost target = clientContext.getTargetHost();  
HttpRequest request = clientContext.getRequest();  
HttpResponse response = clientContext.getResponse();  
RequestConfig config = clientContext.getRequestConfig(); 
```

## 异常处理
`HttpClient`会抛出两种异常：
- java.io.IOException ：在 I/O 失败时，如socket连接超时或被重置的异常；
- HttpException：标志 HTTP 请求失败的信号，如违反 HTTP 协议。

通常 I/O 错误被认为是非致命的和可以恢复的， 而 HTTP 协议错误，则被认为是致命的而且是不能自动恢复的。
请注意HttpClient实现了可抛出异常`HttpExceptions`为`ClientProtocolException`，也是 java.io.IOException的子类。
这使HttpClient使用者能够在一个单一的catch子句中处理 `IOException` 和`HttpException`。

## 终止请求（HttpUriRequest.abort()）


## HTTP协议拦截器(HttpRequestInterceptor)

- 通常协议拦截器希望作用于一个特定头部信息上，或者一族收到报文的相关头部信息，
  或使用一个特定的头部或一族相关的头部信息填充发出的报文。协议拦截器也可以操纵包含在报文中的内容实体，
  透明的内容压缩/解压就是一个很好的示例。
- HttpRequestInterceptor是非线程安全的

## HttpParams
- HttpParams旨在包含简单对象：整型，浮点型，字符串，集合，还有运行时不变的对象。
- HttpParams希望被用在“一次写入-多处准备”模式下。HttpContext旨在包含很可能在HTTP报文处理这一过程中发生改变的复杂对象
- HttpParams的目标是定义其它组件的行为。通常每一个复杂的组件都有它自己的HttpParams对象。
  HttpContext的目标是来表示一个HTTP处理的执行状态。通常相同的执行上下文在很多合作的对象中共享。

在HTTP请求执行过程中，`HttpRequest`对象的`HttpParams`是和用于执行请求的`HttpClient`实例的`HttpParams`联系在一起的。
这使得设置在`HttpRequest`请求级别的参数优先于设置在`HttpClient`级别的`HttpParams`。
推荐的做法是设置普通参数对所有的在`HttpClient`级别的`HTTP请求共享`，而且可以选择性重写具体在`HttpRequest`级别的参数。

示例：
```
DefaultHttpClient httpclient = new DefaultHttpClient();//HttpClient级别设置HttpParams参数
httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_0);
httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,"UTF-8");

HttpGet httpget = new HttpGet("http://www.google.com/");//HttpRequest级别设置HttpParams参数
httpget.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);//覆盖HttpClient级别设置HttpParams参数
httpget.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,Boolean.FALSE);
httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
    public void process(final HttpRequest request,
    final HttpContext context) throws HttpException, IOException {
    System.out.println(request.getParams().getParameter(
    CoreProtocolPNames.PROTOCOL_VERSION));
    System.out.println(request.getParams().getParameter(
    CoreProtocolPNames.HTTP_CONTENT_CHARSET));
    System.out.println(request.getParams().getParameter(
    CoreProtocolPNames.USE_EXPECT_CONTINUE));
    System.out.println(request.getParams().getParameter(
    CoreProtocolPNames.STRICT_TRANSFER_ENCODING));
    }
});
```
输出：
```
HTTP/1.1
UTF-8
false
null
```

HttpParams不能使用DI框架来组合。为了缓解这个限制，HttpClient包含了一些bean类，
它们可以用来按顺序使用标准的Java eban惯例初始化HttpParams对象
示例：
```
HttpParams params = new BasicHttpParams();
HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
paramsBean.setVersion(HttpVersion.HTTP_1_1);
paramsBean.setContentCharset("UTF-8");
paramsBean.setUseExpectContinue(true);
System.out.println(params.getParameter(
CoreProtocolPNames.PROTOCOL_VERSION));
System.out.println(params.getParameter(
CoreProtocolPNames.HTTP_CONTENT_CHARSET));
System.out.println(params.getParameter(
CoreProtocolPNames.USE_EXPECT_CONTINUE));
System.out.println(params.getParameter(
CoreProtocolPNames.USER_AGENT));
```
输出：
```
HTTP/1.1
UTF-8
false
null
```

## HttpRequest请求参数
这些参数会影响到请求执行的过程：
- `http.protocol.version`：如果没有在请求对象中设置明确的版本信息，它就定义了使用的HTTP协议版本。这个参数期望得到一个ProtocolVersion类型的值。如果这个参数没有被设置，那么就使用HTTP/1.1。
- `http.protocol.element-charset`：定义了编码HTTP协议元素的字符集。这个参数期望得到一个java.lang.String类型的值。如果这个参数没有被设置，那么就使用US-ASCII。
- `http.protocol.eontent-charset`：定义了为每个内容主体编码的默认字符集。这个参数期望得到一个java.lang.String类型的值。如果这个参数没有被设置，那么就使用ISO-8859-1。
- `http.useragent`：定义了头部信息User-Agent的内容。这个参数期望得到一个java.lang.String类型的值。如果这个参数没有被设置，那么HttpClient将会为它自动生成一个值。
- `http.protocol.strict-transfer-encoding`：定义了响应头部信息中是否含有一个非法的Transfer-Encoding，都要拒绝掉。
- `http.protocol.expect-continue`：为包含方法的实体激活Expect: 100-Continue握手。Expect: 100-Continue握手的目的是允许客户端使用请求体发送一个请求信息来决定源服务器是否希望在客户端发送请求体之前得到这个请求（基于请求头部信息）。Expect: 100-Continue握手的使用可以对需要目标服务器认证的包含请求的实体（比如POST和PUT）导致明显的性能改善。Expect: 100-Continue握手应该谨慎使用，因为它和HTTP服务器，不支持HTTP/1.1协议的代理使用会引起问题。这个参数期望得到一个java.lang.Boolean类型的值。如果这个参数没有被设置，那么HttpClient将会试图使用握手。
- `http.protocol.wait-for-continue`：定义了客户端应该等待100-Continue响应最大的毫秒级时间间隔。这个参数期望得到一个java.lang.Integer类型的值。如果这个参数没有被设置，那么HttpClient将会在恢复请求体传输之前为确认等待3秒。

## HTTPS
- HttpClient 默认支持HTTPS连接，但是仅支持获得CA认证证书的https，默认CA证书存放在JRE当中，但是不支持自颁发证书的https连接，如12306等。
- 若要支持自颁发证书的https连接
    - 则需要安装其客户端证书，即将对应网站的客户端证书安装至JRE对应目录中。
    - 或者直接使用`SSLConnectionSocketFactory`相关类加载客户端证书，对其进行认证。
    - 或者客户端不校验服务器端证书的合法性（有安全风险)。

## PoolingHttpClientConnectionManager（连接池管理器）
```
PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();  
// Increase max total connection to 200  
cm.setMaxTotal(200);  
// Increase default max connection per route to 20  
cm.setDefaultMaxPerRoute(20);  
// Increase max connections for localhost:80 to 50  
HttpHost localhost = new HttpHost("locahost", 80);  
cm.setMaxPerRoute(new HttpRoute(localhost), 50);  
CloseableHttpClient httpClient = HttpClients.custom()setConnectionManager(cm).build(); 
```