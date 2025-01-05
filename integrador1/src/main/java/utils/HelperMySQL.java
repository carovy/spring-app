package main.java.utils;

import main.java.entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL { //esta clase solo deberia tener la responsabilidad de establecer la conexion a la db
    //y crear el esquema de tablas como mucho, el resto de responsabilidades es para MySqlDAOFactory
    private static Connection conn = null;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String uri = "jdbc:mysql://localhost:3306/mydb";

    public HelperMySQL() { //la conexion no deberia poder ser instanciada desde fuera tantas veces como se quiera
    }

    public static Connection getConnection(){
        if (conn == null) conn = initConn();
        return conn;
    }

    private static Connection initConn(){
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTables() throws SQLException {
        String tablaCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                                "idCliente int PRIMARY KEY, " +
                                "nombre Varchar(500)," +
                                "email Varchar(150))";
        conn.prepareStatement(tablaCliente).execute();

        String tablaFactura = "CREATE TABLE IF NOT EXISTS factura("+
                                "idFactura int PRIMARY KEY,"+
                                "idCliente int," +
                                "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";
        conn.prepareStatement(tablaFactura).execute();

        String tablaProducto = "CREATE TABLE IF NOT EXISTS producto(" +
                                "idProducto int PRIMARY KEY," +
                                "nombre Varchar(45)," +
                                "valor Float)";

        conn.prepareStatement(tablaProducto).execute();

        String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS facturaProducto("+
                                        "idFactura int,"+
                                        "idProducto int,"+
                                        "cantidad int," +
                                        "PRIMARY KEY (idFactura, idProducto)," +
                                        "FOREIGN KEY (idFactura) REFERENCES factura(idFactura)," +
                                        "FOREIGN KEY(idProducto) REFERENCES producto(idProducto))";
        conn.prepareStatement(tablaFacturaProducto).execute();

        conn.commit();
    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}