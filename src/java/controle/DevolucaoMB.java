package controle;

import dao.GenericDao;
import dao.LocacaoDao;
import dao.MidiaDao;
import entidade.ItensLocacao;
import entidade.Locacao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DevolucaoMB extends DefaultMB implements Serializable {

    private List<Locacao> listLocacao = new ArrayList<>();
    private List<Locacao> fullListLocacao = new ArrayList<>();
    private List<ItensLocacao> listItensLocacao = new ArrayList<>();

    private Locacao locacao = new Locacao();

    private String filtro = "";

    private LocacaoDao daoLocacao = new LocacaoDao();
    private GenericDao<ItensLocacao> daoItensLocacao = new GenericDao<>(ItensLocacao.class);
    private MidiaDao daoMidia = new MidiaDao();

    public DevolucaoMB() {
        updateList();
    }

    public void concluirOperacao() {
        if (calcNumeroDevolvidosOuDanificados() != 0
                || !locacao.getDescricaoMulta().isEmpty()
                || locacao.getValorMulta() != 0
                || locacao.getValorPago() != 0) {

            try {

                int quantidadeDevolvidos = 0;
                for (ItensLocacao item : listItensLocacao) {

                    if (item.getOperacao() != null) {

                        item.setDevolvido(true);
                        if (item.getOperacao().equals("Devolver")) {
                            daoMidia.mudarDisponibilidade(item.getMidia(), "disponivel");
                        } else if (item.getOperacao().equals("Inviabilizar")) {
                            daoMidia.mudarDisponibilidade(item.getMidia(), "indisponivel");
                        } else {
                            throw new Exception("Operação não suportada");
                        }

                        item = daoItensLocacao.editar(item);

                    }

                    if (item.isDevolvido()) {
                        quantidadeDevolvidos++;
                    }

                }

                if (quantidadeDevolvidos == listItensLocacao.size() && locacao.getValorPago() != 0) {
                    Calendar hoje = Calendar.getInstance();
                    locacao.setDataDevolucao(hoje.getTime());
                    locacao.setDataPagamento(hoje.getTime());
                }

                daoLocacao.editar(locacao);
                fullClear();
                updateList();
                showInformationMessage("Sucesso!", "Devolução concluída.");

            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }

        } else {
            showInformationMessage("Informação", "Nenhuma alteração foi identificada.");
        }
    }

    public void carregarItensLocacaoByLocacao(Locacao l) {

        listItensLocacao.clear();
        try {
            listItensLocacao = daoItensLocacao.buscarCondicao("locacao_id = " + l.getId());
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public String definirStatus(Locacao l) {
        if (l.isReserva()) {
            return "Reserva";
        } else {
            if (l.getDataDevolucao() != null) {
                return "Concluído";
            } else {
                return "Corrente";
            }
        }
    }

    public void devolver(ItensLocacao item, boolean isDanificado) {

        if (isDanificado) {
            item.setOperacao("Inviabilizar");
        } else {
            item.setOperacao("Devolver");
        }

        listItensLocacao.set(listItensLocacao.indexOf(item), item);

    }

    private void updateList() {
        try {

            fullListLocacao.clear();
            listLocacao.clear();
            fullListLocacao = daoLocacao.buscarCondicao("dataDevolucao = null and reserva = 0");

            for (Locacao l : fullListLocacao) {
                listLocacao.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }
    }

    public void updateFiltredList() {

        if (filtro == null) {
            updateList();
            return;
        }
        
        if (filtro.isEmpty()) {
            updateList();
            return;
        }

        try {
            
            fullListLocacao.clear();
            listLocacao.clear();

            Calendar hoje = Calendar.getInstance();
            String dataHoje = new SimpleDateFormat("yyyy-MM-dd").format(hoje.getTime());

            if (filtro.equals("Hoje")) {
                fullListLocacao = daoLocacao.buscarCondicao("dataDevolucao = null and reserva = 0 and "
                        + "dataPrevDevolucao = '" + dataHoje + "'");
            } else if (filtro.equals("Atrasados")) {
                fullListLocacao = daoLocacao.buscarCondicao("dataDevolucao = null and reserva = 0 and "
                        + "dataPrevDevolucao < '" + dataHoje + "'");
            }
            
            for(Locacao l : fullListLocacao){
                listLocacao.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    private int calcNumeroDevolvidosOuDanificados() {

        int count = 0;
        for (ItensLocacao item : listItensLocacao) {
            if (item.getOperacao() != null) {
                count++;
            }
        }
        return count;

    }

    private void fullClear() {
        listItensLocacao.clear();
        listLocacao.clear();
        fullListLocacao.clear();
        locacao = new Locacao();
    }

    public List<Locacao> getListLocacao() {
        return listLocacao;
    }

    public void setListLocacao(List<Locacao> listLocacao) {
        this.listLocacao = listLocacao;
    }

    public List<ItensLocacao> getListItensLocacao() {
        return listItensLocacao;
    }

    public void setListItensLocacao(List<ItensLocacao> listItensLocacao) {
        this.listItensLocacao = listItensLocacao;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public List<Locacao> getFullListLocacao() {
        return fullListLocacao;
    }

    public void setFullListLocacao(List<Locacao> fullListLocacao) {
        this.fullListLocacao = fullListLocacao;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
