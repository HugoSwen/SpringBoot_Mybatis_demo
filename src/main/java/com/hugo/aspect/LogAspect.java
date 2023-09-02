package com.hugo.aspect;


import com.alibaba.fastjson.JSONObject;
import com.hugo.mapper.OperateLogMapper;
import com.hugo.pojo.OperateLog;
import com.hugo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.hugo.annotation.Log)")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在token中获取操作人id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 获取目标对象信息
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 开始时间
        long begin = System.currentTimeMillis();

        // 调用原始方法运行
        Object result = joinPoint.proceed();

        // 结束时间
        long end = System.currentTimeMillis();

        // 操作方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        Long costTime = end - begin;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        return result;
    }
}
