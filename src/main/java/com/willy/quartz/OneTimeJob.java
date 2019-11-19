package com.willy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class OneTimeJob implements Job {
  private String propertyId;
  @Override
  public void execute(JobExecutionContext context) {
    System.out.println("Hi. I'm running only once. My propertyId = " + propertyId);
  }

  public void setPropertyId(String propertyId) {
    this.propertyId = propertyId;
  }
}
