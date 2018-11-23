package banco;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
   public static String status = "Offline"; 
   
   //editavel
   private static final String local = "localhost";
   private static final String user = "root";
   private static final String pwd = "rentmoviesdb";
   private static final String database = "12345";
   
   public static Connection get(){
       
       Connection con = null;
       String url = "jdbc:mysql://"+local+"/"+database;
       try{
           
           con = DriverManager.getConnection(url,user,pwd);
           status = "Online";
           
       }catch (Exception e){
           e.printStackTrace();
           status = "Offline";
       }
       
       return con;
       
   }
   
}
