package co.edu.uniandes.rest.books.mocks;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import co.edu.uniandes.rest.books.dtos.EditorialDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;


public class EditorialLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(EditorialLogicMock.class.getName());

    // listado de editoriales
    private static ArrayList<EditorialDTO> editorials;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public EditorialLogicMock() {

        if (editorials == null) {
            editorials = new ArrayList<>();
            editorials.add(new EditorialDTO(1L, "Plaza y Janes"));
            editorials.add(new EditorialDTO(2L, "Siruela"));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de editorials");
        logger.info("editorials" + editorials);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de editoriales
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    public List<EditorialDTO> getEditorials() throws BookLogicException {
        if (editorials == null) {
            logger.severe("Error interno: lista de Editorial no existe.");
            throw new BookLogicException("Error interno: lista de Editorials no existe.");
        }

        logger.info("retornando todas los editorials");
        return editorials;
    }

    /**
     * Obtiene un editorial
     *
     * @param id identificador de el editorial
     * @return editorial encontrada
     * @throws BookLogicException cuando el editorial no existe
     */
    public EditorialDTO getEditorial(Long id) throws BookLogicException {
        logger.info("recibiendo solicitud de editorial con id " + id);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : editorials) {
            if (Objects.equals(editorial.getId(), id)) {
                logger.info("retornando editorial " + editorial);
                return editorial;
            }
        }

        // si no encuentra el editorial
        logger.severe("No existe editorial con ese id");
        throw new BookLogicException("No existe editorial con ese id");
    }

    /**
     * Agrega una editorial a la lista.
     *
     * @param newEditorial editorial a adicionar
     * @throws BookLogicException cuando ya existe una editorial con el id
     * suministrado
     * @return editorial agregada
     */
    public EditorialDTO createEditorial(EditorialDTO newEditorial) throws BookLogicException {
        logger.info("recibiendo solicitud de agregar editorial " + newEditorial);

        // la nueva editorial tiene id ?
        if (newEditorial.getId() != null) {
            // busca el editorial con el id suministrado
            for (EditorialDTO editorial : editorials) {
                // si existe una editorial con ese id
                if (Objects.equals(editorial.getId(), newEditorial.getId())) {
                    logger.severe("Ya existe una editorial con ese id");
                    throw new BookLogicException("Ya existe una editorial con ese id");
                };
                if (Objects.equals(editorial.getName(), newEditorial.getName())) {
                    logger.severe("Ya existe una editorial con ese nombre");
                    throw new BookLogicException("Ya existe una editorial con ese nombre");
                }

            }

            // la nueva editorial no tiene id ? 
        } else {
            for (EditorialDTO editorial : editorials) {
                // si existe una editorial con ese id
                
                if (Objects.equals(editorial.getName(), newEditorial.getName())) {
                    logger.severe("Ya existe una editorial con ese nombre");
                    throw new BookLogicException("Ya existe una editorial con ese nombre");
                }

            }
            // genera un id para el editorial
            logger.info("Generando id para la nueva editorial");
            long newId = 1;
            for (EditorialDTO editorial : editorials) {
                if (newId <= editorial.getId()) {
                    newId = editorial.getId() + 1;
                }
            }
            newEditorial.setId(newId);
        }

        // agrega el editorial
        logger.info("agregando editorial " + newEditorial);
        editorials.add(newEditorial);
        return newEditorial;
    }

    /**
     * Actualiza los datos de una editorial
     *
     * @param id identificador de el editorial a modificar
     * @param editorial editorial a modificar
     * @return datos de el editorial modificada
     * @throws BookLogicException cuando no existe una editorial con el id
     * suministrado
     */
    public EditorialDTO updateEditorial(Long id, EditorialDTO updatedEditorial) throws BookLogicException {
        logger.info("recibiendo solictud de modificar editorial " + updatedEditorial);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : editorials) {
            if (Objects.equals(editorial.getId(), id)) {

                // modifica el editorial
                editorial.setId(updatedEditorial.getId());
                editorial.setName(updatedEditorial.getName());

                // retorna el editorial modificada
                logger.info("Modificando editorial " + editorial);
                return editorial;
            }
        }

        // no encontró el editorial con ese id ?
        logger.severe("No existe una editorial con ese id");
        throw new BookLogicException("No existe una editorial con ese id");
    }

    /**
     * Elimina los datos de una editorial
     *
     * @param id identificador de el editorial a eliminar
     * @throws BookLogicException cuando no existe una editorial con el id
     * suministrado
     */
    public void deleteEditorial(Long id) throws BookLogicException {
        logger.info("recibiendo solictud de eliminar editorial con id " + id);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : editorials) {
            if (Objects.equals(editorial.getId(), id)) {

                // elimina el editorial
                logger.info("eliminando editorial " + editorial);
                editorials.remove(editorial);
                return;
            }
        }

        // no encontró el editorial con ese id ?
        logger.severe("No existe un editorial con ese id");
        throw new BookLogicException("No existe un editorial con ese id");
    }
}