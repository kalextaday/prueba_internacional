/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.application.util;

import ec.fin.internacional.prueba.application.dto.DtoOutTransaction;
import ec.fin.internacional.prueba.domain.EnumTypeTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author PC
 */
public class CalculateUtil {
    public static BigDecimal calculateBalance(List<DtoOutTransaction> transactions) {
        BigDecimal balance;

        Double balanceCredito = transactions
                .stream()
                .filter(trans-> EnumTypeTransaction.CREDITO.getName().equals(trans.getTipoMovimiento()))
                .mapToDouble(mov->mov.getValor().doubleValue())
                .sum();

        Double balanceDebito= transactions
                .stream()
                .filter(trans-> EnumTypeTransaction.DEBITO.getName().equals(trans.getTipoMovimiento()))
                .mapToDouble(mov->mov.getValor().doubleValue())
                .sum();


        balance = BigDecimal.valueOf(balanceCredito).subtract(BigDecimal.valueOf(balanceDebito));
        return balance;
    }
}
