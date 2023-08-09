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
public enum EnumTypeTransaction {

    DEBITO("DEB","DEBITO"),
    CREDITO("CRE","CREDITO");

    private String typeTrans;
    private String name;

    EnumTypeTransaction(String typeTrans, String name) {
        this.typeTrans = typeTrans;
        this.name = name;
    }

    public String getTypeTrans() {
        return typeTrans;
    }

    public String getName() {
        return name;
    }
}