/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoOutPerson {
    private String nombre;

    private String apellido;

    private Integer edad;

    private String cargo;

    private String cuenta;

    private String codigoInterno;

    private List<DtoOutTransaction> movimientos;

    private BigDecimal saldo;
}
