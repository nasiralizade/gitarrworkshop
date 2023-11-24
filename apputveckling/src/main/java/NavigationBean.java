import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;

public class NavigationBean {

    public void redirectToAdminHome() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("admin_home.xhtml");
        facesContext.responseComplete();
    }
}
