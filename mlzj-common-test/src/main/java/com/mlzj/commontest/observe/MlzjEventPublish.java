package com.mlzj.commontest.observe;

import com.mlzj.commontest.utils.ClassTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yhl
 * @date 2019/9/17
 */
@Slf4j
public class MlzjEventPublish implements Subject {

    private Map<String, MlzjEventListener> listenerMap = new HashMap<>(16);

    private String packagePattern;

    private Set<String> instanceSet = new HashSet<>();

    private Set<Class<?>> classSet = new HashSet<>();

    public MlzjEventPublish(String packagePattern) {
        this.packagePattern = packagePattern;
    }

    @Override
    public void addListener(MlzjEvent mlzjEvent) {
        String name = mlzjEvent.getClass().getName();
        if (listenerMap.keySet().contains(name)) {
            return;
        }
        if (CollectionUtils.isEmpty(classSet)){
            classSet = ClassTools.getClasses(packagePattern);
        }
        for (Class clazz : classSet) {
            if (MlzjEventListener.class.isAssignableFrom(clazz) && clazz != MlzjEventListener.class && !instanceSet.contains(clazz.getName())) {
                try {
                    Type[] genericInterfaces = clazz.getGenericInterfaces();
                    for (Type type : genericInterfaces) {
                        if (type instanceof ParameterizedType ){
                            ParameterizedType type1 = (ParameterizedType) type;
                            Type[] actualTypeArguments = type1.getActualTypeArguments();
                            if (StringUtils.equals(actualTypeArguments[0].getTypeName(), name)) {
                                instanceSet.add(clazz.getName());
                                listenerMap.put(name, (MlzjEventListener) clazz.newInstance());
                            }
                        }

                    }

                } catch (Exception e) {
                    log.info("发生异常:", e);
                }
            }
        }
    }

    @Override
    public void publishEvent(MlzjEvent mlzjEvent) {
        String name = mlzjEvent.getClass().getName();
        this.listenerMap.get(name).onMlzjEvent(mlzjEvent);

    }
}
