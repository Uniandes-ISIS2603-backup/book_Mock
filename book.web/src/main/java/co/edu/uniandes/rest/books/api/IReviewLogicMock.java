/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.api;

import co.edu.uniandes.rest.books.dtos.ReviewDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import java.util.List;

/**
 *
 * @author asistente
 */
public interface IReviewLogicMock {
    public List<ReviewDTO> getReviews(Long idBook) throws BookLogicException;
    public ReviewDTO getReview(Long idBook, Long id) throws BookLogicException;
    public ReviewDTO createReview(Long idBook, ReviewDTO newReview) throws BookLogicException;
    public ReviewDTO updateReview(Long idBook, Long id, ReviewDTO updatedReview) throws BookLogicException;
    public void deleteReview(Long idBook, Long id) throws BookLogicException;
}
