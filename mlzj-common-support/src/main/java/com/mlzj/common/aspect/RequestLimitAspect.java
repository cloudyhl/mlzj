package com.mlzj.common.aspect;

import com.mlzj.common.annotation.RequestLimit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhl
 * @date 2023/5/15
 */
@Aspect
@Component
public class RequestLimitAspect {

    private final Integer threshold = 10;

    private final Map<String, LinkedList<Long>> visitCounts = new ConcurrentHashMap<>(16);


    @Before("@annotation(requestLimit)")
    public void requestLimit(JoinPoint joinPoint, RequestLimit requestLimit) throws Exception {
        // 获取方法名和 IP 地址
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        String ip = this.getIp();
        // 根据方法名和 IP 地址拼接 Key
        String key = methodName + ":" + ip;
        if (!visitCounts.containsKey(key)) {
            LinkedList<Long> timesList = new LinkedList<>();
            timesList.add(System.currentTimeMillis() / 1000);
            visitCounts.put(key, timesList);
        } else {
            LinkedList<Long> visitRecord = visitCounts.get(key);
            this.checkCanVisit(visitRecord);
        }
    }

    /**
     * 通过 RequestContextHolder 获取 HttpServletRequest
     */
    private String getIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getRemoteAddr();
    }

    private void checkCanVisit(LinkedList<Long> visitRecords) {
        cleanNotUse(visitRecords);
        if (visitRecords.size() >= threshold) {
            throw new RuntimeException("请求次数已达上限,请稍后重试！");
        }
        visitRecords.add(System.currentTimeMillis() / 1000);
    }

    /**
     * 清除没有用的记录
     * @param visitRecords 访问记录
     */
    private void cleanNotUse(LinkedList<Long> visitRecords) {
        Iterator<Long> iterator = visitRecords.iterator();
        while (iterator.hasNext()) {
            Long visitRecord = iterator.next();
            if ((System.currentTimeMillis() / 1000) - 60 > visitRecord) {
                iterator.remove();
            } else {
                return;
            }
        }
    }
}
