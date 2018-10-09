
package controle.beanServices;

import java.text.DateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.prime.validadores.ValidadorCPF;

@ManagedBean(name = "textFormaterService")
@RequestScoped
public class TextFormaterService {
    
    public String formatCPF(String cpf) {
        return new ValidadorCPF().imprimeCPF(cpf);
    }
    
    public String formatData(Date data) {
        DateFormat formataData = DateFormat.getDateInstance();
        return formataData.format(data);
    }
    
    public String formatDisponiblidade(String entrada){
        
        switch(entrada){
            
            case "alugado":
                return "Alugado";
            
            case "disponivel":
                return "Disponível";
                
            case "indisponivel":
                return "Indisponível";
                
            default:
                return "";
                
        }
        
    }
    
}
