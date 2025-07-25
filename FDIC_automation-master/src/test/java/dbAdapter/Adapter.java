package dbAdapter;

import models.FailureBank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Adapter {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://104.237.13.60/DATABASE";

    //  Database credentials
    private static final String USER = "USER_NAME";
    private static final String PASS = "PASSWORD";

    private static Connection conn = null;
    private static Statement stmt = null;

    public static List<FailureBank> getAllFailureBanks() {
        List<FailureBank> failureBanks = new ArrayList<>();
        String query = "SELECT * from failure banks a";

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                FailureBank failureBank = new FailureBank(id, name);
                failureBanks.add(failureBank);
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return failureBanks;
    }//end main

}//end JDBC
