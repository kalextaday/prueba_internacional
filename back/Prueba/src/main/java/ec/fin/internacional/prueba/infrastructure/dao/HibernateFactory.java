package ec.fin.internacional.prueba.infrastructure.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author PC
 */
public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private static final Logger LOGGER = Logger.getLogger(HibernateFactory.class.getName());

    /**
     * Construye una nueva Singleton SessionFactory
     *
     * @return
     * @throws HibernateException
     */
    public static SessionFactory construirSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            cerrarFactory();
        }
        return configurarSessionFactory();
    }

    /**
     * Crea una SessionFactory, si esta no ha sido creada.
     *
     * @return
     */
    public static SessionFactory construirSiNecesita() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        try {
            return configurarSessionFactory();
        } catch (HibernateException e) {
            LOGGER.log(Level.SEVERE, "Error:".concat(e.getMessage()));
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session abrirSession() throws HibernateException {
        construirSiNecesita();
        return sessionFactory.openSession();
    }

    public static void cerrarFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException exception) {
                LOGGER.log(Level.SEVERE, "No se pudo cerrar SessionFactory:".concat(exception.getMessage()));
            }
        }
    }

    public static void cerrar(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException exception) {
                LOGGER.log(Level.SEVERE, "No se pudo cerrar Session:".concat(exception.getMessage()));
            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException exception) {
            LOGGER.log(Level.SEVERE, "No se pudo hacer rollback Transaction:".concat(exception.getMessage()));
        }
    }

    /**
     *
     * @return @throws HibernateException
     */
    private static SessionFactory configurarSessionFactory() throws HibernateException {

        sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

}
