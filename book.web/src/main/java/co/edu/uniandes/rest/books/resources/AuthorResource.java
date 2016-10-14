/*
 * AuthorResource.java
 * Clase que representa el recurso "/authors"
 * Implementa varios métodos para manipular las authores
 */
package co.edu.uniandes.rest.books.resources;

import co.edu.uniandes.rest.books.api.IAuthorLogicMock;
import co.edu.uniandes.rest.books.dtos.AuthorDTO;
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
 * Clase que implementa el recurso REST correspondiente a "authors".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "authors". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/authors"
 *
 *
 */
@Path("authors")
@Produces("application/json")
public class AuthorResource {

    @Inject
    private IAuthorLogicMock authorLogic;

    /**
     * Obtiene el listado de authores.
     *
     * @return lista de authors
     * @throws BookLogicException excepción retornada por la lógica
     */
    @GET
    public List<AuthorDTO> getAuthors() throws BookLogicException {
        return authorLogic.getAuthors();
    }

    /**
     * Obtiene un author
     *
     * @param id identificador de el author
     * @return author encontrada
     * @throws BookLogicException cuando el author no existe
     */
    @GET
    @Path("{id: \\d+}")
    public AuthorDTO getAuthor(@PathParam("id") Long id) throws BookLogicException {
        return authorLogic.getAuthor(id);
    }

    /**
     * Agrega un author
     *
     * @param author author a agregar
     * @return datos de el author a agregar
     * @throws BookLogicException cuando ya existe un author con el id
     * suministrado
     */
    @POST
    public AuthorDTO createAuthor(AuthorDTO author) throws BookLogicException {
        return authorLogic.createAuthor(author);
    }

    /**
     * Actualiza los datos de un author
     *
     * @param id identificador de el author a modificar
     * @param author author a modificar
     * @return datos de el author modificada
     * @throws BookLogicException cuando no existe un author con el id
     * suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public AuthorDTO updateAuthor(@PathParam("id") Long id, AuthorDTO author) throws BookLogicException {
        return authorLogic.updateAuthor(id, author);
    }

    /**
     * Elimina los datos de un author
     *
     * @param id identificador de el author a eliminar
     * @throws BookLogicException cuando no existe un author con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAuthor(@PathParam("id") Long id) throws BookLogicException {
        authorLogic.deleteAuthor(id);
    }

}
