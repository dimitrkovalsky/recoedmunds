package com.epam.callable;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Local
public class CallableExecutorService<E> {

    private int maxThreads = 500;
    static ExecutorService executorService;
    
    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }
    
    public int getMaxThreads() {
        return maxThreads;
    }
    
    @PostConstruct
    private void init() {
        executorService = Executors.newFixedThreadPool(maxThreads);
    }
    
    @PreDestroy
    private void shutdown() {
        executorService.shutdown();
    }
    
    public List<Future<E>> executeJobList(List<Callable<E>> jobList) throws Exception {
        
        
        List<Future<E>> returnTasks = Lists.newArrayList();
        
        try {
            for (Callable<E> job : jobList) {
                returnTasks.add(executorService.submit(job));
            }
            return returnTasks;
        } catch (Exception e) {
            throw e;
        }
    }
}
