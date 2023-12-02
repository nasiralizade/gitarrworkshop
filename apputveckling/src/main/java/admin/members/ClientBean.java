package admin.members;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ClientBean implements Serializable {
    @Resource(name = "msql_web")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(ClientBean.class.getName());
    private List<Client> clients;

    @PostConstruct
    public void init() {
        MysqlDataSource dataSource1 = new MysqlDataSource();
        dataSource1.setURL("jdbc:mysql://localhost:3306/Projectdb");
        dataSource1.setUser("root2");
        dataSource1.setPassword("1234");
        dataSource = dataSource1;

        // Load member data from the database during bean initialization
        loadClients();
    }

    // Encapsulate the logic for mapping a row in the result set to a Member object



    private void loadClients() {
        clients = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM client";


            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    clients.add(mapResultSetToClient(resultSet));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
    }


    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {

        Client client = new Client();
        client.setClientID(resultSet.getInt("CLIENT_ID"));
        client.setName(resultSet.getString("CLIENT_NAME"));
        client.setPhone(resultSet.getString("CLIENT_PHONE"));
        client.setEmail(resultSet.getString("CLIENT_EMAIL"));
        client.setDate(resultSet.getString("CLIENT_DATE"));

        try (Connection connection = dataSource.getConnection()) {
            String sql2 = "SELECT * FROM cases WHERE MEMBER_ID ="+ client.getClientID();


            try (PreparedStatement statement = connection.prepareStatement(sql2);
                 ResultSet resultSet2 = statement.executeQuery()) {

               client.setCas_type(resultSet2.getString("CASE_TYPE"));
               client.setDate(String.valueOf(resultSet2.getDate("CASE_DATE_START")));
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        return client;
    }

    private Client clientToEdit;

    public String editClient(int clientID) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CLIENT WHERE CLIENT_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, clientID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        clientToEdit = mapResultSetToClient(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        return "editClient"; // Return the name of the page where the user can edit the member data
    }

    public Client getClientToEdit() {
        return clientToEdit;
    }

    public String saveOrUpdateClient() {
        try (Connection connection = dataSource.getConnection()) {
            String sql;
            if (clientToEdit.getClientID() == -1) {
                sql = "INSERT INTO CLIENT (CLIENT_NAME, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_DATE) VALUES (?, ?, ?, CURRENT_DATE)";
            } else {
                sql = "UPDATE CLIENT SET CLIENT_NAME = ?, CLIENT_PHONE = ?, CLIENT_EMAIL= ? WHERE CLIENT_ID = ?";
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, clientToEdit.getName());
                statement.setString(2, clientToEdit.getPhone());
                statement.setString(3, clientToEdit.getEmail());
                if (clientToEdit.getClientID() != -1) {
                    statement.setInt(4, clientToEdit.getClientID());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadClients();
        return "clients"; // Return the name of the page where the user can see the member list
    }

    public String addClient() {
        clientToEdit = new Client(); // Create a new Member object
        clientToEdit.setClientID(-1); // Set memberID to -1 to represent a new member
        return "editClient"; // Return the name of the page where the user can input the data for the new member
    }


    public String deleteEntity(int clientID) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM CLIENT WHERE CLIENT_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, clientID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("An error occurred while deleting the member."));
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadClients();
        return "clients"; // Return the name of the page where the user can see the member list
    }

    // Getter method for the members list
    public List<Client> getClients() {
        return clients;
    }

    public static void main(String[] arg){
        ClientBean D=new ClientBean();
    }
}