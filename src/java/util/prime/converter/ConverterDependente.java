
package util.prime.converter;

import dao.GenericDao;
import entidade.Dependente;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterDependente")
public class ConverterDependente implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(!value.trim().isEmpty()){
            
            try{
                
                GenericDao<Dependente> dao = new GenericDao<>(Dependente.class);
                return (Dependente) dao.buscarId(Long.parseLong(value));
            
            }catch(Exception e){
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Dependente inválido"));
            }
            
        }
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value != null){
            return String.valueOf(((Dependente)value).getId());
        }
        
        return "";
        
    }
    
}
