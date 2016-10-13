/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.mocks;

import co.edu.uniandes.rest.books.api.IBookLogicMock;
import co.edu.uniandes.rest.books.api.IReviewLogicMock;
import co.edu.uniandes.rest.books.dtos.BookDTO;
import co.edu.uniandes.rest.books.dtos.BookDetailDTO;
import co.edu.uniandes.rest.books.dtos.ReviewDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.ArrayList;
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

    // listado de reviews
    private static ArrayList<ReviewDTO> REVIEWS;
    
    @Inject
    private IBookLogicMock bookLogic;
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    protected ReviewLogicMock() {
        getInstance();
    }

    private synchronized static void createInstance() {
        if (REVIEWS == null) {
            REVIEWS = new ArrayList<>();
            REVIEWS.add(new ReviewDTO(1L, " ", "El Tiempo", "Maravillosa descripción de lo ilógico"));
            REVIEWS.add(new ReviewDTO(2L, " ", "El Espectador", "Fantástica obra de la realidad imposible"));
            REVIEWS.add(new ReviewDTO(3L, " ", "El Espectador", "Buen libro para todos"));

        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de reviews");
        logger.info("reviews" + REVIEWS);
    }

    public static ArrayList<ReviewDTO> getInstance() {
        if (REVIEWS == null) {
            createInstance();
        }
        return REVIEWS;
    }

    /**
     * Obtiene el listado de reviews.
     *
     * @return lista de reviews
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    @Override
    public List<ReviewDTO> getReviews(Long idBook) throws BookLogicException {
        BookDetailDTO book = bookLogic.getBook(idBook);
        if (book.getReviews() == null) {
            logger.severe("Error interno: lista de reviews no existe. Para el book "+idBook);
            throw new BookLogicException("Error interno: lista de reviews no existe.");
        }

        logger.log(Level.INFO, "retornando todas las reviews del book {0}", idBook);
        return book.getReviews();
    }

    /**
     * Obtiene una getReview
     *
     * @param id identificador de la getReview
     * @return getReview encontrada
     * @throws BookLogicException cuando la getReview no existe
     */
    @Override
    public ReviewDTO getReview(Long idBook, Long id) throws BookLogicException {
        logger.info("recibiendo solicitud de getReview con id " + id);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : REVIEWS) {
            if (Objects.equals(getReview.getId(), id)) {
                logger.info("retornando getReview " + getReview);
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
     * @param newReview getReview a adicionar
     * @throws BookLogicException cuando ya existe una getReview con el id
     * suministrado
     * @return getReview agregada
     */
    @Override
    public ReviewDTO createReview(Long idBook, ReviewDTO newReview) throws BookLogicException {
        logger.info("recibiendo solicitud de agregar createReview " + newReview);
        logger.info("recibiendo solicitud de agregar Review to Book " + idBook);

        // la nueva getReview tiene id ?
        if (newReview.getId() != null) {
            // busca la getReview con el id suministrado
            for (ReviewDTO getReview : REVIEWS) {
                // si existe una getReview con ese id
                if (Objects.equals(getReview.getId(), newReview.getId())) {
                    logger.severe("Ya existe una getReview con ese id");
                    throw new BookLogicException("Ya existe una getReview con ese id");
                };
                if (Objects.equals(getReview.getName(), newReview.getName())) {
                    logger.severe("Ya existe una getReview con ese nombre");
                    throw new BookLogicException("Ya existe una getReview con ese nombre");
                }

            }

            // la nueva getReview no tiene id ? 
        } else {
            for (ReviewDTO getReview : REVIEWS) {
                // si existe una getReview con ese id

                if (Objects.equals(getReview.getName(), newReview.getName())) {
                    logger.severe("Ya existe una getReview con ese nombre");
                    throw new BookLogicException("Ya existe una getReview con ese nombre");
                }

            }
            // genera un id para la getReview
            logger.info("Generando id para la nueva getReview");
            long newId = 1;
            for (ReviewDTO getReview : REVIEWS) {
                if (newId <= getReview.getId()) {
                    newId = getReview.getId() + 1;
                }
            }
            newReview.setId(newId);
        }

        // agrega la getReview
        BookDetailDTO book = bookLogic.getBook(idBook);
        List<ReviewDTO> reviewsBook = book.getReviews();
        
        reviewsBook.add(newReview);
        book.setReviews(reviewsBook);
        
        logger.info("agregando newReview " + newReview);
        return newReview;
    }

    /**
     * Actualiza los datos de una getReview
     *
     * @param id identificador de la getReview a modificar
     * @param getReview getReview a modificar
     * @return datos de la getReview modificada
     * @throws BookLogicException cuando no existe una getReview con el id
     * suministrado
     */
    @Override
    public ReviewDTO updateReview(Long idBook, Long id, ReviewDTO updatedReview) throws BookLogicException {
        logger.info("recibiendo solictud de modificar getReview " + updatedReview);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : REVIEWS) {
            if (Objects.equals(getReview.getId(), id)) {

                // modifica la getReview
                getReview.setId(updatedReview.getId());
                getReview.setName(updatedReview.getName());

                // retorna la getReview modificada
                logger.info("Modificando getReview " + getReview);
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
     * @param id identificador de la getReview a eliminar
     * @throws BookLogicException cuando no existe una getReview con el id
     * suministrado
     */
    @Override
    public void deleteReview(Long idBook, Long id) throws BookLogicException {
        logger.info("recibiendo solictud de eliminar getReview con id " + id);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : REVIEWS) {
            if (Objects.equals(getReview.getId(), id)) {

                // elimina la getReview
                logger.info("eliminando getReview " + getReview);
                REVIEWS.remove(getReview);
                return;
            }
        }

        // no encontró la getReview con ese id ?
        logger.severe("No existe una getReview con ese id");
        throw new BookLogicException("No existe una getReview con ese id");
    }
}
