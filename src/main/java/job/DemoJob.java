package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Map;

/**
 * Created by yinshangwei on 30/03/2017.
 */
public class DemoJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //get param  from  job
        Map data = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("hi," + data.get("name") + "," + new Date().getTime());
    }
}
