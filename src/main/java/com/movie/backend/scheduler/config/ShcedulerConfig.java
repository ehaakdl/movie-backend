package com.movie.backend.scheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.movie.backend.scheduler.SchedulerErrorHandler;

import lombok.RequiredArgsConstructor;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class ShcedulerConfig implements SchedulingConfigurer {
    @Value("${scheduler.thread-pool-size}")
    private int threadPoolSize;
    private final SchedulerErrorHandler schedulerErrorHandler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(threadPoolSize);
        threadPoolTaskScheduler.setThreadNamePrefix("scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.setErrorHandler(schedulerErrorHandler);
        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}
