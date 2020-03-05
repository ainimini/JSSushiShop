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
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MzQzOTE4NiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iLCJzYWxlc21hbiIsImFjY291bnRhbnQiLCJ2aXAiLCJ1c2VyIl0sImp0aSI6Ijg2Y2VmOTQ3LWVlMjItNDFhMy05Njk5LTE3ZGUyMzg1NWViYiIsImNsaWVudF9pZCI6Imp1bnNob3UiLCJ1c2VybmFtZSI6IjExMSJ9.GRazqJVUP5mjx0GwCp6nhFoUbTHb7sqsbpJ5AD45zZgkVvkmx0XN7wkXIb8mbAloCT4QXWS1HlvLeRpo7Vflo-Ddlb9bsoLP_hbjE10Gp-Y9SQHxdxfqN_bX70LcMJlChCTp_Zvtpuytpg9Erdgs8lK5mJxHow2A7jfSvrzgMzwYqAquP-NPu9LimcnFOhzstM_VIc68Fpu3wePLqJaM1g7UB95rJzJIJZZwKeFWduIs5mCECbkWF5lT-PuHqGfSxNllYKuu9gtmLsFTWHDfoIZBuhylJ0P2HaW-bwQvEwyrgiqC99KyOkc5toRXO5dQ-SOZ4q77Ivx1n1rpWDTEVA";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
