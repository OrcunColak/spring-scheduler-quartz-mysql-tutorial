package com.colak.springtutorial.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyJobExecutor implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

        String jobId = (String) jobDataMap.get("jobID");
        log.info("Job Started-{} at:{}", jobId, new Date());
    }
}
