/*
 * EditorialResource.java
 * Clase que representa el recurso "/editorials"
 * Implementa varios métodos para manipular las editoriales
 */
package co.edu.uniandes.rest.books.resources;

import co.edu.uniandes.rest.books.dtos.EditorialDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import co.edu.uniandes.rest.books.mocks.EditorialLogicMock;

import java.util.List;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "editorials".
 * 
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "editorials". 
 * Al ejecutar la aplicación, el recurse será accesibe a través de la 
 * ruta "/api/editorials" 
 * 
 * 
 */
@Path("editorials")
@Produces("application/json")
public class EditorialResource {

	
	EditorialLogicMock editorialLogic = new EditorialLogicMock();

	/**
	 * Obtiene el listado de editoriales. 
	 * @return lista de editoriales
	 * @throws BookLogicException excepción retornada por la lógica  
	 */
    @GET
    public List<EditorialDTO> getEditorials() throws BookLogicException {
        return editorialLogic.getEditorials();
    }

    /**
     * Obtiene un editorial
     * @param id identificador de el editorial
     * @return editorial encontrada
     * @throws BookLogicException cuando el editorial no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EditorialDTO getEditorial(@PathParam("id") Long id) throws BookLogicException {
        return editorialLogic.getEditorial(id);
    }

    /**
     * Agrega un editorial
     * @param editorial editorial a agregar
     * @return datos de el editorial a agregar
     * @throws BookLogicException cuando ya existe un editorial con el id suministrado
     */
    @POST
    public EditorialDTO createEditorial(EditorialDTO editorial) throws BookLogicException {
        return editorialLogic.createEditorial(editorial);
    }

    /**
     * Actualiza los datos de un editorial
     * @param id identificador de el editorial a modificar
     * @param editorial editorial a modificar
     * @return datos de el editorial modificada 
     * @throws BookLogicException cuando no existe un editorial con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EditorialDTO updateEditorial(@PathParam("id") Long id, EditorialDTO editorial) throws BookLogicException {
        return editorialLogic.updateEditorial(id, editorial);
    }

    /**
     * Elimina los datos de un editorial
     * @param id identificador de el editorial a eliminar
     * @throws BookLogicException cuando no existe un editorial con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEditorial(@PathParam("id") Long id) throws BookLogicException {
    	editorialLogic.deleteEditorial(id);
    }

}
