package ex.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

/*
 * Application Lifecycle Listener implementation class HelloListener
 *
 *ServletContextListener : 웹 애플리케이션의 LifeCycle로 동작(톰캣에 애플리케이션이 올라가고 내려올 때)
 *
 */
@WebListener		//  나는 리스너이다. 나는 Listener다 하고 알려줌. 
public class HelloListener implements ServletContextListener {

	/* field :: 메소드마다 공통으로 사용하는 것 --> field이고 아래 메소드들 (Destroyed:종료, Initialized:초기화)실행시 공통적으로 필요한 값이므로 */
	private Scheduler scheduler;
	
	/* constructor */
	public HelloListener() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();	// 생성자로 만듦. -- try-catch가 필요함.
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    // 웹 애플리케이션이 종료될  때 실행되는 메소드. (톰캣에서 제거)
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
    		if (scheduler != null) {
    			
    		}
    		System.out.println("***** 스케쥴러 SHUTDOWN *****");
    		scheduler.shutdown();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    // 웹 애플리케이션이 시작할 때 실행되는 메소드. (톰캣에 올라감)
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
    		// 1) HelloJob
    		JobDetail job = JobBuilder.newJob(HelloJob.class)
    				.withIdentity("job2", "group2")
    				.build();
    		// 2) Trigger
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger2", "group2")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * 1/1 * ? *"))	// cron expression (크론식) : cronMaker.com
    				.build();
    		// 3. scheduler에 job, trigger, 등록
    		scheduler.scheduleJob(job, trigger);
    		// 4. scheduler 실행 시
    		scheduler.start();
    		System.out.println("***** 스케쥴러 START *****");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
