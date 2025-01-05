package main.java.org.example.factory;

import main.java.org.example.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance;
    private EntityManagerFactory emf;
    private EntityManager em;

    public MySqlDaoFactory() {
        this.emf = Persistence.createEntityManagerFactory("Example");
        this.em = emf.createEntityManager();
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }

        return instance;
    }

    @Override
    public AlumnoRepository getAlumnoRepository() {
        return new AlumnoRepositoryImpl(this.em);
    }

    @Override
    public CarreraRepository getCarreraRepository() {
        return new CarreraRepositoryImpl(this.em);
    }

    @Override
    public AlumnoCarreraRepository getAlumnoCarreraRepository() {
        return new AlumnoCarreraRepositoryImpl(this.em);
    }
}