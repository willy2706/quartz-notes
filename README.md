Simple notes about this tutorial:
-  Generates IDEA project files
    ```bash
    gradle idea
    ```
-  Run the program
    ```bash
    gradle run
    ```
- Quartz configuration is at `src/main/resources/quartz.properties`

You may explore:
1. Change quartz datastore from memory to postgres
2. Create a one time task
3. Create a repetitive task (e.g. job run every 5 minutes/specific cron expression)
4. How to delete a job

Output:
1. Understand `trigger` vs `job`
2. Understand type of `trigger`
3. Understand `jobdetail`
4. Understand jobkey and triggerkey
5. Understand quartz's database schema
6. Understand annotations @DisallowConcurrentExecution and @PersistJobDataAfterExecution
7. Understand how to pass data to quartz job