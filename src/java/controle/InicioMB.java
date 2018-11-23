package controle;

import dao.GenericDao;
import dao.MidiaDao;
import entidade.Cliente;
import entidade.Locacao;
import entidade.Midia;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class InicioMB implements Serializable {

    private int clientesAtivos = 0;
    private int locacoesCorrentes = 0;
    private double faturamentoDiário = 0.d;
    private int midiasDestruidas = 0;

    private String queryAbleTodayDate = "";

    public InicioMB() {

        Calendar hoje = Calendar.getInstance();
        queryAbleTodayDate = new SimpleDateFormat("yyyy-MM-dd").format(hoje.getTime());

        updateClientesAtivos();
        updateLocacoesCorrentes();
        updateFaturamentaDiario();
        updateMidiasDestruidas();
        
    }

    private void updateClientesAtivos() {
        try {
            GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
            List<Cliente> list = dao.buscarCondicao("enable = 1");
            clientesAtivos = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLocacoesCorrentes() {
        try {
            GenericDao<Locacao> dao = new GenericDao<>(Locacao.class);
            List<Locacao> list = dao.buscarCondicao("dataDevolucao = null and reserva = 0");
            locacoesCorrentes = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFaturamentaDiario() {
        try {
            GenericDao<Locacao> dao = new GenericDao<>(Locacao.class);
            List<Locacao> list = dao.buscarCondicao("dataDevolucao = '"+queryAbleTodayDate+"'");
            
            double somatoria = 0.d;
            
            for(Locacao l : list){
                somatoria += l.getValorPago();
            }
            
            faturamentoDiário = somatoria;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateMidiasDestruidas(){
        try{
            
            MidiaDao dao = new MidiaDao();
            List<Midia> list = dao.buscarCondicao("disponibilidade = 'indisponivel'");
            midiasDestruidas = list.size();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getClientesAtivos() {
        return clientesAtivos;
    }

    public void setClientesAtivos(int clientesAtivos) {
        this.clientesAtivos = clientesAtivos;
    }

    public int getLocacoesCorrentes() {
        return locacoesCorrentes;
    }

    public void setLocacoesCorrentes(int locacoesCorrentes) {
        this.locacoesCorrentes = locacoesCorrentes;
    }

    public double getFaturamentoDiário() {
        return faturamentoDiário;
    }

    public void setFaturamentoDiário(double faturamentoDiário) {
        this.faturamentoDiário = faturamentoDiário;
    }

    public int getMidiasDestruidas() {
        return midiasDestruidas;
    }

    public void setMidiasDestruidas(int midiasDestruidas) {
        this.midiasDestruidas = midiasDestruidas;
    }

}
