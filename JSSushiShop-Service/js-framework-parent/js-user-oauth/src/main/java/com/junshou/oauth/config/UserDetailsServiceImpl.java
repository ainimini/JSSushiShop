package com.junshou.oauth.config;

import com.junshou.common.entity.Result;
import com.junshou.oauth.util.UserJwt;
import com.junshou.user.feign.UserFeign;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/*****
 * 自定义授权认证类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    private UserFeign userFeign;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //*************************客户端信息验证 start*************************
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //秘钥
                String clientSecret = clientDetails.getClientSecret();
                //静态方式
                //return new User(
                // username,   //客户端ID
                // new BCryptPasswordEncoder().encode(clientSecret),   //客户端秘钥->加密操作
                // AuthorityUtils.commaSeparatedStringToAuthorityList(""));   //权限
                //数据库查找方式
                return new User(
                        //客户端ID
                        username,
                        //客户端秘钥->加密操作
                        clientSecret,
                        //权限
                        AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        //*************************客户端信息验证 end*************************

        //*************************用户账号密码信息认证 start*************************
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        //根据用户名查询用户信息
        //String pwd = new BCryptPasswordEncoder().encode("itheima");
        Result<com.junshou.user.pojo.User> userResult = userFeign.findUserInfo(username);
        if (userResult == null || userResult.getData() == null) {
            return null;
        }
        String psw = userResult.getData().getPassword();
        //创建User对象
        String permissions = "salesman,accountant,user,admin,vip"; //指定用户角色信息
        UserJwt userDetails = new UserJwt(username, psw, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        //*************************用户账号密码信息认证 end*************************
        return userDetails;
    }
}
