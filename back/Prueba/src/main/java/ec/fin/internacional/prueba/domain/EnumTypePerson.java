/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.domain;

/**
 *
 * @author PC
 */
public enum EnumTypePerson {
    CLIENTE("CLI"),
    EMPLEADO("EMP");

    private String typePerson;

    EnumTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }

    public String getTypePerson() {
        return typePerson;
    }
}
