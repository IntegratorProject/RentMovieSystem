package controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.ImpressorRelatorio;

@ManagedBean
@ViewScoped
public class RelatorioMB extends DefaultMB {

    private Date relFaturamentoPeriodoInicio;
    private Date relFaturamentoPeriodoFinal = new Date();

    public void imprimirRelatorioFaturamentoPeriodo() {

        String dataInicio = new SimpleDateFormat("yyyy-MM-dd").format(relFaturamentoPeriodoInicio);
        String dataFinal = new SimpleDateFormat("yyyy-MM-dd").format(relFaturamentoPeriodoFinal);
        
        String query = "SELECT locacao.dataLocacao,"
                + "locacao.dataDevolucao,"
                + "locacao.valorPago,"
                + "locacao.id,"
                + "dependente.nome "
                + "FROM locacao "
                + "INNER JOIN dependente ON "
                + "locacao.dependente_id = dependente.id "
                + "WHERE locacao.dataDevolucao <> null and "
                + "locacao.dataDevolucao >= '"+dataInicio+"' and "
                + "locacao.dataDevolucao <= '"+dataFinal+"'";
        
        System.out.println("Imprimindo relatorio \n" +query);
        
        try{
            
            ImpressorRelatorio.imprimir(query,
                    "Blank_A4", 
                    "relatorioFaturamento", null);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void imprimirRelatorioLocacoesAtrasadas(){
        
        
        
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
