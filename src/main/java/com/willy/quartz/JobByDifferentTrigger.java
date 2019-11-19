package com.willy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobByDifferentTrigger implements Job {
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("Hi. This is job by different trigger");
  }
}
