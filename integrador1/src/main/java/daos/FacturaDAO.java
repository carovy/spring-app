package main.java.daos;

import main.java.entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacturaDAO {
    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void addFactura(Factura fac) throws SQLException {
        String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, fac.getIdFactura());
        ps.setInt(2, fac.getIdCliente());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void deleteFactura(int idFactura) throws SQLException {
        String delete = "DELETE FROM factura WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(delete);
        ps.setInt(1, idFactura);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void updateFactura(Factura factura) throws SQLException {
        String update = "UPDATE factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1, factura.getIdCliente());
        ps.setInt(2, factura.getIdFactura());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public ArrayList<Factura> getFacturas() throws SQLException {
        ArrayList<Factura> facturas = new ArrayList<>();
        String select = "SELECT * FROM factura ORDER BY idFactura";
        PreparedStatement ps = conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            facturas.add(new Factura(rs.getInt("idFactura"), rs.getInt("idCliente")));
        }

        rs.close();
        ps.close();
        return facturas;
    }

    public Factura getFactura(int idFactura) throws SQLException {
        String select = "SELECT * FROM factura WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setInt(1, idFactura);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Factura factura = new Factura(rs.getInt("idFactura"), rs.getInt("idCliente"));
        rs.close();
        ps.close();
        return factura;
    }
}