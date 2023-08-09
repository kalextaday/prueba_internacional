/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.infrastructure.dao;

import ec.fin.internacional.prueba.domain.Persona;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author PC
 */
public class PersonDao extends DAOGenerico<Persona, Integer>{
    
    private static final Logger LOG = Logger.getLogger(PersonDao.class.getName());
    
    public PersonDao() {
        super(Persona.class);
    }
    
    public Persona findPersonaByCodigoInternoIs(String codigoInterno) {
        Persona person = null;

        try (Session sesion = HibernateFactory.abrirSession()) {
            person = (Persona) sesion.createQuery("from Persona p "
                    + "where p.codigoInterno=:_codigoInterno ")
                    .setParameter("_codigoInterno", codigoInterno)
                    .uniqueResult();

        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        } finally {
            HibernateFactory.cerrar(session);
        }

        return person;
    }
}
