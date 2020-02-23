package com.junshou.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(-1)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /***
     * 忽略安全拦截的URL
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/login", "/oauth/logout", "/oauth/toLogin", "/login.html", "/static/css/**", "/static/data/**", "/static/fonts/**", "/static/img/**", "/static/js/**");
    }


    /***
     * 创建授权管理认证对象
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /***
     * 采用BCryptPasswordEncoder对密码进行编码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /****
     *目的就是每次访问的时候除了带上自己的访问凭据以外，还需要带上每次csrf的票据。当然这个是会根据具体的会话进行变化的，也就是防止csrf攻击。
     *
     * 　　如果csrf放开配置方式可以为cookie
     *
     * 　　即：将.csrf().disable()换成.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
     *
     * 　　如果存在后端接口忽略的加入：.ignoringAntMatchers("/api/user/login")
     * @param http
     * @throws Exception
     */
    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()        //启用Http基本身份验证
                .and()
                .formLogin()       //启用表单身份验证
                .and()
                .authorizeRequests()    //限制基于Request请求访问
                .anyRequest()
                .authenticated();       //其他请求都需要经过验证

        //开启表单登录
        http.formLogin().loginPage("/oauth/toLogin")//设置访问登录页面的路径
                .loginProcessingUrl("/oauth/login");//设置执行登录操作的路径
    }*/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                /***
                 * 启用Http基本身份验证
                 */
                .httpBasic()
                .and()
                /***
                 * 除了“/”,”/home”(首页),”/login”(登录),”/logout”(注销),之外，其他路径都需要认证
                 */
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                /***
                 * 启用表单身份验证
                 * 指定“/oauth/toLogin”该路径为登录页面，当未认证的用户尝试访问任何受保护的资源时，都会跳转到“/oauth/toLogin”
                 */
                .formLogin()
                    .loginPage("/oauth/toLogin")
                    .loginProcessingUrl("/oauth/login")
                    .and()
                /***
                 * 默认指定“/logout”为注销页面
                 */
                .logout()
                    .permitAll();       //其他请求都需要经过验证
    }
}
