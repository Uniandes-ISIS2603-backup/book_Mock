/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.mocks;

import co.edu.uniandes.rest.books.api.IBookLogicMock;
import co.edu.uniandes.rest.books.api.IReviewLogicMock;
import co.edu.uniandes.rest.books.dtos.BookDetailDTO;
import co.edu.uniandes.rest.books.dtos.ReviewDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rcasalla
 */
@Stateless
public class ReviewLogicMock implements IReviewLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ReviewLogicMock.class.getName());

    @Inject
    private IBookLogicMock bookLogic;

    /**
     * Constructor.
     */
    protected ReviewLogicMock() {}


    /**
     * Obtiene el listado de reviews.
     *
     * @param idBook
     * @return lista de reviews de un book
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    @Override
    public List<ReviewDTO> getReviews(Long idBook) throws BookLogicException {
        BookDetailDTO book = bookLogic.getBook(idBook);
        if (book.getReviews() == null) {
            logger.log(Level.SEVERE, "Error interno: lista de reviews no existe. Para el book {0}", idBook);
            throw new BookLogicException("Error interno: lista de reviews no existe.");
        }
        logger.log(Level.INFO, "retornando todas las reviews del book {0}", idBook);
        return book.getReviews();
    }

    /**
     * Obtiene una getReview
     *
     * @param idBook
     * @param id identificador de la getReview
     * @return getReview encontrada
     * @throws BookLogicException cuando la getReview no existe
     */
    @Override
    public ReviewDTO getReview(Long idBook, Long id) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solicitud de getReview con id {0}", id);
        BookDetailDTO book = bookLogic.getBook(idBook);
        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : book.getReviews()) {
            if (Objects.equals(getReview.getId(), id)) {
                logger.log(Level.INFO, "retornando getReview {0}", getReview);
                return getReview;
            }
        }

        // si no encuentra la getReview
        logger.severe("No existe getReview con ese id");
        throw new BookLogicException("No existe getReview con ese id");
    }

    /**
     * Agrega una getReview a la lista.
     *
     * @param idBook
     * @param newReview getReview a adicionar
     * @throws BookLogicException cuando ya existe una getReview con el id
     * suministrado
     * @return getReview agregada
     */
    @Override
    public ReviewDTO createReview(Long idBook, ReviewDTO newReview) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solicitud de agregar createReview {0}", newReview.toString());
        logger.log(Level.INFO, "recibiendo solicitud de agregar Review to Book {0}", idBook);

        BookDetailDTO book = bookLogic.getBook(idBook);

        List<ReviewDTO> reviewsBook = book.getReviews();

        // la nueva getReview tiene id ?
        if (newReview.getId() != null) {
            // busca la getReview con el id suministrado
            for (ReviewDTO getReview : reviewsBook) {
                // si existe una getReview con ese id
                if (Objects.equals(getReview.getId(), newReview.getId())) {
                    logger.severe("Ya existe una getReview con ese id");
                    throw new BookLogicException("Ya existe una getReview con ese id");
                }
                if (Objects.equals(getReview.getName(), newReview.getName())) {
                    logger.severe("Ya existe una getReview con ese nombre para el book");
                    throw new BookLogicException("Ya existe una getReview con ese nombre");
                }
            }
            // la nueva getReview no tiene id ? 
        } else {
            for (ReviewDTO getReview : reviewsBook) {
                // si existe una getReview con ese id
                if (Objects.equals(getReview.getName(), newReview.getName())) {
                    logger.severe("Ya existe una getReview con ese nombre para el book");
                    throw new BookLogicException("Ya existe una getReview con ese nombre");
                }
            }
            // genera un id para la getReview
            logger.info("Generando id para la nueva getReview");
            long newId = 1;
            for (ReviewDTO getReview : reviewsBook) {
                if (newId <= getReview.getId()) {
                    newId = getReview.getId() + 1;
                }
            }
            newReview.setId(newId);
        }

        // agrega la getReview a la lista de reviews del book
        reviewsBook.add(newReview);
        book.setReviews(reviewsBook);

        logger.log(Level.INFO, "Agregando newReview {0}", newReview.toString());
        logger.log(Level.INFO, "Se agrego review con id {0}", newReview.getId());
        return newReview;
    }

    /**
     * Actualiza los datos de una getReview
     *
     * @param idBook
     * @param id
     * @param updatedReview
     * @return datos de la getReview modificada
     * @throws BookLogicException cuando no existe una getReview con el id
     * suministrado
     */
    @Override
    public ReviewDTO updateReview(Long idBook, Long id, ReviewDTO updatedReview) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solictud de modificar getReview {0}", updatedReview);
        logger.log(Level.INFO, "recibiendo solictud de modificar id Review {0}", id);

        BookDetailDTO book = bookLogic.getBook(idBook);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : book.getReviews()) {
            if (Objects.equals(getReview.getId(), id)) {
                // modifica la getReview
                getReview.setId(updatedReview.getId());
                getReview.setName(updatedReview.getName());
                getReview.setSource(updatedReview.getSource());
                getReview.setDescription(updatedReview.getDescription());
                // retorna la getReview modificada
                logger.log(Level.INFO, "Modificando getReview {0}", getReview);
                return getReview;
            }
        }

        // no encontró la getReview con ese id ?
        logger.severe("No existe una getReview con ese id");
        throw new BookLogicException("No existe una getReview con ese id");
    }

    /**
     * Elimina los datos de una getReview
     *
     * @param idBook
     * @param id identificador de la getReview a eliminar
     * @throws BookLogicException cuando no existe una getReview con el id
     * suministrado
     */
    @Override
    public void deleteReview(Long idBook, Long id) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solictud de eliminar getReview con id {0}", id);

        BookDetailDTO book = bookLogic.getBook(idBook);
        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : book.getReviews()) {
            if (Objects.equals(getReview.getId(), id)) {
                // elimina la getReview
                logger.log(Level.INFO, "eliminando getReview {0}", getReview);
                book.getReviews().remove(getReview);
                return;
            }
        }

        // no encontró la getReview con ese id ?
        logger.severe("No existe una getReview con ese id");
        throw new BookLogicException("No existe una getReview con ese id");
    }
}
