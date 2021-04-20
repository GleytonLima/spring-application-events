package com.maolabs.etapas.spring.config;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CustomPerformanceMonitorInterceptor extends AbstractMonitoringInterceptor {

    public CustomPerformanceMonitorInterceptor() {
    }

    public CustomPerformanceMonitorInterceptor(boolean useDynamicLogger) {
        setUseDynamicLogger(useDynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log log) throws Throwable {
        final Object[] arguments = invocation.getArguments();
        String name = createInvocationTraceName(invocation);
        String startDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long totalMillis = end - start;
            if (totalMillis > 5000) {
                String endDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
                String message = String
                        .format("performancegantt performance_id[%s]performance_id  performance_start_date[%s]performance_start_date  performance_end_date[%s]performance_end_date  performance_total_millis[%s]performance_total_millis arguments[%s]arguments",
                                name,
                                startDate, endDate,
                                totalMillis,
                                Arrays.toString(arguments));
                log.info(message);
                log.debug(name + " demorou mais que 2000ms: " + totalMillis);
            }
        }
    }
}
