package com.junshou.oauth.controller;

import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.oauth.service.AuthService;
import com.junshou.oauth.util.AuthToken;
import com.junshou.oauth.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${server.servlet.session.cookie.domain}")
    private String cookieDomain;

    @Value("${server.servlet.session.cookie.maxAge}")
    private int cookieMaxAge;

    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM", required = false, defaultValue = "") String from, Model model) {
        model.addAttribute("from", from);
        return "login";
    }


    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) throws Exception {
        //校验参数
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("请输入用户名");
        }
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("请输入密码");
        }
        //申请令牌 authtoken
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);
        if (authToken != null){
            //将jti的值存入cookie中
            this.saveJtiToCookie(authToken.getJti(), response);

            //返回结果
            return new Result(true, StatusCode.OK, "登录成功", authToken.getJti());
        }
        return new Result(false, StatusCode.LOGINERROR, "登录失败");
    }

    //将令牌的断标识jti存入到cookie中
    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
    }
}
