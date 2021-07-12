package com.liubing.demoaop.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Order(value = 999)
@Component
public class AspectConfig {

    /**
     * @Pointcut ：切入点声明，即切入到哪些目标方法。value 属性指定切入点表达式，默认为 ""。
     * 用于被下面的通知注解引用，这样通知注解只需要关联此切入点声明即可，无需再重复写切入点表达式
     * <p>
     * 切入点表达式常用格式举例如下：
     * execution(* com.wmx.aspect.EmpServiceImpl.findEmpById(Integer))	匹配 com.wmx.aspect.EmpService 类中的 findEmpById 方法，且带有一个 Integer 类型参数。
     * execution(* com.wmx.aspect.EmpServiceImpl.findEmpById(*))	匹配 com.wmx.aspect.EmpService 类中的 findEmpById 方法，且带有一个任意类型参数。
     * execution(* com.wmx.aspect.EmpServiceImpl.findEmpById(..))	匹配 com.wmx.aspect.EmpService 类中的 findEmpById 方法，参数不限。
     * execution(* grp.basic3.se.service.SEBasAgencyService3.editAgencyInfo(..)) || execution(* grp.basic3.se.service.SEBasAgencyService3.adjustAgencyInfo(..))	匹配 editAgencyInfo 方法或者 adjustAgencyInfo 方法
     * execution(* com.wmx.aspect.EmpService.*(..))	匹配 com.wmx.aspect.EmpService 类中的任意方法
     * execution(* com.wmx.aspect.*.*(..))	匹配 com.wmx.aspect 包(不含子包)下任意类中的任意方法
     * execution(* com.wmx.aspect..*.*(..))	匹配 com.wmx.aspect 包及其子包下任意类中的任意方法
     * </p>
     * @Aspect:作用是把当前类标识为一个切面供容器读取
     * @Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     * @Around：环绕增强，相当于MethodInterceptor
     * @AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
     * @Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
     * @AfterThrowing：异常抛出增强，相当于ThrowsAdvice
     * @After: final增强，不管是抛出异常或者正常退出都会执行
     */
    @Pointcut(value = "execution(* com.liubing.demoaop.controller.auth..*.*(..))")
    private void aspectPointcut() {
        System.out.println("【Pointcut】");
    }


    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容。
     * value：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * <br/>
     *
     * @param joinPoint：提供对连接点处可用状态和有关它的静态信息的反射访问<br/> <p>
     *                                                 Object[] getArgs()：返回此连接点处（目标方法）的参数
     *                                                 Signature getSignature()：返回连接点处的签名。
     *                                                 Object getTarget()：返回目标对象
     *                                                 Object getThis()：返回当前正在执行的对象
     *                                                 StaticPart getStaticPart()：返回一个封装此连接点的静态部分的对象。
     *                                                 SourceLocation getSourceLocation()：返回与连接点对应的源位置
     *                                                 String toLongString()：返回连接点的扩展字符串表示形式。
     *                                                 String toShortString()：返回连接点的缩写字符串表示形式。
     *                                                 String getKind()：返回表示连接点类型的字符串
     *                                                 </p>
     */
    @Before(value = "aspectPointcut()")
    public void aspectBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();

        System.out.println("【前置通知】 Before");
        System.out.println("\targs=" + Arrays.asList(args));
        System.out.println("\tsignature=" + signature);
        System.out.println("\ttarget=" + target);
        System.out.println("\taThis=" + aThis);
        System.out.println("\tstaticPart=" + staticPart);
        System.out.println("\tsourceLocation=" + sourceLocation);
        System.out.println("\tlongString=" + longString);
        System.out.println("\tshortString=" + shortString);
    }

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管目标方法是否发生异常。
     * value：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     */
    @After(value = "aspectPointcut()")
    public void aspectAfter(JoinPoint joinPoint) {
        System.out.println("【后置通知】");
        System.out.println("\tkind=" + joinPoint.getKind());
    }

    /**
     * 返回通知：目标方法返回后执行以下代码
     * value 属性：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * pointcut 属性：绑定通知的切入点表达式，优先级高于 value，默认为 ""
     * returning 属性：通知签名中要将返回值绑定到的参数的名称，默认为 ""
     *
     * @param joinPoint ：提供对连接点处可用状态和有关它的静态信息的反射访问
     * @param result    ：目标方法返回的值，参数名称与 returning 属性值一致。无返回值时，这里 result 会为 null.
     */
    @AfterReturning(pointcut = "aspectPointcut()", returning = "result")
    public void aspectAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("【返回通知】");
        System.out.println("\t目标方法返回值=" + result);
    }

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码，此时返回通知不会再触发
     * 可用于记录操作日志
     * value 属性：绑定通知的切入点表达式。可以关联切入点声明，也可以直接设置切入点表达式
     * pointcut 属性：绑定通知的切入点表达式，优先级高于 value，默认为 ""
     * throwing 属性：与方法中的异常参数名称一致，
     *
     * @param ex：捕获的异常对象，名称与 throwing 属性值一致
     */
    @AfterThrowing(pointcut = "aspectPointcut()", throwing = "ex")
    public void aspectAfterThrowing(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        System.out.println("【异常通知】");
        if (ex instanceof ArithmeticException) {
            System.out.println("\t【" + methodName + "】方法算术异常（ArithmeticException）：" + ex.getMessage());
        } else {
            System.out.println("\t【" + methodName + "】方法异常：" + ex.getMessage());
        }


//        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        //HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//
//        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
//        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
//
//        try {
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取请求的方法名
//            String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getMethod().getName();
//
//            System.out.println("=========== AfterThrowing  begin =========== ");
//            System.out.println("methodName =========== " + methodName);
//            System.out.println("Exception =========== " + e.toString());
//            System.out.println("getRequestURI =========== " + request.getRequestURI());
//            System.out.println("getParameterMap =========== " + new JSONObject(request.getParameterMap()).toString());
//            System.out.println("=========== AfterThrowing  end =========== ");
//
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
    }


}
