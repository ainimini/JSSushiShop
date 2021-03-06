package com.junshou.gateway.web.filter;

/**
 * @author X
 * @updateTime: 2020/2/10 17:43
 */
public class UrlFilter {

    /***
     * 需要传递令牌的URL
     */
   /* public static String filterPath = "/api/worder/**,/api/wseckillorder,/api/seckill,/api/wxpay,/api/wxpay/**,/api/user/**,/api/address/**,/api/wcart/**,/api/cart/**,/api/categoryReport/**,/api/orderConfig/**,/api/order/**,/api/orderItem/**,/api/orderLog/**,/api/preferential/**,/api/returnCause/**,/api/returnOrder/**,/api/returnOrderItem/**";*/
    public static String filterPath = "/api/worder/**,/api/wseckillorder,/api/seckillOrder/**,/api/wxpay/**,/api/user/**,/api/address/**,/api/wcart/**,/api/cart/**,/api/categoryReport/**,/api/orderConfig/**,/api/order/**,/api/orderItem/**,/api/orderLog/**,/api/preferential/**,/api/returnCause/**,/api/returnOrder/**,/api/returnOrderItem/**,/api/role/**";

    /**
     * 校验访问路径是否需要权限
     * 需要校验的返回 true
     * 不需要校验的返回 false
     *
     * @param url
     * @author: X
     * @updateTime: 2020/2/10 17:44
     */
    public static boolean hasAuthorize(String url) {
        //不需要拦截的URL
        String[] split = filterPath.replace("**", "").split(",");

        for (String value : split) {

            if (url.startsWith(value)) {
                return true; //代表当前的访问地址是需要传递令牌的
            }
        }

        return false; //代表当前的访问地址是不需要传递令牌的
    }
}
