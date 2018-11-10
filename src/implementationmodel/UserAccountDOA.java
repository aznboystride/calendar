package implementationmodel;

import interfacemodel.DelegationOfAuthority;
import object.UserAccount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDOA implements DelegationOfAuthority {

    private static final UserAccountDOA INSTANCE = new UserAccountDOA();

    private UserAccountDOA() {}

    public static UserAccountDOA GetInstance() {
        return INSTANCE;
    }

    private final Connection connection = ConnectionManager.GetInstance().GetConnection(this);

    @Override
    public void CreateTable() {
        String createInsr = "" +
                "CREATE TABLE USERACCOUNT(" +
                "username varchar(50)," +
                "password varchar(50)," +
                "firstname varchar(50)," +
                "lastname varchar(50)," +
                "email varchar(50)," +
                "gender char," +
                "dob DATE" +
                ");";
        try {
            PreparedStatement statement = connection.prepareStatement(createInsr);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DropTable() {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM UserAccount";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Insert(Object user) {
        PreparedStatement preparedStatement = null;
        String query = "insert into UserAccount VALUES (?,?,?,?,?,?,?)";
        if(connection != null)
        {
            try
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ((UserAccount)user).getUserName());
                preparedStatement.setString(2, ((UserAccount)user).getPassword());
                preparedStatement.setString(3, ((UserAccount)user).getFirstName());
                preparedStatement.setString(4, ((UserAccount)user).getLastName());
                preparedStatement.setString(5, ((UserAccount)user).getEmail());
                preparedStatement.setString(6, String.valueOf(((UserAccount)user).getGender()));
                preparedStatement.setDate(7, ((UserAccount)user).getDateOfBirth());
                preparedStatement.executeUpdate();
                System.out.println("Successfully inserted UserAccount: ");
                System.out.println(user);
            }
            catch(SQLException e)
            {
                System.out.println("Fail to insert user");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    @Override
    public void Update(Object o) {
        UserAccount userAccount = (UserAccount) o;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE userAccount " +
                            "SET firstname = ?, lastname = ?, gender = ?, email = ?, dob = ?, " +
                            "password = ? " +
                            "WHERE userAccount.username = ?"
            );
            preparedStatement.setString(1, userAccount.getFirstName());
            preparedStatement.setString(2, userAccount.getLastName());
            preparedStatement.setString(3, Character.toString(userAccount.getGender()));
            preparedStatement.setString(4, userAccount.getEmail());
            preparedStatement.setDate(5, userAccount.getDateOfBirth());
            preparedStatement.setString(6, userAccount.getPassword());
            preparedStatement.setString(7, userAccount.getUserName());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserAccount> GetList() {
        List<UserAccount> list = new ArrayList<>();
        try {
            String user, pass, fname, lname, email;
            char gender;
            Date dob;
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM " +
                    "userAccount")
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                user = resultSet.getString("username");
                pass = resultSet.getString("password");
                fname = resultSet.getString("firstname");
                lname = resultSet.getString("lastname");
                email = resultSet.getString("email");
                gender = resultSet.getString("gender").charAt(0);
                dob = resultSet.getDate("dob");
                list.add(new UserAccount(user, fname, lname, email, gender, dob, pass));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean Exists(Object o) {
        UserAccount userAccount = (UserAccount) o;
        for(UserAccount user: GetList()) {
            if(user.getUserName().equalsIgnoreCase(userAccount.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
