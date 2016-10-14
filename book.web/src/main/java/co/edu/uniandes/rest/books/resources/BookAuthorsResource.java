package co.edu.uniandes.rest.books.resources;

import co.edu.uniandes.rest.books.api.IAuthorLogicMock;
import co.edu.uniandes.rest.books.dtos.AuthorDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * URI: books/{booksId: \\d+}/authors
 *
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookAuthorsResource {

    @Inject
    private IAuthorLogicMock authorLogic;

    /**
     * Obtiene el listado de authores.
     *
     * @param booksId
     * @return lista de authores
     * @throws BookLogicException excepción retornada por la lógica
     */
    @GET
    public List<AuthorDTO> getBookAuthors(@PathParam("booksId") Long booksId) throws BookLogicException {
        return authorLogic.getBookAuthors(booksId);
    }

    /**
     * Modifica los authors de book.
     *
     * @param booksId
     * @param authors
     * @return lista de authores
     * @throws BookLogicException excepción retornada por la lógica
     */
    @PUT
    public List<AuthorDTO> updateBookAuthors(@PathParam("booksId") Long booksId, List<AuthorDTO> authors) throws BookLogicException {
        return authorLogic.updateBookAuthors(booksId, authors);
    }

    /**
     * Elimina los datos de un author
     *
     * @param booksId
     * @param id identificador de el author a eliminar
     * @throws BookLogicException cuando no existe un author con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBookAuthor(@PathParam("booksId") Long booksId, @PathParam("id") Long id) throws BookLogicException {
        authorLogic.deleteBookAuthor(booksId, id);
    }

}
