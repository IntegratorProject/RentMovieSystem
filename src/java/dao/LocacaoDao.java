package dao;

import entidade.ItensLocacao;
import entidade.Locacao;
import java.util.List;

public class LocacaoDao extends GenericDao<Locacao> {

    private GenericDao<ItensLocacao> daoItensLocacao = new GenericDao<>(ItensLocacao.class);
    private MidiaDao daoMidia = new MidiaDao();

    public LocacaoDao() {
        super(Locacao.class);
    }

    public boolean delete(Locacao l) throws Exception {
        
        List<ItensLocacao> tempListLocacao = daoItensLocacao.buscarCondicao("locacao_id = " + l.getId());

        for (ItensLocacao il : tempListLocacao) {

            daoMidia.mudarDisponibilidade(il.getMidia(), "disponivel");
            daoItensLocacao.delete(il.getId());

        }

        return delete(l.getId());
        
    }

}
