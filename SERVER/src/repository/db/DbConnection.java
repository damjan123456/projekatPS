/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author damja
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
public class DbConnection {
    private static DbConnection instance;
    private Connection connection= null;
    private DbConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (connection == null || connection.isClosed()){
                String url = konfiguracija.Konfiguracija.getIntanca().getProperty("url");
                String username = Konfiguracija.getIntanca().getProperty("username");
                String password = Konfiguracija.getIntanca().getProperty("password");
                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() {
        if (instance == null)
            instance = new DbConnection();
        return instance;
    }
    
 
}
