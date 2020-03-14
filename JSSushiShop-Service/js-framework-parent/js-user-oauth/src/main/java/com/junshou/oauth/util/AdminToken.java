package com.junshou.oauth.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminToken
 * @Description 管理员令牌发放
 * @author X
 * @date 2020/2/10
 * @version 1.0
 **/
public class AdminToken {

    public static String adminToken() {
        //基于私钥生成jwt
        //1. 创建一个秘钥工厂
        //1: 指定私钥的位置
        ClassPathResource classPathResource = new ClassPathResource("junshou.jks");
        //2: 指定秘钥库的密码
        String keyPass = "junshou";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keyPass.toCharArray());

        //2. 基于工厂获取私钥
        String alias = "junshou";
        String password = "junshou";
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
        //将当前的私钥转换为rsa私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //3.生成jwt
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("company", "company");
        map.put("address", "beijing");
        map.put("authorities", new String[]{"oauth"});

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));
        String token = jwt.getEncoded();
        return token;
    }
}
