
import java.math.BigDecimal;
import java.sql.*; 

public class Conn {

    Connection c;
    Statement s; // executes mysql query

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Hospital";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "megkhanal";

    Conn(){

        try {

        Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
        c = DriverManager.getConnection("jdbc:mysql:///Hospital","root","megkhanal"); // pass in connection string
        s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static boolean register(String email, String password, String firstName, String lastName, String phoneNumber, String gender, int idNum, String allergies, String chronicCondition, String medication, String familyHistory, String language, String relationship, String date) {
        try {
            // first we will need to check if the username has already been taken
            if (!checkUser(email)) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(first_name, last_name, email, password, phone_number, gender, id, allergies, chronic_condition, medication, family_history, language, relationship, date) " +
                                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                                preparedStatement.setString(1, firstName);
                                preparedStatement.setString(2, lastName);
                                preparedStatement.setString(3, email);
                                preparedStatement.setString(4, password);
                                preparedStatement.setString(5, phoneNumber);
                                preparedStatement.setString(6, gender);
                                preparedStatement.setInt(7, idNum);
                                preparedStatement.setString(8, allergies);
                                preparedStatement.setString(9, chronicCondition);
                                preparedStatement.setString(10, medication);
                                preparedStatement.setString(11, familyHistory);
                                preparedStatement.setString(12, language);
                                preparedStatement.setString(13, relationship);
                                preparedStatement.setString(14, date);

                preparedStatement.executeUpdate();
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean checkUser(String email) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ?");

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            // this means that the query returned no data meaning that the username is
            // available
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    
}
