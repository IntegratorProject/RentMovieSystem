
package util.prime.converter;

import dao.GenericDao;
import entidade.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterCliente")
public class ConverterCliente implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
            try{
                return (Cliente) dao.buscarId(Long.parseLong(value));
            }catch(Exception e){
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Cliente inválido"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return String.valueOf(((Cliente) value).getId());
        }
        return null;
    }
    
}
