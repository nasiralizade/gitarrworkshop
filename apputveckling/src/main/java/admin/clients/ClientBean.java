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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ClientBean implements Serializable {

    private String searchName;
    private String case_typ;
    private List<Client> clientList;
    private   List<Cases> caseList;

    @PersistenceContext(unitName = "mysql_web")
    EntityManager em;

    @PostConstruct
    public void init() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
        TypedQuery<Cases> query2 = em.createQuery("SELECT a FROM Cases a", Cases.class);

        clientList = query.getResultList();
        caseList = query2.getResultList();

    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
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

        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.clientName LIKE :searchName", Client.class);
        query.setParameter("searchName", "%" + searchName + "%");
        List<Client> matchList = query.getResultList();

        for(Client x: matchList){
            clientList.add(0,x);
        }


    }

    public void FindCase(int clientID){

        if(caseList.isEmpty()){
            System.out.println("Empty");
        }

    }


    public static void main(String[] arg){
        ClientBean D = new ClientBean();
        D.init();
        D.FindCase(1);
    }

}