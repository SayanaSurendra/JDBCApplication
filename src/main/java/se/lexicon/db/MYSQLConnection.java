package se.lexicon.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MYSQLConnection {

     static Properties properties=new Properties();

    private static String url;
    private static String username;
    private static String password;

    static{
        try(Reader in = Files.newBufferedReader(Path.of("src/main/resources/database.properties"), StandardCharsets.UTF_8);){

            properties.load(in);

        } catch (IOException e) {
           e.printStackTrace();
        }
        url= properties.getProperty("jdbc.url");
        username= properties.getProperty("jdbc.username");
        password= properties.getProperty("jdbc.password");


    }


   public static  Connection getConnection(){
       try {
           return DriverManager.getConnection(url,username,password);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }

}
