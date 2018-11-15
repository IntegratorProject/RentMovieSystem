
package util.quartz;

import dao.LocacaoDao;
import entidade.Locacao;
import java.util.Calendar;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ServiceRmvReservaLocacao implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        System.out.println("Iniciando serviço de varredura na tabela Locacao");
        try{
            
            LocacaoDao daoLocacao = new LocacaoDao();
            List<Locacao> listReservas = daoLocacao.buscarCondicao("reserva = 1");
            
            if(listReservas.isEmpty()){
                System.out.println("Nenhuma reserva encontrada");
            }else{
                
                System.out.println("Iniciando analise...");
                Calendar hoje = Calendar.getInstance();
                
                for(Locacao l : listReservas){
                    
                    Calendar dataLocacao = Calendar.getInstance();
                    dataLocacao.setTime(l.getDataLocacao());
                    
                    if(hoje.after(daoLocacao)){
                        
                        System.out.println("Reserva irregular encontrada. Iniciando processo de remoção.");
                        daoLocacao.delete(l);
                        System.out.println("Reserva removida com sucesso!");
                        
                    }
                    
                }
                
            }
            
            System.out.println("Fim de execução da rotina");
            
        }catch(Exception e){
            e.printStackTrace();
            throw new JobExecutionException();
        }
        
    }
    
}
