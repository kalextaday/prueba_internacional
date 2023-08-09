package ec.fin.internacional.prueba.infrastructure.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @param <T>
 * @param <ID>
 */
public abstract class DAOGenerico<T, ID extends Serializable> {

    private static final Logger LOG = Logger.getLogger(DAOGenerico.class.getName());

    Session session;

    private Transaction tx;

    private final Class<T> clase;
    private String dataBase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
    }

    public DAOGenerico(Class<T> clase, String dataBase) {
        this.clase = clase;
        this.dataBase = dataBase;
    }

    protected T save(T entidad) {

        try {
            startOperation();

            if (session != null) {
                session.save(entidad);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }

    protected boolean saveOrUpdate(T entidad) {
        boolean guardado = false;
        try {
            startOperation();
            if (session != null) {
                session.saveOrUpdate(entidad);
                tx.commit();
                guardado = true;
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return guardado;
    }

    protected T merge(T entidad) {

        try {
            startOperation();
            if (session != null) {
                session.merge(entidad);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }

    protected boolean delete(T entidad) {
        boolean borrado = false;
        try {
            startOperation();
            if (session != null) {
                session.delete(entidad);
                tx.commit();
                borrado = true;
            }
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return borrado;
    }

    protected T find(ID id) {
        T entidad = null;
        try {
            startOperation();
            if (session != null) {
                entidad = (T) session.get(clase, id);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }

    protected List<T> findAll() {
        List<T> lista = null;
        try {
            startOperation();
            if (session != null) {
                lista = session.createQuery("from " + clase.getName())
                        .list();
                tx.commit();
            }

        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return lista;
    }

    protected void handleException(HibernateException e) {
        HibernateFactory.rollback(tx);
        LOG.log(Level.SEVERE, "Se hará un rollback, Clase:{0} Error:{1}", new Object[]{clase.getCanonicalName(), e.getMessage()});

    }

    protected void startOperation() throws HibernateException {
        session = HibernateFactory.abrirSession();
        if (session != null) {
            tx = session.beginTransaction();
        } else {
            LOG.log(Level.SEVERE, null, "No existe conexión a la base de datos");
        }
    }

    public String getDataBase() {
        return dataBase;
    }

}
