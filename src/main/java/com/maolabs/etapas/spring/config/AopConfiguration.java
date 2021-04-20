package com.maolabs.etapas.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

    @Pointcut("within(com.maolabs.etapas..*)")
    public void classesNoPacote() {
    }

    @Before("classesNoPacote()")
    public void logMethod(JoinPoint jp) {
        String nomeMetodo = jp.getSignature().getName();
        String nomeClasse = jp.getTarget().getClass().getSimpleName();
        String atributos = Arrays.stream(jp.getArgs()).map(o -> o.getClass().getSimpleName()).collect(Collectors.joining(","));
        logger.info("{}", String.format("Executando: %s#%s#%s", nomeClasse, nomeMetodo, atributos));
    }

    @Bean
    public CustomPerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new CustomPerformanceMonitorInterceptor(false);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.maolabs.etapas.spring.config.AopConfiguration.classesNoPacote()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}
