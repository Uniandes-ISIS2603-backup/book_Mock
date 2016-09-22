package co.edu.uniandes.rest.books.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReviewDTO {

    private Long id;

    private String name;

    private String source;

    private String description;

    /**
     * Constructor por defecto
     */
    public ReviewDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la book
     * @param name nombre de la book
     */
    public ReviewDTO(Long id, String name, String source, String description) {
        super();
        this.id = id;
        this.name = name;
        this.source = source;
        this.description = description;
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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
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

}
