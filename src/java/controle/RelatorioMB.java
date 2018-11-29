package controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

        String query = "SELECT locacao.dataLocacao AS DATALOCACAO, locacao.dataDevolucao AS DATADEVOLUCAO, locacao.valorPago AS VALORPAGO ,dependente.nome AS NOME "
                + "FROM locacao "
                + "INNER JOIN dependente ON "
                + "locacao.dependente_id = dependente.id "
                + "WHERE "
                + "locacao.dataDevolucao BETWEEN '2018-01-01' and '2018-12-12'";

        //System.out.println("Imprimindo relatorio \n" + query);

        try {

//            ImpressorRelatorio.imprimir(query,
//                    "rel_faturamento",
//                    "relatorioFaturamento", null);
            HashMap param = new HashMap<>();
            param.put("DATA_INICIO", dataInicio);
            param.put("DATA_FIM", dataFinal);

//            ImpressorRelatorio.relatorioConexao(
//                    "rel_faturamento",
//                    "relatorioFaturamento", param);

            ImpressorRelatorio.relatorioConexao(
                    "Blank_A4",
                    "relatorioFaturamento", null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void imprimirRelatorioLocacoesAtrasadas() {

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
