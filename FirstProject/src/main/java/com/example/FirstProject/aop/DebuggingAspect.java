package com.example.FirstProject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect// aop class 선언: 부가기능을 주입하는 클래스
@Component // IOC컨테이너가 해당 객체를 생성 및 관리(new 해서 생성할 필요 없음)
@Slf4j
public class DebuggingAspect {
    @Pointcut("execution(* com.example.FirstProject.api.*.*(..))")  // 어따 찔러넣을지 지정
    private void cut() {
    }
    @Before("cut()")  //실행 시점 설정
    public void loggingArgs(JoinPoint joinPoint){ //cut()의 대상 메소드

        //입력값 가져오기
        Object [] args = joinPoint.getArgs();
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName= joinPoint.getSignature()
                .getName();
        //입력값 로깅하기
        //CommentService#create()의 입력값 =>
        for(Object obj : args){
            log.info("{}#{} input = > {}",className, methodName, obj);
        }
    }
@AfterReturning(value= "cut()", returning = "returnObj") //cut 에 지정된 대상(여기선 create() 메서드) 호출 성공 후
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj){

        //클래스명
    String className= joinPoint.getTarget()
            .getClass()
                    .getSimpleName();
    //메소드명
        String methodName= joinPoint.getSignature().getName();
        //반환값을 로깅
    log.info("msg: {}#{} 's return value=> {}", className, methodName, returnObj);
    }
}



