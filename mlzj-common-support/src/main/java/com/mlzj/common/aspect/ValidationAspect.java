package com.mlzj.common.aspect;

import com.mlzj.common.annotation.IsNeedCheck;
import com.mlzj.common.utils.CharacterUtils;
import com.mlzj.common.validate.Validation;
import com.mlzj.common.validate.ValidationFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 检验器
 *
 * @author yhl
 * @date 2020/6/3
 */
@Component
@Aspect
public class ValidationAspect {
    @Resource
    private ValidationFactory validationFactory;

    private static final String GET_METHOD_PRE = "get";

    /**
     * 检验用户是否已登录
     *
     * @param point 切点
     * @throws IllegalAccessException 异常
     */
    @Before("@annotation(com.mlzj.common.annotation.Validation)")
    public void checkAccess(JoinPoint point) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        List<Annotation[]> annotations = new ArrayList<>(Arrays.asList(parameterAnnotations));
        Object[] args = point.getArgs();
        for (int index = 0; index < annotations.size() ; index ++) {
            for (Annotation paramAnnotation : annotations.get(index)) {
                if (paramAnnotation instanceof IsNeedCheck){
                    this.check(args[index]);
                }
            }
        }
    }

    /**
     * 进行检查
     * @param arg 参数
     * @throws NoSuchMethodException 没有找到方法
     * @throws InvocationTargetException 非法调用
     * @throws IllegalAccessException 非法访问异常
     */
    private void check(Object arg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = arg.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            IsNeedCheck fieldAnnotation = field.getAnnotation(IsNeedCheck.class);
            if (fieldAnnotation == null){
                continue;
            }
            Method method = arg.getClass().getMethod(GET_METHOD_PRE + CharacterUtils.toUpperCaseFist(field.getName()));
            if (fieldAnnotation.isSimpleCheck()){
                String type = fieldAnnotation.type();
                Validation instance = validationFactory.getInstance(type);
                Object invoke = method.invoke(arg);
                boolean validate = instance.validate(invoke);
            } else {
                this.check(method.invoke(arg));
            }
        }
    }
}
