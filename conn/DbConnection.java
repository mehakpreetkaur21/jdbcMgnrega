/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author mehak
 */
public class DbConnection {
    private static DbConnection DAOOBJ=null;
 private static final String URL="jdbc:mysql://localhost:3306/MgnregaApp";
 private static final String USERNAME="root";
 private static final String PASSWORD="root";
 
 private DbConnection()
 {
     try{
         Class.forName("co.mysql.cj.jdbc.Driver");
     }catch(ClassNotFoundException e){
     }
 }
 public static DbConnection getInstance(){
     if(DAOOBJ==null){
         DAOOBJ=new DbConnection();
     }
     return DAOOBJ;
 }
 public  Connection getConnection() throws SQLException{
     Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
     return con;
 }
}
