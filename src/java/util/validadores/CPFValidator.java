
package util.validadores;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
import util.prime.validadores.ValidadorCPF;

@FacesValidator("custom.cpfValidator")
public class CPFValidator implements Validator, ClientValidator{
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        ValidadorCPF vCPF = new ValidadorCPF();
        if(!vCPF.isCPF(vCPF.removeMask(value.toString()))){
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação!", 
                        "CPF Inválido"));
            
        }
        
    }
    
    public Map<String, Object> getMetadata() {
        return null;
    }
    
    public String getValidatorId() {
        return "custom.cpfValidator";
    }
    
}
