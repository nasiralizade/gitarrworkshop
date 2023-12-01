package admin.members;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
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
public class Membersbean implements Serializable {
    @Resource(name = "projectdb")
    private DataSource dataSource;
    private List<MemberInfo> member_list;

    public Membersbean() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/Projectdb");
        mysqlDataSource.setUser("root2");
        mysqlDataSource.setPassword("1234");
        dataSource = mysqlDataSource;

        member_list = new ArrayList<>();
        loadMembers(); // Call the method to load members on bean instantiation
    }

    private void loadMembers() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM client";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    MemberInfo client = new MemberInfo(); // Create a new instance for each member
                    client.setMemberID(resultSet.getInt("client_id"));
                    client.setClientName(resultSet.getString("Client_name"));
                    client.setEmail(resultSet.getString("client_email"));
                    client.setPhone(resultSet.getString("client_phone"));
                    //client.setAddress(resultSet.getString("Address"));

                    String sql2 = "SELECT * FROM cases WHERE member_id =" + client.getMemberID();

                    try (PreparedStatement statement2 = connection.prepareStatement(sql2);
                    ResultSet resultSet2 = statement2.executeQuery()){

                        while (resultSet2.next()){
                            client.setStatus(resultSet2.getString("status"));
                            client.setCase_typ(resultSet2.getString("case_type"));
                        }

                    }catch (SQLException e){
                        Logger.getLogger(Membersbean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
                    }


                    member_list.add(client);
                }
            }
        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            Logger.getLogger(Membersbean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }


        for (MemberInfo x: member_list) {
            System.out.println(x.getMemberID()+" "+x.getClientName()+" "+x.getCase_typ());
        }

    }

    public List<MemberInfo> getMemberlist() {
        return member_list;
    }

}
