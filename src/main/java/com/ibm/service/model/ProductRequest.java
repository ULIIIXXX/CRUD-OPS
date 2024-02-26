package com.ibm.service.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * record en java que implementa un constructor por defecto obligatoriamente, no se
 * incluyen metodos ni sobrecarga de constructores en esta ocasion
 * @param name para definir el nombre de un articulo
 * @param price para definir el precio de un articulo
 */
public record ProductRequest(@NotNull String name,@NotNull BigDecimal price) {
}
