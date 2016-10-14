/*
 * AuthorDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.books.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 */
public class AuthorDTO {

    private Long id;
    private String name;
    private Date birthDate;

    /**
     * Constructor por defecto
     */
    public AuthorDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la author
     * @param name nombre de la author
     */
    public AuthorDTO(Long id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", name : \"" + getName() + ", birthdate : \"" + getBirthDate() + "\" }";
    }
}
