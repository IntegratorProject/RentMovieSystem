
package util;

import java.text.DateFormat;
import java.util.Date;

public class TextFormater {
    
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
