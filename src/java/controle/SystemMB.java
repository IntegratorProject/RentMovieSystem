
package controle;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import util.quartz.ServiceRmvReservaLocacao;

@ManagedBean(eager=true)
@ApplicationScoped
public class SystemMB {
    
    public SystemMB(){
        iniciarRotinaVarreduraLocacoes();
    }
    
    private void iniciarRotinaVarreduraLocacoes(){
        
        SchedulerFactory shedFact = new StdSchedulerFactory();
        try{
            
            Scheduler scheduler = shedFact.getScheduler();
            scheduler.start();
            
            JobDetail job = JobBuilder.newJob(ServiceRmvReservaLocacao.class)
                    .withIdentity("varreduraLocacaoJob", "group01").build();
            
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("varreduraLocacaoTrigger", "group01")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 1 0 ? * MON-FRI *"))
                    .build();
            
            scheduler.scheduleJob(job,trigger);
            
        }catch(SchedulerException se){
            se.printStackTrace();
        }
        
    }
    
}
