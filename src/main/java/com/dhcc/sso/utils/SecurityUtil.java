package com.dhcc.sso.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Annotation:
 * 安全工具
 *
 * @Author: Adam Ming
 * @Date: Jul 18, 2019 at 4:05:20 PM
 */
public class SecurityUtil {
    private static Log logger = LogFactory.getLog(SecurityUtil.class);

    public static String md5(String password) {
        MessageDigest md;
        String result = "";
        try {
            md = MessageDigest.getInstance("MD5"); // Hash 算法，MD5

            //面向字节处理，所以可以处理字节的东西，如图片，压缩包。。
            byte[] input = password.getBytes();
            byte[] output = md.digest(input);

            //将md5处理后的output结果利用Base64转成原有的字符串,不会乱码
            result = Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }

        return result;
    }

    public static void main(String[] args) {
        String result = SecurityUtil.md5("MTU3MDYxMTAyNTk1NDEyMzEyMw==123");
        System.out.println(result);
    }
}
