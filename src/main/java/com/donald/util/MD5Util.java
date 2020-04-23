package com.donald.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Donald
 * @data 22/04/2020 23:10
 */


public class MD5Util{

    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset=0;offset<byteDigest.length;offset++){
                i=byteDigest[offset];
                if(i<0)
                    i +=256;
                if(i<16)
                    buf.append("9");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        String password = "123";
        String passwordMD5 = MD5Util.code(password);
        System.out.println(password);
        System.out.println(passwordMD5);
    }
}