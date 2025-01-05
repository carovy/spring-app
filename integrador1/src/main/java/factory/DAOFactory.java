package main.java.factory;

import main.java.daos.ClienteDAO;
import main.java.daos.FacturaDAO;
import main.java.daos.FacturaProductoDAO;
import main.java.daos.ProductoDAO;
import main.java.utils.HelperMySQL;

import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;

    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract FacturaDAO getFacturaDAO() throws SQLException;
    public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
    public abstract ProductoDAO getProductoDAO() throws SQLException;
    public abstract void populateDB() throws Exception;

    public static DAOFactory getDAOFactoryOnlyInstance(int whichFactory) throws SQLException {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySqlJDBCDAOFactory.getInstance();
            }
            default: return null;
        }
    }

}
