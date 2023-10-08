package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connectionbd {

    private static String url="jdbc:postgresql://localhost:5433/bank";
    private static String userName="postgres";
    private static String password="kamal123";
    private static Connection cnx=null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            cnx= DriverManager.getConnection(url,userName, password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConn(){
        return cnx;
    }




}
