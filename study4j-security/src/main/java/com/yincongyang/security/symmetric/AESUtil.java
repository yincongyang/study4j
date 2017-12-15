package com.yincongyang.security.symmetric;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * TODO:加密文档编写
 *
 * 对称加密算法AES的应用
 * Created by yincongyang on 17/11/21.
 */
public class AESUtil {




    /**
     * 生成符合AES规范的Key
     * @return
     * @throws Exception
     */
    @Test
    public void initKey() throws Exception{
        //实例化
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度
        kgen.init(128);
        //生成密钥
        SecretKey skey = kgen.generateKey();

        //返回密钥的字节数组
        byte[] bytes = skey.getEncoded();
        String format = skey.getFormat();
        String algorithm = skey.getAlgorithm();

        System.out.println(Hex.encodeHex(bytes));
        System.out.println(format);
        System.out.println(algorithm);


    }


    @Test
    public void test() throws DecoderException {
        String key = "09a76834c468dc7952144cb071e11ddc";

        byte[] bytes = Hex.decodeHex(key.toCharArray());

        for(int i=0; i<bytes.length; i++){
            byte[] temp = new byte[]{bytes[i]};
            System.out.print(bytes[i]);
            System.out.print(":");
            BigInteger temInteger = new BigInteger(temp);
            System.out.print(temInteger.toString(2));
            System.out.print(":");
            System.out.println(temInteger.bitLength());
        }

        BigInteger bigInteger = new BigInteger(bytes);

        System.out.println(bigInteger.bitLength());
        System.out.println(bytes.length);
    }

}
