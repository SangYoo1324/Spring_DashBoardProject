package com.example.FirstProject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Pointcut("execution(* com.example.FirstProject..*.*(..))")  // .. FirstProject 하위, *.* 모든 클래스의 모든 메서드
    // ..은 메소드의 파라메터 개수는 상관 없다
    private void cut(){

    }
}
