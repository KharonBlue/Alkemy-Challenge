
package com.alkemy.disney.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Kharon
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistroRequest {
    private final String nombre;
    private final String correo;
    private final String clave;

}
