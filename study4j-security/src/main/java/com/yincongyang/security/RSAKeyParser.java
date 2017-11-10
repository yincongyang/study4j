package com.yincongyang.security;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

/**
 * RSA 公私钥信息解析器
 * Created by yincongyang on 17/11/7.
 */
public class RSAKeyParser {

    public static void readKeyFromCer(URL url) throws Exception {
        System.out.println("公钥所在路径:"+url.getFile());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(url.getFile()));

        // 获得证书版本
        System.out.println("证书版本:" + String.valueOf(cert.getVersion()));
        // 获得证书序列号
        System.out.println("证书序列号:" + cert.getSerialNumber().toString(16));
        // 获得证书有效期
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("证书生效日期:" + dateformat.format(cert.getNotBefore()));
        System.out.println("证书失效日期:" + dateformat.format(cert.getNotAfter()));
        // 获得证书主体信息
        System.out.println("证书拥有者:" + cert.getSubjectDN().getName());
        // 获得证书颁发者信息
        System.out.println("证书颁发者:" + cert.getIssuerDN().getName());
        // 获得证书签名算法名称
        System.out.println("证书签名算法:" + cert.getSigAlgName());
        // 获得公钥内容
        PublicKey publicKey = cert.getPublicKey();
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String publicKeyString = base64Encoder.encode(publicKey.getEncoded());
        System.out.println("公钥内容:\r\n" + publicKeyString);
    }

    public static void main(String[] args) throws Exception {
//        String fileName = new String ("yifubao_pro_3.cer".getBytes("UTF-8"));
        String fileName = URLEncoder.encode("yifubao_pro_3.cer","UTF-8");
        URL url = RSAKeyParser.class.getClassLoader().getResource("yifubao_test.cer");   //证书路径
        RSAKeyParser.readKeyFromCer(url);
    }
}
