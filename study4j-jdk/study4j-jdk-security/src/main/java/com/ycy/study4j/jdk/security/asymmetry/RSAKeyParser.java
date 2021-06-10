package com.ycy.study4j.jdk.security.asymmetry;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * RSA 公私钥信息解析器
 * Created by yincongyang on 17/11/7.
 */
public class RSAKeyParser {

    private static final Logger logger = LoggerFactory.getLogger(RSAKeyParser.class);

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * TODO:仍需优化 不知道如何解析
     * 从key文件中读取publickey
     * key文件里存储的是publickey的二进制格式，直接读取出来，并打印即可
     */
    @Test
    public void parsePublicKeyFromKey() throws IOException, CertificateException {
        String filePath = "key/public.key";

        URL url = RSAKeyParser.class.getClassLoader().getResource(filePath);   //证书路径

        InputStream inStream = new FileInputStream(url.getFile());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int ch;
        String res = "";
        while ((ch = inStream.read()) != -1)
        {
            out.write(ch);
        }
        byte[] result = out.toByteArray();
        System.out.println(out.toString());

    }

    /**
     * 从pfx读取私钥及其对应公钥内容
     * @throws Exception
     */
    @Test
    public void parserPrivateKeyFromPfx() throws Exception {
        String p = "1234qwer";
        String filePath = "pfx/jnf.pfx";

        URL url = RSAKeyParser.class.getClassLoader().getResource(filePath);   //证书路径
        logger.info("私钥路径：{}",url.getFile());

        //根据pfx证书和password获取keyStore
        FileInputStream fis = new FileInputStream(url.getFile());
        KeyStore ks = KeyStore.getInstance("PKCS12");
        char[] password = p.toCharArray();
        ks.load(fis, password);
        fis.close();

        //从keyStore中读取privatekey
        Enumeration<?> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = (String) enumas.nextElement();
        }
        PrivateKey privatekey = (PrivateKey) ks.getKey(keyAlias, password);

        logger.info("私钥算法:" + privatekey.getAlgorithm());
        logger.info("私钥Base64内容:\r\n" + new BASE64Encoder().encode(privatekey.getEncoded()));

        //从keyStore中生成对应公钥证书
        X509Certificate cert = (X509Certificate)ks.getCertificate(keyAlias);
        // 获得证书版本
        logger.info("证书版本:" + String.valueOf(cert.getVersion()));
        // 获得证书序列号
        logger.info("证书序列号:" + cert.getSerialNumber().toString(16));
        // 获得证书有效期
        logger.info("证书生效日期:" + dateformat.format(cert.getNotBefore()));
        logger.info("证书失效日期:" + dateformat.format(cert.getNotAfter()));
        // 获得证书主体信息
        logger.info("证书拥有者:" + cert.getSubjectDN().getName());
        // 获得证书颁发者信息
        logger.info("证书颁发者:" + cert.getIssuerDN().getName());
        // 获得证书签名算法名称
        logger.info("证书签名算法:" + cert.getSigAlgName());
        // 获得公钥内容
        PublicKey publicKey = cert.getPublicKey();
        String publicKeyString = new BASE64Encoder().encode(publicKey.getEncoded());
        logger.info("公钥算法:" + publicKey.getAlgorithm());
        logger.info("公钥信息:" + publicKey.toString());
        logger.info("公钥Base64内容:\r\n" + publicKeyString);

        String algorithm = publicKey.getAlgorithm(); // 获取算法
        KeyFactory keyFact = KeyFactory.getInstance(algorithm);
        BigInteger publicSize = null;
        if ("RSA".equals(algorithm)) { // 如果是RSA加密
            RSAPublicKeySpec pubkeySpec = (RSAPublicKeySpec)keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
            publicSize = pubkeySpec.getModulus();
        } else if ("DSA".equals(algorithm)) { // 如果是DSA加密
            DSAPublicKeySpec pubkeySpec = (DSAPublicKeySpec)keyFact.getKeySpec(publicKey, DSAPublicKeySpec.class);
            publicSize = pubkeySpec.getP();
        }
        logger.info("公钥位数:" + publicSize.toString(2).length());
    }

    /**
     * 从.cer证书中读取公钥相关信息：公钥内容，证书生效日期，失效日期，颁发机构，签名算法等
     * @throws Exception
     */
    @Test
    public void parserPublicKeyFromCer() throws Exception {
        String filePath = "cer/yifubao_test.cer";
//        String filePath = "key/public.key";

        //用 java.security.cert.X509Certificate类初始化证书
        URL url = RSAKeyParser.class.getClassLoader().getResource(filePath);   //证书路径
        logger.info("公钥所在路径:"+url.getFile());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(url.getFile()));

        // 获得证书版本
        logger.info("证书版本:" + String.valueOf(cert.getVersion()));
        // 获得证书序列号
        logger.info("证书序列号:" + cert.getSerialNumber().toString(16));
        // 获得证书有效期
        logger.info("证书生效日期:" + dateformat.format(cert.getNotBefore()));
        logger.info("证书失效日期:" + dateformat.format(cert.getNotAfter()));
        // 获得证书主体信息
        logger.info("证书拥有者:" + cert.getSubjectDN().getName());
        // 获得证书颁发者信息
        logger.info("证书颁发者:" + cert.getIssuerDN().getName());
        // 获得证书签名算法名称
        logger.info("证书签名算法:" + cert.getSigAlgName());
        // 获得公钥内容
        PublicKey publicKey = cert.getPublicKey();
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String publicKeyString = base64Encoder.encode(publicKey.getEncoded());
        logger.info("公钥内容:\r\n" + publicKeyString);
    }
}
