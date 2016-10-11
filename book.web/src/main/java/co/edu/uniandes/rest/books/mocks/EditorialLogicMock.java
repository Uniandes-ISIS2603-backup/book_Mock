package co.edu.uniandes.rest.books.mocks;

import co.edu.uniandes.rest.books.api.IEditorialLogicMock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.books.dtos.EditorialDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import javax.ejb.Stateless;

@Stateless
public class EditorialLogicMock implements IEditorialLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(EditorialLogicMock.class.getName());

    // listado de editoriales
    private static ArrayList<EditorialDTO> EDITORIALS = null;

    protected EditorialLogicMock() {
        getInstance();
    }

    private synchronized static void createInstance() {
        if (EDITORIALS == null) {
            EDITORIALS = new ArrayList<>();
            EDITORIALS.add(new EditorialDTO(1L, "Plaza y Janes"));
            EDITORIALS.add(new EditorialDTO(2L, "Siruela"));
            EDITORIALS.add(new EditorialDTO(3L, "Universidad de los Andes"));
            EDITORIALS.add(new EditorialDTO(4L, "Ediciones USTA"));
        }
        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de EDITORIALS");
        logger.info("EDITORIALS" + EDITORIALS);
    }

    public static ArrayList<EditorialDTO> getInstance() {
        if (EDITORIALS == null) {
            createInstance();
        }
        return EDITORIALS;
    }

    /**
     * Obtiene el listado de EDITORIALS.
     *
     * @return lista de editoriales
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    @Override
    public List<EditorialDTO> getEditorials() throws BookLogicException {
        if (EDITORIALS == null) {
            logger.severe("Error interno: lista de Editorial no existe.");
            throw new BookLogicException("Error interno: lista de Editorials no existe.");
        }

        logger.info("retornando todas los EDITORIALS");
        return EDITORIALS;
    }

    /**
     * Obtiene un editorial
     *
     * @param id identificador de el editorial
     * @return editorial encontrada
     * @throws BookLogicException cuando el editorial no existe
     */
    @Override
    public EditorialDTO getEditorial(Long id) throws BookLogicException {
        logger.info("recibiendo solicitud de editorial con id " + id);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : EDITORIALS) {
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
    @Override
    public EditorialDTO createEditorial(EditorialDTO newEditorial) throws BookLogicException {
        logger.info("recibiendo solicitud de agregar editorial " + newEditorial);

        // la nueva editorial tiene id ?
        if (newEditorial.getId() != null) {
            // busca el editorial con el id suministrado
            for (EditorialDTO editorial : EDITORIALS) {
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
            for (EditorialDTO editorial : EDITORIALS) {
                // si existe una editorial con ese id

                if (Objects.equals(editorial.getName(), newEditorial.getName())) {
                    logger.severe("Ya existe una editorial con ese nombre");
                    throw new BookLogicException("Ya existe una editorial con ese nombre");
                }

            }
            // genera un id para el editorial
            logger.info("Generando id para la nueva editorial");
            long newId = 1;
            for (EditorialDTO editorial : EDITORIALS) {
                if (newId <= editorial.getId()) {
                    newId = editorial.getId() + 1;
                }
            }
            newEditorial.setId(newId);
        }

        // agrega el editorial
        logger.info("agregando editorial " + newEditorial);
        EDITORIALS.add(newEditorial);
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
    @Override
    public EditorialDTO updateEditorial(Long id, EditorialDTO updatedEditorial) throws BookLogicException {
        logger.info("recibiendo solictud de modificar editorial " + updatedEditorial);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : EDITORIALS) {
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
    @Override
    public void deleteEditorial(Long id) throws BookLogicException {
        logger.info("recibiendo solictud de eliminar editorial con id " + id);

        // busca el editorial con el id suministrado
        for (EditorialDTO editorial : EDITORIALS) {
            if (Objects.equals(editorial.getId(), id)) {

                // elimina el editorial
                logger.info("eliminando editorial " + editorial);
                EDITORIALS.remove(editorial);
                return;
            }
        }

        // no encontró el editorial con ese id ?
        logger.severe("No existe un editorial con ese id");
        throw new BookLogicException("No existe un editorial con ese id");
    }
}
