/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 *
 * @author Lorenzo1
 */
public class CreateConection {
    static Properties config = new Properties();
    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;
    public CreateConection (){
        String path = "C:\\Users\\Lorenzo1\\OneDrive\\Escritorio\\JAva\\ProyectoFinal\\src\\Conexion\\db_config.properties";
        InputStream archivoEntrada = null;
        try{
            archivoEntrada = Files.newInputStream(Paths.get(path));
            config.load(archivoEntrada);
            archivoEntrada.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            try{
               archivoEntrada.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        loadProperties();
    }
     // Método que asigna los valores del archivo de configuración a las variables de la clase
    public void loadProperties(){
        hostname = config.getProperty("hostname");
        port  = config.getProperty("port");
        database = config.getProperty("database");
        username = config.getProperty("username");
        password = config.getProperty("password");
    }
    
    // Método que crea la conexión a la base de datos PostgreSQL
    public Connection getConection() throws SQLException {
        Connection conn = null;
        
        // Construcción de la URL de conexión con los parámetros cargados
        String jdbcUrl = "jdbc:postgresql://" + this.hostname + ":" +
                         this.port + "/" + this.database;
        
        // Se conecta a la BD usando DriverManager y las credenciales
        conn = DriverManager.getConnection(jdbcUrl, username, password);
        
        // Mensaje de confirmación
        System.out.println("Conexion establecida");
        
        // Retorna el objeto Connection para poder usarlo en consultas SQL
        return conn;
    }   
    
}
