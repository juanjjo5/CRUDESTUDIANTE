
package Modelo;

import java.sql.*;

public class Conexion {
  String url= "jdbc:mysql://localhost:3306/estudiante_db";
  String user="root";
  String pass="jacobluis7.";
  Connection con;
  
  
  public Connection getConnnection(){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection)DriverManager.getConnection(url,user,pass);
      }catch(Exception e){
          System.out.println(e);
      }
      return con;
  }   
}
