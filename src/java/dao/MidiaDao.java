package dao;

import entidade.Midia;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MidiaDao extends GenericDao<Midia> {

    public MidiaDao() {
        super(Midia.class);
    }

    public List<Midia> buscarAlugadosPorIdade(int idade) throws Exception {

        List<Midia> tempList = new ArrayList<>();

        tempList = buscarCondicao("disponibilidade = 'disponivel'");
        
        if (idade < 18) {
            
            for (Iterator<Midia> iterator = tempList.iterator(); iterator.hasNext();) {

                String c = iterator.next().getAcervo().getClassificacaoEtaria();
                if (!c.equals("Livre")) {

                    if (Integer.parseInt(c) > idade) {
                        iterator.remove();
                    }

                }

            }

        }

        return tempList;
        
    }
    
    public Midia mudarDisponibilidade(Midia midia, String disponibilidade) throws Exception{
        
        midia.setDisponibilidade(disponibilidade);
        return editar(midia);
        
    }

}
