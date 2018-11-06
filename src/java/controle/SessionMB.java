
package controle;

import entidade.Funcionario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.spring.UserSession;

@ManagedBean
@SessionScoped
public class SessionMB {
    
    private Funcionario funcionario;

    public SessionMB(){
        funcionario = UserSession.getCurrentUser();
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
}
