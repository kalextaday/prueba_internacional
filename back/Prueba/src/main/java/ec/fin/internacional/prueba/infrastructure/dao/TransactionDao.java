/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.infrastructure.dao;

import ec.fin.internacional.prueba.domain.Movimiento;
import ec.fin.internacional.prueba.domain.Persona;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author PC
 */
public class TransactionDao extends DAOGenerico<Movimiento, Integer>{
    
    private static final Logger LOG = Logger.getLogger(PersonDao.class.getName());
    
    public TransactionDao() {
        super(Movimiento.class);
    }
    
    public List<Movimiento> findMovimientosByPersonaIs(Persona persona) {
        List<Movimiento> transactions = null;

        try (Session sesion = HibernateFactory.abrirSession()) {
            transactions = sesion.createQuery("from Movimiento "
                    + "where persona=:_persona ")
                    .setParameter("_persona", persona)
                    .list();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        } finally {
            HibernateFactory.cerrar(session);
        }

        return transactions;
    }
    
}
