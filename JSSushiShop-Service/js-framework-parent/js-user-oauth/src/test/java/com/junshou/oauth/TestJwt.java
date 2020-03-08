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
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MzU2Mzk3OCwiYXV0aG9yaXRpZXMiOlsianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9saXN0IiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9iYXNlIiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9kZWwiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlX3B1Ymxpc2giLCJhZG1pbiIsImNvdXJzZV9maW5kX2xpc3QiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlIiwianNfdGVhY2htYW5hZ2VyX2NvdXJzZV9tYXJrZXQiLCJqc190ZWFjaG1hbmFnZXIiLCJqc190ZWFjaG1hbmFnZXJfY291cnNlX2FkZCIsImpzX3RlYWNobWFuYWdlcl9jb3Vyc2VfcGxhbiJdLCJqdGkiOiJmMDNkNTRhMS1lZjNiLTRjMjUtOTBhNC1kMWQ3YzAxYjY5N2UiLCJjbGllbnRfaWQiOiJqdW5zaG91IiwidXNlcm5hbWUiOiIxMTEifQ.p_yrWg3HAzaEN-DoULPZt4byZlrijJorA1TShVq-4chi6Ydi3Fo4-8F1aieGPMUqXzIIwf62v1-sWXbx_MATUNl_kD4z6qNGzh6zWNvGAe02V2JM95U_xI9uA_hXfl9yEyheEyVQeQdw02qvsQH1P0YxgmEM_CjgKcnqihrVbOzBQ0yl3c8HF-Zh0Z1ASneWQ2f6e3rAO4hvmwOuLSkERD2t2YfTF8SCaMJ90aUFco2XBYb3v1PrBX-8Q8MDcnYFlYodAE1ogDUk4t8Sqt4s8OgPVzaF4fQIuJXbyIvMSlTU8hIJLRyb_YZw_B83IqSAxDPMms3YSrtrEzVgAHZuqQ";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
