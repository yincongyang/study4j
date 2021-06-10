package com.yincongyang.study4j.tools.httpclient.utils;

import com.yincongyang.study4j.tools.httpclient.request.HttpsExample;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 默认的HTTPS不信任自签名的证书
 * <p>
 * 该类创建一个支持所有证书的SSLConnectionSocketFactory
 * <p>
 * 用法：
 * CloseableHttpClient client = HttpClients
 * .custom()
 * .setSSLSocketFactory(TrustAllCASSLConnectionSocketFactory.INSTANCE)
 * .build();
 * <p>
 * Created by yincongyang on 17/10/9.
 */
public class TrustAllCASSLConnectionSocketFactory {
    private final static Logger logger = LoggerFactory.getLogger(TrustAllCASSLConnectionSocketFactory.class);

    public static final SSLConnectionSocketFactory INSTANCE = TrustAllCASSLConnectionSocketFactory.build();

    private TrustAllCASSLConnectionSocketFactory() {

    }

    /**
     * 创建支持所有证书的SSLConnectionSocketFactory
     *
     * @return
     */
    private static SSLConnectionSocketFactory build() {
        // 支持所有证书
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts
                    .custom()
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    })
                    .build();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(),e);
        } catch (KeyManagementException e) {
            logger.error(e.getMessage(),e);
        } catch (KeyStoreException e) {
            logger.error(e.getMessage(),e);
        }

        // 先服务器端主要使用TLS 加密套接字（CipherSuite），SSL有漏洞，基本不用
        return new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2", "SSLv2Hello", "SSLv3"},
                null,
                NoopHostnameVerifier.INSTANCE);
    }

}
