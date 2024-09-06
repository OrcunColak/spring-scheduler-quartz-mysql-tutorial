package com.colak.springtutorial.controller;

import com.colak.springtutorial.job.MyJobExecutor;
import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@RestController
@RequestMapping(path = "/job")
@RequiredArgsConstructor
public class JobController {

    // This is a quartz Scheduler
    private final Scheduler scheduler;

    // http://localhost:8080/job/start
    @GetMapping(path = "/start")
    String startJob() throws SchedulerException {
        JobDetail job = createJob();

        Trigger trigger = createTrigger();

        Date date = scheduler.scheduleJob(job, trigger);
        return "Scheduled Job at " + date;
    }

    private static Trigger createTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("triggerIdentity-1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(60)
                        // How many times we want the job to run
                        .withRepeatCount(10))
                .build();
    }

    private static JobDetail createJob() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobID", "Job-1");

        return JobBuilder.newJob(MyJobExecutor.class)
                .withIdentity("jobIdentity-1")
                .usingJobData(jobDataMap)
                .build();
    }
}
