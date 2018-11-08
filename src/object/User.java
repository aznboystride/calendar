package object;

import java.sql.Date;

public class User extends UserAccount {

    private static final User user = new User("", "", "", "",
            ' ', new Date(System.currentTimeMillis()), ""
    );

    private User(String userName, String firstName, String lastName, String email, char gender, Date dateOfBirth, String password) {
        super(userName, firstName, lastName, email, gender, dateOfBirth, password);
    }

    public static User GetInstance() {
        return user;
    }
}
