# Read Me

The original idea is from  
https://medium.com/@tecnicorabi/part-2-persisting-quartz-jobs-in-spring-boot-with-sql-and-nosql-databases-7849ead2f610

# Trigger Example

Hourly example

```
Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("security-audit-trigger")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(intervalInHours))
                    .build();
```

Repeat example

```
Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerIdentity-1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(60)
                        // How many times we want the job to run
                        .withRepeatCount(10))
                .build();
```