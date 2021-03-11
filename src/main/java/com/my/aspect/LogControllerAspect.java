package com.my.aspect;

import com.my.enums.ResponseEnum;
import com.my.response.CommonResponse;
import com.my.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Controller加上统一日志处理
 * @author chenming
 * @date 2021/1/26 11:23
 */
@Slf4j
@Aspect
@Component
public class LogControllerAspect {

    @Pointcut("execution(public * com.my.controller.*.*.*(..))")
    public void point(){
        log.info("---------------  LogControllerAspect execution 切入点 [ public * com.fofund.research.controller.*.*(..)) ]  --------------");
    }

    @Around(value = "point()")
    public Object around(ProceedingJoinPoint pjp){
        String uuid = UUID.randomUUID().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date start = new Date();

        try{
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();

            StringBuffer sb = new StringBuffer("\r\n");
            sb.append("------------------------------------------------");
            sb.append("\r\n");
            sb.append("uuid : ");
            sb.append(uuid);
            sb.append("\r\n");
            sb.append("startTime : ");

            sb.append(sdf.format(start));
            sb.append(" [ ");
            sb.append(start.getTime());
            sb.append(" ] ");


            sb.append("\r\n");
            sb.append("requestURL : ");
            sb.append(request.getRequestURL());
            sb.append("\r\n");
            sb.append("className : ");
            sb.append(pjp.getTarget().getClass().getName());
            sb.append("\r\n");
            sb.append("methodName : ");
            sb.append(pjp.getSignature().getName());
            sb.append("\r\n");
            sb.append("methodType : ");
            sb.append(request.getMethod());
            sb.append("\r\n");
            sb.append("methodArgs : [ ");

            Object[] args = pjp.getArgs();
            if(args != null && args.length > 0){
                int size = args.length;
                for(int i = 0 ; i < size ; i++){
                    sb.append(args[i]);
                    if(i < (size - 1)){
                        sb.append(" , ");
                    }
                }
            }

            sb.append(" ] ");
            sb.append("\r\n");
            sb.append("------------------------------------------------");
            log.info(sb.toString());

            Object rst = pjp.proceed();

            Date end = new Date();

            if(rst != null && rst instanceof CommonResponse){
                ((CommonResponse)rst).appendExtend("耗时 : " + (end.getTime() - start.getTime()) + " ms");
            }

            StringBuffer sb2 = new StringBuffer("\r\n");
            sb2.append("------------------------------------------------");
            sb2.append("\r\n");
            sb2.append("uuid : ");
            sb2.append(uuid);
            sb2.append("\r\n");
            sb2.append("endTime : ");
            sb2.append(sdf.format(end));
            sb2.append(" [ ");
            sb2.append(end.getTime());
            sb2.append(" ] ");
            sb2.append("\r\n");
            sb2.append("耗时 : ");
            sb2.append((end.getTime() - start.getTime()));
            sb2.append(" ms");
            sb2.append("\r\n");
            sb2.append("rst : success");
            sb2.append("\r\n");
            sb2.append("------------------------------------------------");

            log.info(sb2.toString());

            return rst;
        }catch(Throwable ex){
            Date exEnd = new Date();

            StringBuffer sb3 = new StringBuffer("\r\n");
            sb3.append("------------------------------------------------");
            sb3.append("\r\n");
            sb3.append("uuid : ");
            sb3.append(uuid);
            sb3.append("\r\n");
            sb3.append("endTime : ");
            sb3.append(sdf.format(exEnd));
            sb3.append(" [ ");
            sb3.append(exEnd.getTime());
            sb3.append(" ] ");
            sb3.append("\r\n");
            sb3.append("耗时 : ");
            sb3.append((exEnd.getTime() - start.getTime()));
            sb3.append(" ms");
            sb3.append("\r\n");
            sb3.append("rst : fail");
            sb3.append("\r\n");
            sb3.append("exception - message : [ ");
            sb3.append(ex.getMessage());
            sb3.append(" ] ");
            sb3.append("\r\n");
            sb3.append("------------------------------------------------");

            log.info(sb3.toString());

            ex.printStackTrace();

            return ResponseUtil.custom(ResponseEnum.UNKNOWN_INTERNAL_ERROR);
        }
    }

}
