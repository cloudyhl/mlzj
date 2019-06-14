package com.mlzj.cloud.zuul.filter;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 测试filter
 * @author yhl
 * @date 2019/6/8
 */
@Component
public class DemoFilterThree extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return (Boolean) RequestContext.getCurrentContext().get("logic-is-success");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取responseBody 需要使用 requestContext.getResponseBody()requestContext.getResponseDataStream()两者中不为空的那一项
        String responseBody = requestContext.getResponseBody();
        InputStream responseDataStream = requestContext.getResponseDataStream();
        try {
            String s = CharStreams.toString(new InputStreamReader(responseDataStream, StandardCharsets.UTF_8));
            requestContext.setResponseBody(s);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(responseBody);
        return null;
    }
}
