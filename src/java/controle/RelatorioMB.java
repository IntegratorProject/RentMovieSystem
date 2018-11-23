
package controle;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RelatorioMB extends DefaultMB{
    
    private Date relFaturamentoPeriodoInicio;
    private Date relFaturamentoPeriodoFinal = new Date();
    
    public void imprimirRelatorio(){
    }

    public Date getRelFaturamentoPeriodoInicio() {
        return relFaturamentoPeriodoInicio;
    }

    public void setRelFaturamentoPeriodoInicio(Date relFaturamentoPeriodoInicio) {
        this.relFaturamentoPeriodoInicio = relFaturamentoPeriodoInicio;
    }

    public Date getRelFaturamentoPeriodoFinal() {
        return relFaturamentoPeriodoFinal;
    }

    public void setRelFaturamentoPeriodoFinal(Date relFaturamentoPeriodoFinal) {
        this.relFaturamentoPeriodoFinal = relFaturamentoPeriodoFinal;
    }
    
}
