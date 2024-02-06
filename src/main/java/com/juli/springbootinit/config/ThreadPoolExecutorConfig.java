package com.juli.springbootinit.config;

import org.elasticsearch.threadpool.ThreadPool;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolExecutorConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadFactory threadFactory = new ThreadFactory(){
            private int count = 1;
            @Override
            //@NotNull：参数r永远不为null
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程" + count);
                count++;
                return thread;
            }
        };
        //创建一个新的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,100,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),threadFactory);
        return threadPoolExecutor;
    }

}
