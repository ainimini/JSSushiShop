package com.junshou.oauth.controller;

import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.common.util.CookieUtil;
import com.junshou.oauth.service.AuthService;
import com.junshou.oauth.util.AuthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
@Api(value = "oauth接口", description = "oauth接口，提供页面的增、删、改、查")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${security.oauth2.client.cookieDomain}")
    private String cookieDomain;

    @Value("${security.oauth2.client.cookieMaxAge}")
    private int cookieMaxAge;

    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM", required = false, defaultValue = "") String from, Model model) {
        model.addAttribute("from", from);
        return "login";
    }

    /***
     * 登录
     * @param username
     * @param password
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation("登录")
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
        if (authToken != null) {
            //将jti的值存入cookie中
            this.saveJtiToCookie(authToken.getJti(), response);

            //返回结果
            return new Result(true, StatusCode.OK, "登录成功", authToken);
        }
        return new Result(false, StatusCode.LOGINERROR, "登录失败");
    }

    /***
     * 验证登录用户的信息
     * @return
     */
    @GetMapping("/userJwt")
    @ResponseBody
    @ApiOperation("验证登录用户的信息")
    public Result userJwt() {
        //取出cookie中的用户身份令牌
        String jti = getTokenFormCookie();
        if (jti != null) {
            //拿身份令牌从redis中查询jwt令牌
            AuthToken userToken = authService.getUserToken(jti);
            if (userToken != null) {
                //将jwt令牌返回给用户
                String accessToken = userToken.getAccessToken();
                return new Result(true, StatusCode.OK, "成功获取用户信息", accessToken);
            }
        }
        return new Result(true, StatusCode.ERROR, "cookie中没有jti短令牌");
    }

    /***
     * 退出
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    @ApiOperation("退出")
    public Result logout() {
        //取出cookie中的用户身份令牌
        String jti = getTokenFormCookie();
        //删除redis中的token
        boolean result = authService.delToken(jti);
        //清除cookie
        this.clearCookie(jti);
        return new Result(true, StatusCode.OK, "成功删除cookie信息");
    }

    /***
     * 取出cookie中的身份令牌
     * @return
     */
    private String getTokenFormCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if (map != null && map.get("uid") != null) {
            String jti = map.get("uid");
            return jti;
        }
        return null;
    }

    /***
     * 从cookie删除token
     * @param token
     */
    private void clearCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);

    }

    /***
     * 将令牌的断标识jti存入到cookie中
     * @param jti
     * @param response
     */
    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
    }
}
