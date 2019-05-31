package com.mlzj.common.utils;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {


    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * @param applicationEvent 事件
     */
    public void publisherEvent(ApplicationEvent applicationEvent){
        this.applicationEventPublisher.publishEvent(applicationEvent);
    }

    /**
     * 异步发送事件
     * @param applicationEvent 事件体
     */
    public void asyncPublishEvent(ApplicationEvent applicationEvent){
        this.publisherEvent(applicationEvent);
    }
}
