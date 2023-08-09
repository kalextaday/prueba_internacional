/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.internacional.prueba.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="persona")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @Column(name="id_persona", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    private String nombre;

    private String apellido;

    private Integer edad;

    private String cargo;

    private String cuenta;

    private String codigoInterno;

    private String tipoPersona;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="persona")
    private Set<Movimiento> movimientos;
}
