package admin.clients;
import admin.cases.Cases;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class ClientBean implements Serializable {
    private Client clientTOedit;

    private String passwordTemp;

    private String searchName;
    private String case_typ;
    private List<Client> clientList;
    private   List<Cases> caseList;

    @PersistenceContext(unitName = "mysql_web")
    EntityManager em;

    @PostConstruct
    public void init() {

        clientTOedit = new Client();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
        TypedQuery<Cases> query2 = em.createQuery("SELECT a FROM Cases a", Cases.class);

        clientList = query.getResultList();
        caseList = query2.getResultList();

    }
    public String getPasswordTemp() {
        return passwordTemp;
    }

    public void setPasswordTemp(String passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

    public Client getClientTOedit() {
        return clientTOedit;
    }

    public void setClientTOedit(Client clientTOedit) {
        this.clientTOedit = clientTOedit;
    }

    public String getCase_typ() {
        return case_typ;
    }

    public void setCase_typ(String case_typ) {
        this.case_typ = case_typ;
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


    public String editClient() {
        return "customerInfo.xhtml";
    }

    public String collectClientInfo(int clientID){

        Query query = em.createQuery("SELECT c FROM Client c WHERE c.clientId =:clientID", Client.class);
        query.setParameter("clientID", clientID);
        clientTOedit = (Client) query.getSingleResult();

        return "customerInfo.xhtml";
    }


    public String addClient() {
        int count = 0;

        for (Client x : clientList) {
            if (clientTOedit.getClientEmail().equals(x.getClientEmail()) || clientTOedit.getClientPhone().equals(x.getClientPhone())) {
                count = count + 1;
            }
        }

        if (count == 0) {
            em.persist(clientTOedit);
        }

        return "admin_clients.xhtml";
    }



    public String deleteEntity(int clientID) {
        // Find the entity by ID
        Client entityToDelete = em.find(Client.class, clientID);

        // Check if the entity exists before attempting to delete
        if (entityToDelete != null) {
            // Remove the entity from the database
            em.remove(entityToDelete);
        }

        return "admin_clients.xhtml"; // Return the name of the page where the user can see the member list
    }

    public void SearchClient(){

        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.clientName LIKE :searchName", Client.class);
        query.setParameter("searchName", "%" + searchName + "%");
        List<Client> matchList = query.getResultList();

        Iterator<Client> iterator = clientList.iterator();
        while (iterator.hasNext()) {
            Client match = iterator.next();
            if (matchList.stream().anyMatch(name -> name.getClientName().equals(match.getClientName()))) {
                iterator.remove(); // Remove the matching client
            }
        }

        clientList.addAll(0, matchList);

    }

    public void FindCase(int clientID){

        caseList = em.createQuery("select p from Cases p where p.CASE_ID = :caseId", Cases.class)
                .setParameter("caseId", clientID)
                .getResultList();
       // case_typ = caseList.get(0).getCASE_TYPE();

    }

    public void NewInstans(){
        clientTOedit = new Client();
    }


    public static void main(String[] arg){
        ClientBean D = new ClientBean();
        D.init();
        D.FindCase(1);
    }

}