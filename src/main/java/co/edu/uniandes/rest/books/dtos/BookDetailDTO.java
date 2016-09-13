/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rubby
 */
public class BookDetailDTO extends BookDTO{
   
    // relación  cero o muchos reviews 
    private List<ReviewDTO> reviews = new ArrayList<>();
    
    // relación  cero o muchos author
    private List<AuthorDTO> authors = new ArrayList<>();
    
    
    public BookDetailDTO() {
        
    }
   
    public BookDetailDTO(Long id, String name, String isbn, String image, String description, Date publishingdate, EditorialDTO editorial) {
        super(id, name, isbn, image, description,publishingdate, editorial);
       
     }
    
    public BookDetailDTO(BookDTO book) {
       super(book.getId(), book.getName(), book.getIsbn(), book.getImage(), book.getDescription(), book.getPublishingdate(), book.getEditorial());
        
       
     }
    /**
     * @return the reviews
     */
    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the authors
     */
    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Reviews \"" + getReviews().toString()
                + ", Authors \"" + getAuthors().toString();
    }
}
