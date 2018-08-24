
package controle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped
public class InicioMB {
    
    private DashboardModel model;
    
    @PostConstruct
    public void init(){
        
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
         
        column1.addWidget("locacoesAtrazadas");
        column1.addWidget("ultimasMovimentacoes");
         
        column2.addWidget("faturamentoDiario");
        column2.addWidget("devolucoesPrevistas");
 
        model.addColumn(column1);
        model.addColumn(column2);
        
    }
    
    public DashboardModel getModel(){
        return model;
    }
    
}
