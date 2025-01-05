package main.java.services;

import main.java.daos.ProductoDAO;
import main.java.entities.Producto;

import java.sql.SQLException;

public class ProductoService {

    private ProductoDAO productoDAO;

    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public Producto getProductoMayorRecaudacion() throws SQLException {
        return productoDAO.getHighestIncomeProduct();
    }

}
