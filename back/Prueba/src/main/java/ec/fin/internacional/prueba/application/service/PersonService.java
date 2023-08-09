/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.application.service;

import ec.fin.internacional.prueba.application.dto.DtoOutPerson;
import ec.fin.internacional.prueba.application.dto.DtoOutTransaction;
import ec.fin.internacional.prueba.application.util.CalculateUtil;
import ec.fin.internacional.prueba.domain.EnumTypeTransaction;
import ec.fin.internacional.prueba.domain.Persona;
import ec.fin.internacional.prueba.infrastructure.dao.PersonDao;
import ec.fin.internacional.prueba.infrastructure.dao.TransactionDao;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class PersonService {
    private PersonDao personDao;

    private TransactionDao transactionDao;

    public PersonService() {
        this.personDao = new PersonDao();
        this.transactionDao = new TransactionDao();
    }

    public DtoOutPerson getPersonByIdentification(String identification) {
        try{
            Persona person = personDao.findPersonaByCodigoInternoIs(identification);
            DtoOutPerson responsePerson = new DtoOutPerson();

            responsePerson.setNombre(person.getNombre());
            responsePerson.setApellido(person.getApellido());
            responsePerson.setEdad(person.getEdad());
            responsePerson.setCodigoInterno(person.getCodigoInterno());
            responsePerson.setCuenta(person.getCuenta());
            responsePerson.setCargo(person.getCargo());
            responsePerson.setMovimientos(this.getTransactions(person));

            BigDecimal balance = CalculateUtil.calculateBalance(responsePerson.getMovimientos());

            responsePerson.setSaldo(balance);

            return responsePerson;
        }catch(Exception ex){
            return null;
        }
    }

    public List<DtoOutTransaction> getTransactions(Persona person){

        try{
            return transactionDao.findMovimientosByPersonaIs(person)
                    .stream()
                    .map(mov->{
                        DtoOutTransaction responseTransaction = new DtoOutTransaction();
                        responseTransaction.setMotivo(mov.getMotivo());
                        responseTransaction.setValor(mov.getValor());

                        if(EnumTypeTransaction.DEBITO.getTypeTrans().equals(mov.getTipoMovimiento())) {
                            responseTransaction.setTipoMovimiento(EnumTypeTransaction.DEBITO.getName());
                        } else if(EnumTypeTransaction.CREDITO.getTypeTrans().equals(mov.getTipoMovimiento())) {
                            responseTransaction.setTipoMovimiento(EnumTypeTransaction.CREDITO.getName());
                        }
                        return responseTransaction;
                    }).collect(Collectors.toList());
        }catch(Exception ex){
            return Collections.emptyList();
        }
    }
}
