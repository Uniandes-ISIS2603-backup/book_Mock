/*
 * BookDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.books.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 */
public class BookDTO {

    private Long id;
    private String name;
    private String isbn;
    private String image;
    private String description;
    private Date publishingdate;

    // Relación a una editorial
    private EditorialDTO editorial;

 
    /**
     * Constructor por defecto
     */
    public BookDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la book
     * @param name nombre de la book
     */
    public BookDTO(Long id, String name, String isbn, String image, String description, Date publishingdate, EditorialDTO editorial) {
        super();
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.image = image;
        this.description = description;
        this.publishingdate = publishingdate;
        this.editorial = editorial;
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
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the publishingdate
     */
    public Date getPublishingdate() {
        return publishingdate;
    }

    /**
     * @param publishingdate the publishingdate to set
     */
    public void setPublishingdate(Date publishingdate) {
        this.publishingdate = publishingdate;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId()
                + ", name : \"" + getName()
                + ", description : \"" + getDescription()
                + ", image : \"" + getImage()
                + ", isbn : \"" + getIsbn()
                + ", editorial : \"" + getEditorial().getName()
                + ", publishingdate: \"" + getPublishingdate()
                + "\" }";
    }

    /**
     * @return the editorial
     */
    public EditorialDTO getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

}
