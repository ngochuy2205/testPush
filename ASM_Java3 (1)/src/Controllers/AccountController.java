
package Controllers;

import Models.Account;
import java.sql.*;

/**
 *
 * @author Ps21313_NguyenNgocHuy
 */
public class AccountController {

    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=CSDL_JAVA3;encrypt=true;trustServerCertificate=true;";
    private static String username = "ngochuy_ADM";
    private static String password = "";

    public static Account getAccount(String user, String pass) {
        try {
            Account acc = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                acc = new Account(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
            return acc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Account getRememberAccount() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM USERS WHERE STATUS=1";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
            System.out.println("Connect succesfull");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi select account được ghi nhớ");
            return null;
        }
    }

    public static void clearRememberAccount() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE USERS SET STATUS=0";
            Statement stm = con.createStatement();
            stm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public static void rememberAccount(String user){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE USERS SET STATUS=1 WHERE USERNAME=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, user);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
     }
}
