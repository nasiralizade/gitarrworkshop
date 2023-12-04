package admin.cases;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
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
@ViewScoped
public class CasesBean implements Serializable {
    @Resource(name = "mysql_web")
    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(CasesBean.class.getName());
    private List<Cases> my_cases;
    private Cases clientToEdit;
    @PostConstruct
    public void init() {
        // Initialize clientToEdit
        clientToEdit = new Cases();
        // Load member data from the database during bean initialization
        loadClients();
    }


    private void loadClients() {
        my_cases = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CASES";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    my_cases.add(mapResultSetToClient(resultSet));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CasesBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
    }

    // Encapsulate the logic for mapping a row in the result set to a Member object
    private Cases mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Cases client = new Cases();
        client.setCase_id(resultSet.getInt("CASE_ID"));
        client.setCase_desc(resultSet.getString("CASE_DESC"));
        client.setCase_status(resultSet.getString("CASE_STATUS"));
        client.setCase_type(resultSet.getString("CASE_TYPE"));
        return client;
    }
/*

    public void editClient(int clientID) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM CASES WHERE CASE_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, clientID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        clientToEdit = mapResultSetToClient(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CasesBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
    }

    public Cases getClientToEdit() {
        return clientToEdit;
    }
    public String saveOrUpdateClient() {
        try (Connection connection = dataSource.getConnection()) {
            String sql;
            if (clientToEdit.getCase_id() == -1) {
                sql = "INSERT INTO CASES (CLIENT_NAME, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_DATE) VALUES (?, ?, ?, CURRENT_DATE)";
            } else {
                sql = "UPDATE CLIENT SET CLIENT_NAME = ?, CLIENT_PHONE = ?, CLIENT_EMAIL= ? WHERE CLIENT_ID = ?";
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(2, clientToEdit.getCase_desc());
                statement.setString(3, clientToEdit.getCase_status());
                if (clientToEdit.getCase_id() != -1) {
                    statement.setInt(4, clientToEdit.getCase_id());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(CasesBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadClients();
        return "cases"; // Return the name of the page where the user can see the member list
    }

    public void addClient() {
        clientToEdit = new Cases(); // Create a new Member object
        clientToEdit.setCase_id(-1); // Set memberID to -1 to represent a new member
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
            Logger.getLogger(CasesBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadClients();
        return "clients"; // Return the name of the page where the user can see the member list
    }
*/
    // Getter method for the members list
    public List<Cases> getMy_cases() {
        return my_cases;
    }
}