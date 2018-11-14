
package util.prime.converter;

import dao.GenericDao;
import entidade.Midia;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterMidia")
public class ConverterMidia implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            GenericDao<Midia> dao = new GenericDao<>(Midia.class);
            try{
                return (Midia) dao.buscarId(Long.parseLong(value));
            }catch(Exception e){
                e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro midia invalida", ""));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return String.valueOf(((Midia) value).getId());
        }
        return null;
    }
}
