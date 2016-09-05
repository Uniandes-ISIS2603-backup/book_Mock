/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.mocks;

import co.edu.uniandes.rest.books.dtos.ReviewDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcasalla
 */


public class ReviewLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ReviewLogicMock.class.getName());

    // listado de reviews
    private static ArrayList<ReviewDTO> reviews;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ReviewLogicMock() {

       
       
      
        if (reviews == null) {
            reviews = new ArrayList<>();
            reviews.add(new ReviewDTO(1L, " ", "El Tiempo", "Maravillosa descripción de lo ilógico" ));
            reviews.add(new ReviewDTO(2L, " ", "El Espectador", "Fantástica obra de la realidad imposible"));
      
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de reviews");
        logger.info("reviews" + reviews);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de reviews
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    public List<ReviewDTO> getReviews(Long idBook) throws BookLogicException {
        if (reviews == null) {
            logger.severe("Error interno: lista de reviews no existe.");
            throw new BookLogicException("Error interno: lista de reviews no existe.");
        }

        logger.info("retornando todas las reviews");
        return reviews;
    }

    /**
     * Obtiene una getReview
     *
     * @param id identificador de la getReview
     * @return getReview encontrada
     * @throws BookLogicException cuando la getReview no existe
     */
    public ReviewDTO getReview(Long idBook, Long id) throws BookLogicException {
        logger.info("recibiendo solicitud de getReview con id " + id);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : reviews) {
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
    public ReviewDTO createReview(Long idBook, ReviewDTO newReview) throws BookLogicException {
        logger.info("recibiendo solicitud de agregar getReview " + newReview);

        // la nueva getReview tiene id ?
        if (newReview.getId() != null) {
            // busca la getReview con el id suministrado
            for (ReviewDTO getReview : reviews) {
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
            for (ReviewDTO getReview : reviews) {
                // si existe una getReview con ese id

                if (Objects.equals(getReview.getName(), newReview.getName())) {
                    logger.severe("Ya existe una getReview con ese nombre");
                    throw new BookLogicException("Ya existe una getReview con ese nombre");
                }

            }
            // genera un id para la getReview
            logger.info("Generando id para la nueva getReview");
            long newId = 1;
            for (ReviewDTO getReview : reviews) {
                if (newId <= getReview.getId()) {
                    newId = getReview.getId() + 1;
                }
            }
            newReview.setId(newId);
        }

        // agrega la getReview
        logger.info("agregando getReview " + newReview);
        reviews.add(newReview);
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
    public ReviewDTO updateReview(Long idBook, Long id, ReviewDTO updatedReview) throws BookLogicException {
        logger.info("recibiendo solictud de modificar getReview " + updatedReview);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : reviews) {
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
    public void deleteReview(Long idBook, Long id) throws BookLogicException {
        logger.info("recibiendo solictud de eliminar getReview con id " + id);

        // busca la getReview con el id suministrado
        for (ReviewDTO getReview : reviews) {
            if (Objects.equals(getReview.getId(), id)) {

                // elimina la getReview
                logger.info("eliminando getReview " + getReview);
                reviews.remove(getReview);
                return;
            }
        }

        // no encontró la getReview con ese id ?
        logger.severe("No existe una getReview con ese id");
        throw new BookLogicException("No existe una getReview con ese id");
    }
}
