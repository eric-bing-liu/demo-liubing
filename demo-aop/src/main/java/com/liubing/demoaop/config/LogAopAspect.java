package com.liubing.demoaop.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 切面处理类，操作日志异常日志记录处理
 */
@Order(5)   //应对多个Apsect时用
@Aspect
@Component
public class LogAopAspect {


    public LogAopAspect() {
        System.out.println("LogAopAspect.init...");
    }

//    /**
//     * 切点
//     */
//    @Pointcut("@annotation(com.liubing.demoaop.config.LogAop)")
//    public void methodCachePointcut() {
//        System.out.println("  === public void methodCachePointcut() ===");
//    }


    /**
     * @PointCut 表达式详解
     * <p>
     * 1 execution
     * ===========================================
     * 方法类型包含Public，Protected等，可省略
     * 方法返回值类型，*可以包含所有的返回值类型
     * 包路径，如“com.demo…*”,表示"com.demo"包以及该包之下子包的所有类型
     * 方法名称，如“add*”,表示所有以add开头的方法，参数：(*)表示任意一个参数，(…)表示所有参数
     * 异常类型，如execution(* *(…) throws Exception)”匹配所有抛出Exception的方法。
     * <p>
     * 1）execution(public * *(..))——表示匹配所有public方法   public可省略
     * 2）execution(* set*(..))——表示所有以“set”开头的方法
     * 3）execution(* com.xyz.service.AccountService.*(..))——表示匹配所有AccountService接口的方法
     * 4）execution(* com.xyz.service.*.*(..))——表示匹配service包下所有的方法
     * 5）execution(* com.xyz.service..*.*(..))——表示匹配service包和它的子包下的方法
     * ===========================================
     * <p>
     * 2 within  匹配使用指定注解的类
     * @within: 匹配使用指定注解的类
     * ===========================================
     * 异常类型，如execution(* *(…) throws Exception)”匹配所有抛出Exception的方法。
     * ===========================================
     * <p>
     * 3 this  SpringAOP是基于代理的，this就代表代理对象，语法是this(type)，当生成的代理对象可以转化为type指定的类型时表示匹配。
     * ===========================================
     * this(com.demo.service.IUserService)匹配生成的代理对象是IUserService类型的所有方法的外部调用
     * ===========================================
     * <p>
     * 4 target  SpringAOP是基于代理的，target表示被代理的目标对象，当被代理的目标对象可以转换为指定的类型时则表示匹配。
     * @target： 带有指定注解的类型
     * ===========================================
     * target(com.demo.service.IUserService) 匹配所有被代理的目标对象能够转化成IuserService类型的所有方法的外部调用。
     * ===========================================
     * <p>
     * 5 args  args用来匹配方法参数
     * @args 指定运行时传的参数带有指定的注解
     * ===========================================
     * args() 匹配不带参数的方法
     * args(java.lang.String) 匹配方法参数是String类型的
     * args(…) 带任意参数的方法
     * args(java.lang.String,…) 匹配第一个参数是String类型的，其他参数任意。最后一个参数是String的同理。
     * ===========================================
     * <p>
     * 6 @annotation:指定方法所应用的注解
     * ===========================================
     * ===========================================
     * <p>
     * 7 bean 指定特定的bean名称，可以使用通配符（Spring自带的）
     */


    //PointCut表达式
    @Pointcut("execution(public * com.liubing.demoaop.controller.auth.UserController.*(..))")
    public void userLog() {
    }

    @Before("userLog()")
    public void doBefore() {
        System.out.println("doBefore");
    }

    //一旦引用了这个，就不走controller中定义的方法
//    @Around("userLog()")
//    public void doAround(){
//        System.out.println("doAround");
//    }
    @After("userLog()")
    public void doAfter() {
        System.out.println("doAfter");
    }

    //异常抛出增强，在异常抛出后织入的增强。有点像上面的@AfterReturning，这个注解也是有两个属性，pointcut和throwing。
    @AfterThrowing("userLog()")
    public void doAfterThrowing() {
        System.out.println("doAfterThrowing");
    }


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.liubing.demoaop.config.LogAop)")
    public void operLogPoinCut() {
        System.out.println("operLogPoinCut....");
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint       切入点
     * @param returningObject 返回结果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "returningObject")
    public void saveOperLog(JoinPoint joinPoint, Object returningObject) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {


            System.out.println("=========== AfterReturning  begin =========== ");

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            LogAop logAop = method.getAnnotation(LogAop.class);
            if (logAop != null) {
                System.out.println("logAop.param1() =========== " + logAop.param1());
                System.out.println("logAop.param2() =========== " + logAop.param2());
            }

            // 获取请求的类名 + . + 请求的方法名
            String methodName = joinPoint.getTarget().getClass().getName() + "." + method.getName();
            System.out.println("methodName =========== " + methodName);
            System.out.println("returningObject =========== " + returningObject);
            System.out.println("getRequestURI =========== " + request.getRequestURI());
            System.out.println("getParameterMap =========== " + new JSONObject(request.getParameterMap()).toString());
            System.out.println("=========== AfterReturning  end =========== ");

            // 请求的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
//            operlog.setOperUserId(UserShiroUtil.getCurrentUserLoginName()); // 请求用户ID
//            operlog.setOperUserName(UserShiroUtil.getCurrentUserName()); // 请求用户名称
//            operlog.setOperIp(IPUtil.getRemortIP(request)); // 请求IP
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
