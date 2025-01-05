package main.java;

import main.java.daos.ClienteDAO;
import main.java.daos.FacturaDAO;
import main.java.daos.FacturaProductoDAO;
import main.java.daos.ProductoDAO;
import main.java.dtos.ClienteDTO;
import main.java.entities.Cliente;
import main.java.entities.Factura;
import main.java.entities.FacturaProducto;
import main.java.entities.Producto;
import main.java.factory.DAOFactory;
import main.java.services.ListadoClientesService;
import main.java.services.ProductoService;
import main.java.utils.HelperMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws SQLException {
        DAOFactory dao = DAOFactory.getDAOFactoryOnlyInstance(1);
        assert dao != null;
        //ej 1
        System.out.println("Consigna 1: ");
        System.out.println("Estableciendo conexion MYSQL ");
        Connection c = HelperMySQL.getConnection();
        if (c != null) HelperMySQL.createTables();

        //ej 2
        System.out.println("Consigna 2: ");
        try {
            dao.populateDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //ej 3
        System.out.println("Consigna 3: ");
        ProductoService servicioProducto = new ProductoService(dao.getProductoDAO());
        Producto productoMayorRecaudacion = servicioProducto.getProductoMayorRecaudacion();

        System.out.println("El producto de mayor recaudacion es: " + productoMayorRecaudacion.getNombre()
                + " con ID de producto: " + productoMayorRecaudacion.getIdProducto());

        // ej 4
        ListadoClientesService servicioDos = new ListadoClientesService(dao.getClienteDAO());
        ArrayList<ClienteDTO> result = servicioDos.mostFacturedClientsList();
        System.out.println("Consigna 4: ");
        for (ClienteDTO cliente : result) {
            System.out.println("Nombre cliente: " + cliente.getNombre() + " con el id: " + cliente.getIdCliente() + " facturó: " + cliente.getTotalFacturado());
        }
    }
}
