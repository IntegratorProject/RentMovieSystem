
package controle.beanServices;

import java.text.DateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.validadores.ValidadorCPF;

@ManagedBean(name = "textFormaterService")
@RequestScoped
public class TextFormaterService {
    
    public String formatCPF(String cpf) {
        if(!cpf.isEmpty()){
            return new ValidadorCPF().imprimeCPF(cpf);
        }
        return "";
    }
    
    public String formatData(Date data) {
        if(data != null){
            DateFormat formataData = DateFormat.getDateInstance();
            return formataData.format(data);
        }
        return null;
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
