
package controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class DefaultMB {
    
    /**
     * Creates an information message displayed on a dialog for error on xhtml page
     * @param strongText Destacated text
     * @param text Description text
     */
    public void showInformationMessage(String strongText, String text){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, strongText, text));
    }
    
    /**
     * Creates an warning message displayed on a dialog for error on xhtml page
     * @param strongText Destacated text
     * @param text Description text
     */
    public void showWarningMessage(String strongText, String text){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, strongText, text));
    }
    
    /**
     * Creates an error message displayed on a dialog for error on xhtml page
     * @param strongText Destacated text
     * @param text Description text
     */
    public void showErrorMessage(String strongText, String text){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, strongText, text));
    }
    
    /**
     * Creates an fatal error message displayed on a dialog for error on xhtml page
     * @param strongText Destacated text
     * @param text Description text
     */
    public void showFatalMessage(String strongText, String text){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_FATAL, strongText, text));
    }
    
    
    //Errors
    
    /**
     * Creates an fatal error message displayed on a dialog for error on xhtml page,
     * apointing an connection error
     */
    public void connetionError(){ 
        showFatalMessage("Falha de Conexão",
                    "Se o problema persistir, contate o desenvolvedor!");
    }
    
}
