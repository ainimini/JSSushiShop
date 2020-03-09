package com.junshou.oauth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    /***
     * 校验jwt令牌
     */
    @Test
    public void testVerify(){
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2Z3x/7KyUZlHXA6dP+WwimZqrvijHbMIIyTD+F3v7KVLoILCQqKbXh9PD9h3YGOz3q81Xgh8VSulLmdrRHkHryIZoU/8VtgZv2qRolrBKtkb0JQJBXqH4E1bvqbdEIrwKV3VkG2Y5+V/C1uhQdb//Z+zRZP0f/cE23NT2vDcBaB9Kul2eU6IA3hmjtNJFm3X4+TR0jEdYOAmJqrZ/pd5nYAoTtJHeDmXfQemuFDPf6iO1lS2PPfX18r1fzam9Hy/LunuYBGD7G0RsP5n0u5eqeHGaTtR6+LqFGAcZsWliAeKBpuhOmFraS/bVYwHxDRioNFf9KPz1I8SFStNMv6S5wIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4Mzc3MzI3OCwiYXV0aG9yaXRpZXMiOlsianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9saXN0IiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9iYXNlIiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9kZWwiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlX3B1Ymxpc2giLCJhZG1pbiIsImNvdXJzZV9maW5kX2xpc3QiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlIiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9tYXJrZXQiLCJqc190ZWFjaG1hbmFnZXIiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlX2FkZCIsImpzX3RlYWNobWFuYWdlcl9jb3Vyc2VfcGxhbiJdLCJqdGkiOiJjYWI3OWQyZC1mY2ZhLTQyNTgtYjgzMi0wNGYxOGY4MTkyY2YiLCJjbGllbnRfaWQiOiJqdW5zaG91IiwidXNlcm5hbWUiOiIxMTEifQ.vfepnhhLxP094315N44vDtqObnumysKDxJerUVZliAGD9QrnaT_ZP9P_MLAQmJtYFjmxfAEQyIYRR3uA_PWKQvR_CSwAkLJncgwM4NvnlnmlwXNtmRg6HWY0JN1rHAbj6kppTEY2D5OVc2-8lxGReAgwmkhdoNUTDQO8xTDTBbDyngP0uugOa_uWKhkwOPqEdfoUVXVX6rQ_RiidcBop79acf3wES7K7IFaVOSRXsliYvNYWhA_7rqi8PcSRIWRuETBVdD5D8Gky1P_9HVJrqEAqV3FZfGRjRD3fqeYQVK2XeB0Ebwh_pkvmrum6AvPd5Hke6uMUyMFHDszvp6K";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
