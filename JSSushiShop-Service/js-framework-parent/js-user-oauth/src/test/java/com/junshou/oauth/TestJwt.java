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
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4NDI1MjEzOSwiYXV0aG9yaXRpZXMiOlsidXNlciJdLCJqdGkiOiI4YjIxNGVhNy0zMDZhLTQ0MmYtYmVkNy04MjljNjZhMTA5MzIiLCJjbGllbnRfaWQiOiJqdW5zaG91IiwidXNlcm5hbWUiOiIyMjIifQ.SJWkMYun9IlaKljbcH-npSI2ze9OB5O6AGYWnC4TxEKjxn32WRA1FA4engVp9iOhNtrZc35jpcSgHJNlRfX-qVHLKHp_ZBHldgLOjYswK-en6gK6NUWWIkW28f1LUpFuUgc7fHMZLWTBLDhS7LEyvS5FdCkCxEEwAWs2UR728eMXe4fshfDcYg0o8KtVY5ems9DQoPW236Ov1HQzS2DZhh4RibW2KmPf4GXg6_Db_22tm0Knk4aSsMJnnvS6bnX4e5vcPBWVdltblLdO62jB2gGCSb5rdZlnztvrVjjgW-h2mUunp3EyaUDYq0BRDO0hgQIMQi9uDkwNZUdyJFNUKg";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
