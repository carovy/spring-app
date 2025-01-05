package main.java.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.entities.FacturaProducto;

public class FacturaProductoDAO {
    private Connection conn;

    public FacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void addFacturaProducto(FacturaProducto fp) throws SQLException {
        String insert = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, fp.getIdFactura());
        ps.setInt(2, fp.getIdProducto());
        ps.setInt(3, fp.getCantidad());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void deleteFacturaProducto(int idFactura, int idProducto) throws SQLException {
        String delete = "DELETE FROM facturaproducto WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(delete);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void updateFacturaProducto(FacturaProducto facturaProducto) throws SQLException {
        String update = "UPDATE facturaproducto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1, facturaProducto.getCantidad());
        ps.setInt(2, facturaProducto.getIdFactura());
        ps.setInt(3, facturaProducto.getIdProducto());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public ArrayList<FacturaProducto> getFacturaProductoList() throws SQLException {
        ArrayList<FacturaProducto> facturaProductoList = new ArrayList<>();
        String select = "SELECT * FROM facturaproducto ORDER BY idFactura";
        PreparedStatement ps = conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            facturaProductoList.add(new FacturaProducto(rs.getInt("idFactura"), rs.getInt("idProducto"), rs.getInt("cantidad")));
        }

        rs.close();
        ps.close();
        return facturaProductoList;
    }

    public FacturaProducto getFacturaProducto(int idFactura, int idProducto) throws SQLException {
        String select = "SELECT * FROM facturaproducto WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ResultSet rs = ps.executeQuery();
        rs.next();
        FacturaProducto facturaProducto = new FacturaProducto(rs.getInt("idFactura"), rs.getInt("idProducto"), rs.getInt("cantidad"));
        rs.close();
        ps.close();
        return facturaProducto;
    }
}