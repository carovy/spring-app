package main.java.daos;

import main.java.dtos.ClienteDTO;
import main.java.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void deleteCliente(int idCliente) throws SQLException {
        String delete = "DELETE FROM cliente WHERE idCliente = ?";
        PreparedStatement ps = conn.prepareStatement(delete);
        ps.setInt(1, idCliente);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String update = "UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?";
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getEmail());
        ps.setInt(3, cliente.getId());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public ArrayList<Cliente> getClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String select = "SELECT * FROM cliente ORDER BY idCliente";
        PreparedStatement ps = conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            clientes.add(new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email")));
        }

        rs.close();
        ps.close();
        return clientes;
    }

    public Cliente getCliente(int idCliente) throws SQLException {
        String select = "SELECT * FROM cliente WHERE idCliente = ?";
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Cliente cliente = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email"));
        rs.close();
        ps.close();
        return cliente;
    }

    public ArrayList<ClienteDTO> mostFacturedClients(){
        String select =  "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad*p.valor) AS totalFacturado "+
                "FROM mydb.cliente c "+
                "JOIN mydb.factura f USING (idCliente) "+
                "JOIN mydb.facturaProducto fp USING (idFactura) "+
                "JOIN mydb.producto p USING (idProducto) "+
                "GROUP BY c.idCliente, c.nombre, c.email "+
                "ORDER BY totalFacturado DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            ArrayList<ClienteDTO> result = new ArrayList<>();

            while(rs.next()){
                ClienteDTO client = new ClienteDTO(rs.getInt(1), rs.getString(2), rs.getInt("totalFacturado"));
                result.add(client);
            }
            ps.close();
            conn.commit();
            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}