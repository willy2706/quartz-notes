package com.willy.quartz;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMain {
  public static void main(String[] args) throws SchedulerException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    System.out.println("===================START SCHEDULER===================");
    scheduler.start();
    oneTimeTask(scheduler);
//    differentTriggerSimilarJob(scheduler);
    System.out.println("===================END SCHEDULER===================");
  }

  private static void oneTimeTask(Scheduler scheduler) throws SchedulerException {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("propertyId","10000001");

    JobDetail job = newJob(OneTimeJob.class)
        .withIdentity("oneTimeJobName", "oneTimeJobGroup") // name "myJob", group "group1"
        .setJobData(jobDataMap)
        .build();

    Trigger trigger = newTrigger()
        .withIdentity("oneTimeJobTriggerName", "oneTimeJobTriggerGroup")
        .startNow()
        .withSchedule(simpleSchedule()
            .withRepeatCount(0))
        .build();

    scheduler.scheduleJob(job, trigger);
  }

  private static void differentTriggerSimilarJob(Scheduler scheduler) throws SchedulerException {
    JobDetail job = newJob(JobByDifferentTrigger.class)
        .withIdentity("sharedJobName", "sharedJobGroup") // name "myJob", group "group1"
        .build();

    Trigger trigger = newTrigger()
        .withIdentity("sharedTriggerOneName", "sharedTriggerOneGroup")
        .startAt(futureDate(7, IntervalUnit.SECOND))
        .withSchedule(simpleSchedule()
            .withRepeatCount(0))
        .build();

    Trigger trigger2 = newTrigger()
        .withIdentity("sharedTriggerTwoName", "sharedTriggerTwoGroup")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(5)
            .withRepeatCount(1))
        .build();

    scheduler.scheduleJob(job, trigger);
    scheduler.scheduleJob(job, trigger2); //fail, exception
  }
}
