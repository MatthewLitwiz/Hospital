
import java.sql.Connection;

import java.sql.*; 

public class Conn {

    Connection c;
    Statement s; // executes mysql query

    Conn(){

        try {

        Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
        c = DriverManager.getConnection("jdbc:mysql:///Hospital","root","megkhanal"); // pass in connection string
        s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
