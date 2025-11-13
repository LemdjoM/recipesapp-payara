package ch.unil.doplab.recipesapp.ui;

import ch.unil.doplab.recipesapp.RecipesAppService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@SessionScoped
@Named
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;

    @Inject
    private RecipesAppService recipesAppService;
    @Inject
    RecipesAppService theService;

    public LoginBean() { }
    public void reset() {
        username = null;
        password = null;
        role = null;
    }

    // Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String login() {
        var uuid = theService.authenticate(username, password, role);
        var session = getSession(true);
        if (uuid != null) {
            session.setAttribute("uuid", uuid);
            session.setAttribute("username", username);
            //session.setAttribute("role", role);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Invalid login", null));
        reset();
        return "Login";
    }


    public static HttpSession getSession(boolean create) {
        var facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            return null;
        }
        var externalContext = facesContext.getExternalContext();
        if (externalContext == null) {
            return null;
        }
        return (HttpSession) externalContext.getSession(create);
    }

    public static void invalidateSession() {
        var session = getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
