package admin.clients;

import admin.DB.DB;
import admin.cases.Cases;
import admin.cases.CasesBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ClientBean implements Serializable {
    private TypedQuery<Client> query;
    private String searchName;

    @PersistenceContext(unitName = "mysql_web")
    EntityManager em;



    public TypedQuery<Client> getQuery() {
        return query;
    }

    public void setQuery(TypedQuery<Client> query) {
        this.query = query;
    }

    @PostConstruct
    public void init() {
       // query = em.createQuery("SELECT e FROM Client e WHERE e.clientId = :value", Client.class);

    }


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }


    public String editClient(int clientID) {

        return "customerInfo.xhtml";
    }


    public String addClient(Client client) {

        return "addclients"; // Return the name of the page where the user can input the data for the new member
    }


    public String deleteEntity(int clientID) {

        return "admin_clients.xhtml"; // Return the name of the page where the user can see the member list
    }

    public void SearchClient(){

    }


}