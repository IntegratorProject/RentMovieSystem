package controle;

import dao.MidiaDao;
import dao.GenericDao;
import dao.LocacaoDao;
import entidade.ItensLocacao;
import entidade.Locacao;
import entidade.Midia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.DateCalculator;
import util.spring.UserSession;

@ManagedBean
@ViewScoped
public class LocacaoMB extends DefaultMB {

    private Locacao locacao = new Locacao();
    private ItensLocacao itemLocacao = new ItensLocacao();
    private Midia midia = new Midia();

    private List<Midia> midiasDisponiveis = new ArrayList<>();
    private List<Locacao> listLocacao = new ArrayList<>();
    private List<ItensLocacao> listItensLocacao = new ArrayList<>();

    private LocacaoDao daoLocacao = new LocacaoDao();
    private GenericDao<ItensLocacao> daoItensLocacao = new GenericDao<>(ItensLocacao.class);
    private MidiaDao daoMidia = new MidiaDao();

    public LocacaoMB() {
        updateList();
    }

    private void updateList() {
        try {
            listLocacao = daoLocacao.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }
    }

    public void updateValidMidias() {
        try {
            int idade = DateCalculator.getAgeByBirthDate(
                    locacao.getDependente().getDataNascimento());
            midiasDisponiveis = daoMidia.buscarAlugadosPorIdade(idade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void definirItemLocacao() {
        itemLocacao.setMidia(midia);
        itemLocacao.setPreco(midia.getPrecoLocacao());
    }

    public void adicionarItemLocacao() {
        if (itemLocacao.getMidia() == null) {
            showErrorMessage("Erro!", "Informe uma midia para prosseguir.");
        } else if (itemLocacao.getPreco() <= 0) {
            showErrorMessage("Erro!", "Informe um preço maior do que zero.");
        } else {

            for (ItensLocacao il : listItensLocacao) {
                if (il.getMidia().equals(itemLocacao.getMidia())) {
                    showErrorMessage("Erro!", "Uma mídia idêntica já foi selecionada!");
                    return;
                }
            }

            listItensLocacao.add(itemLocacao);
            updateTotalLocacao();
            itemLocacao = new ItensLocacao();
            midia = new Midia();

        }
    }

    public void removerItemLocacao(ItensLocacao il) {
        listItensLocacao.remove(il);
        updateTotalLocacao();
    }

    private void updateTotalLocacao() {
        double totalLocacao = 0.d;
        for (ItensLocacao il : listItensLocacao) {
            totalLocacao += il.getPreco();
        }
        locacao.setPrecoLocacao(totalLocacao);
    }

    public void cadastrar() {

        if (locacao.getId() == 0) {

            try {

                locacao.setDataPagamento(locacao.getDataPrevDevolucao());

                Calendar dataLocacao = Calendar.getInstance();
                dataLocacao.setTime(locacao.getDataLocacao());
                Calendar dataHoje = Calendar.getInstance();
                locacao.setReserva(dataLocacao.after(dataHoje));

                locacao.setFuncionario(UserSession.getCurrentUser());

                locacao = daoLocacao.salvar(locacao);

                for (ItensLocacao il : listItensLocacao) {
                    il.setLocacao(locacao);
                    daoItensLocacao.salvar(il);
                    daoMidia.mudarDisponibilidade(il.getMidia(), "alugado");
                }

                fullClear();

                showInformationMessage("Sucesso!", "Cadastro concluído.");

            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }

        }

    }

    private void fullClear() {
        locacao = new Locacao();
        midia = new Midia();
        itemLocacao = new ItensLocacao();
        listItensLocacao.clear();
        listLocacao.clear();
        midiasDisponiveis.clear();
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

    public void carregarItensLocacaoByLocacao(Locacao l) {

        listItensLocacao.clear();
        try {
            listItensLocacao = daoItensLocacao.buscarCondicao("locacao_id = " + l.getId());
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public void solicitarExclusaoLocacao(Locacao l) {
        if (l.isReserva()) {
            excluirLocacao(l);
        } else {

            Calendar hoje = Calendar.getInstance();
            Calendar dataLocacao = Calendar.getInstance();
            dataLocacao.setTime(l.getDataLocacao());

            if (hoje.equals(dataLocacao)) {
                excluirLocacao(l);
                return;
            } else {
                showWarningMessage("Atenção",
                        "Apenas reservas e locações realizadas no dia de hoje podem ser excluidas.");
            }
        }
    }

    private void excluirLocacao(Locacao l) {

        try {

            daoLocacao.delete(l);
            updateList();

            showInformationMessage("Sucesso!", "Locação removida");

        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public void modificarStatusLocacao(Locacao l) {

        Calendar hoje = Calendar.getInstance();
        Calendar dataLocacao = Calendar.getInstance();
        dataLocacao.setTime(l.getDataLocacao());

        if (hoje.equals(dataLocacao) || hoje.before(dataLocacao)) {
            try {
                l.setReserva(false);
                if(!hoje.equals(dataLocacao)) l.setDataLocacao(hoje.getTime());
                daoLocacao.editar(l);
                showInformationMessage("Sucesso!", "Reserva transformada em locação.");
            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }
        }else{
            showErrorMessage("Erro!", "A reserva passou do prazo determinado.");
        }

    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public ItensLocacao getItemLocacao() {
        return itemLocacao;
    }

    public void setItemLocacao(ItensLocacao itemLocacao) {
        this.itemLocacao = itemLocacao;
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

    public List<Midia> getMidiasDisponiveis() {
        return midiasDisponiveis;
    }

    public void setMidiasDisponiveis(List<Midia> midiasDisponiveis) {
        this.midiasDisponiveis = midiasDisponiveis;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

}
