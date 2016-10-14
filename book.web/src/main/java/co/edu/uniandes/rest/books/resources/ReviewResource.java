/*
 * ReviewResource.java
 * Clase que representa el recurso "/books/idBook/reviews"
 * Implementa varios métodos para manipular las books
 */
package co.edu.uniandes.rest.books.resources;

import co.edu.uniandes.rest.books.api.IReviewLogicMock;
import co.edu.uniandes.rest.books.dtos.ReviewDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "reviews".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "reviews". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/books/idBook/reviews"
 *
 */
@Path("books/{idBook: \\d+}/reviews")
@Produces("application/json")
public class ReviewResource {

    @Inject
    private IReviewLogicMock reviewLogic;

    /**
     * Obtiene el listado de reviews.
     *
     * @param idBook
     * @return lista de reviews
     * @throws BookLogicException excepción retornada por la lógica
     */
    @GET
    public List<ReviewDTO> getReviews(@PathParam("idBook") Long idBook) throws BookLogicException {
        return reviewLogic.getReviews(idBook);
    }

    /**
     * Obtiene una review
     *
     * @param idBook
     * @param id identificador de la review
     * @return review encontrada
     * @throws BookLogicException cuando la review no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ReviewDTO getReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id) throws BookLogicException {
        return reviewLogic.getReview(idBook, id);
    }

    /**
     * Agrega una review
     *
     * @param idBook
     * @param review review a agregar
     * @return datos de la review a agregar
     * @throws BookLogicException cuando ya existe una review con el id
     * suministrado
     */
    @POST
    public ReviewDTO createReview(@PathParam("idBook") Long idBook, ReviewDTO review) throws BookLogicException {
        System.out.println("En el servicio: ");
        System.out.println("idBook " + idBook);
        System.out.println("review " + review.toString());
        return reviewLogic.createReview(idBook, review);
    }

    /**
     * Actualiza los datos de una review
     *
     * @param idBook
     * @param id identificador de la review a modificar
     * @param review review a modificar
     * @return datos de la review modificada
     * @throws BookLogicException cuando no existe una review con el id
     * suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ReviewDTO updateReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id, ReviewDTO review) throws BookLogicException {
        return reviewLogic.updateReview(idBook, id, review);
    }

    /**
     * Elimina los datos de una review
     *
     * @param idBook
     * @param id identificador de la review a eliminar
     * @throws BookLogicException cuando no existe una review con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("idBook") Long idBook, @PathParam("id") Long id) throws BookLogicException {
        reviewLogic.deleteReview(idBook, id);
    }

}
