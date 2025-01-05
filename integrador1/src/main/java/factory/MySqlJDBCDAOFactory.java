package main.java.factory;

import main.java.entities.Factura;
import main.java.entities.FacturaProducto;
import main.java.daos.ClienteDAO;
import main.java.daos.FacturaProductoDAO;
import main.java.daos.FacturaDAO;
import main.java.daos.ProductoDAO;
import main.java.entities.Cliente;
import main.java.entities.Producto;
import main.java.utils.HelperMySQL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class MySqlJDBCDAOFactory extends DAOFactory {
    private static MySqlJDBCDAOFactory instance;

    public MySqlJDBCDAOFactory() throws SQLException {

    }

    public static MySqlJDBCDAOFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlJDBCDAOFactory();
        }

        return instance;
    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return new ClienteDAO(HelperMySQL.getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() throws SQLException {
        return new FacturaDAO(HelperMySQL.getConnection());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() throws SQLException {
        return new FacturaProductoDAO(HelperMySQL.getConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return new ProductoDAO(HelperMySQL.getConnection());
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        System.out.println("Populating DB...");
        this.loadClients();
        this.loadProducts();
        this.loadBills();
        this.loadBillProducts();
    }

    private void loadBills() throws IOException {
        for(CSVRecord row : getData("facturas.csv")) {
            if(row.size() >= 1) {
                String idFactura = row.get(0);
                String idCliente = row.get(1);
                if(!idFactura.isEmpty() && !idCliente.isEmpty()) {
                    try {
                        int idF = Integer.parseInt(idFactura);
                        int idC = Integer.parseInt(idCliente);
                        Factura factura = new Factura(idF, idC);
                        this.getFacturaDAO().addFactura(factura);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("facturas insertadas");
    }

    private void loadBillProducts() throws IOException{
        for(CSVRecord row : getData("facturas-productos.csv")) {
            if(row.size() >= 2) {
                String idFactura = row.get(0);
                String idProducto = row.get(1);
                if(!idFactura.isEmpty() && !idProducto.isEmpty()) {
                    try {
                        int idF = Integer.parseInt(idFactura);
                        int idP = Integer.parseInt(idProducto);
                        FacturaProducto facturaProducto = new FacturaProducto(idF, idP, Integer.parseInt(row.get(2)));
                        this.getFacturaProductoDAO().addFacturaProducto(facturaProducto);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("facturas-productos insertados");
    }

    private void loadProducts() throws IOException {
        for(CSVRecord row : getData("productos.csv")) {
            if(row.size() >= 2) {
                String idString = row.get(0);
                if(!idString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        Producto producto = new Producto(id, row.get(1), Float.parseFloat(row.get(2)));
                        this.getProductoDAO().addProducto(producto);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("productos insertados");
    }

    private void loadClients() throws IOException {
        for(CSVRecord row : getData("clientes.csv")) {
            if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                String idString = row.get(0);
                if(!idString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        Cliente cliente = new Cliente(id, row.get(1), row.get(2));
                        this.getClienteDAO().addCliente(cliente);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("clientes insertados");
    }
}
