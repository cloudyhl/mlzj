package com.mlzj.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 第二个filter
 * @author yhl
 * @date 2019/6/8
 */
@Component
public class DemoFilterTwo extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //过滤器链使用RequestContext来共享整条链路
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //向下游服务传递数据 requestContext.addZuulRequestHeader
        requestContext.addZuulRequestHeader("prefix","zuul");
        Object string = request.getAttribute("string");
        System.out.println(string);
//        if (StringUtils.isBlank(name)){
//            //此时会静止向下路由
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseBody("{\"status\":500,\"message\":\"name is null\"}");
//            //标识下一个同类型的filter是否执行，自定义在下一个filter中  shouldFilter方法可以用该值来判断是否要执行
//            requestContext.set("logic-is-success",false);
//            return null;
//        }
        requestContext.set("logic-is-success",true);
        return null;
    }
}
