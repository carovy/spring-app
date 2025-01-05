package main.java.org.example.factory;

import main.java.org.example.repositories.AlumnoCarreraRepository;
import main.java.org.example.repositories.AlumnoCarreraRepositoryImpl;
import main.java.org.example.repositories.AlumnoRepository;
import main.java.org.example.repositories.CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class DaoFactory  {

    public static DaoFactory getInstance(int whichFactory) {
        switch (whichFactory) {
            case 1:
                return MySqlDaoFactory.getInstance();
            default:
                return null;
        }
    }

    public abstract AlumnoRepository getAlumnoRepository();
    public abstract CarreraRepository getCarreraRepository();
    public abstract AlumnoCarreraRepository getAlumnoCarreraRepository();

}