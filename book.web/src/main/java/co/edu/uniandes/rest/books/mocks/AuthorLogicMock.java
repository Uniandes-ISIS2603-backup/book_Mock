package co.edu.uniandes.rest.books.mocks;

import co.edu.uniandes.rest.books.api.IAuthorLogicMock;
import co.edu.uniandes.rest.books.api.IBookLogicMock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import co.edu.uniandes.rest.books.dtos.AuthorDTO;
import co.edu.uniandes.rest.books.dtos.BookDetailDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthorLogicMock implements IAuthorLogicMock{

    @Inject private IBookLogicMock bookLogic;
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(AuthorLogicMock.class.getName());

    // listado de authores
    private static ArrayList<AuthorDTO> authors;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public AuthorLogicMock() {

        Date date = new Date();
        if (authors == null) {
            authors = new ArrayList<>();
            authors.add(new AuthorDTO(1L, "Gabriel Garcia Marquez", date));
            authors.add(new AuthorDTO(2L, "Alvaro Mutis", date));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

  
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de authores
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    @Override
    public List<AuthorDTO> getAuthors() throws BookLogicException {
        if (authors == null) {
            logger.severe("Error interno: lista de Author no existe.");
            throw new BookLogicException("Error interno: lista de Authors no existe.");
        }

        logger.info("retornando todas los authors");
        return authors;
    }

    /**
     * Obtiene un author
     *
     * @param id identificador de el author
     * @return author encontrada
     * @throws BookLogicException cuando el author no existe
     */
    @Override
    public AuthorDTO getAuthor(Long id) throws BookLogicException {
        logger.info("recibiendo solicitud de author con id " + id);

        // busca el author con el id suministrado
        for (AuthorDTO author : authors) {
            if (Objects.equals(author.getId(), id)) {
                logger.info("retornando author " + author);
                return author;
            }
        }

        // si no encuentra el author
        logger.severe("No existe author con ese id");
        throw new BookLogicException("No existe author con ese id");
    }

    /**
     * Agrega una author a la lista.
     *
     * @param newAuthor author a adicionar
     * @throws BookLogicException cuando ya existe una author con el id
     * suministrado
     * @return author agregada
     */
    @Override
    public AuthorDTO createAuthor(AuthorDTO newAuthor) throws BookLogicException {
        logger.info("recibiendo solicitud de agregar author " + newAuthor);

        // la nueva author tiene id ?
        if (newAuthor.getId() != null) {
            // busca el author con el id suministrado
            for (AuthorDTO author : authors) {
                // si existe una author con ese id
                if (Objects.equals(author.getId(), newAuthor.getId())) {
                    logger.severe("Ya existe una author con ese id");
                    throw new BookLogicException("Ya existe una author con ese id");
                };
                if (Objects.equals(author.getName(), newAuthor.getName())) {
                    logger.severe("Ya existe una author con ese nombre");
                    throw new BookLogicException("Ya existe una author con ese nombre");
                }

            }

            // la nueva author no tiene id ? 
        } else {
            for (AuthorDTO author : authors) {
                // si existe una author con ese id

                if (Objects.equals(author.getName(), newAuthor.getName())) {
                    logger.severe("Ya existe una author con ese nombre");
                    throw new BookLogicException("Ya existe una author con ese nombre");
                }

            }
            // genera un id para el author
            logger.info("Generando id para la nueva author");
            long newId = 1;
            for (AuthorDTO author : authors) {
                if (newId <= author.getId()) {
                    newId = author.getId() + 1;
                }
            }
            newAuthor.setId(newId);
        }

        // agrega el author
        logger.info("agregando author " + newAuthor);
        authors.add(newAuthor);
        return newAuthor;
    }

    /**
     * Actualiza los datos de una author
     *
     * @param id identificador de el author a modificar
     * @param author author a modificar
     * @return datos de el author modificada
     * @throws BookLogicException cuando no existe una author con el id
     * suministrado
     */
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO updatedAuthor) throws BookLogicException {
        logger.info("recibiendo solictud de modificar author " + updatedAuthor);

        // busca el author con el id suministrado
        for (AuthorDTO author : authors) {
            if (Objects.equals(author.getId(), id)) {

                // modifica el author
                author.setId(updatedAuthor.getId());
                author.setName(updatedAuthor.getName());

                // retorna el author modificada
                logger.info("Modificando author " + author);
                return author;
            }
        }

        // no encontró el author con ese id ?
        logger.severe("No existe una author con ese id");
        throw new BookLogicException("No existe una author con ese id");
    }

    /**
     * Elimina los datos de una author
     *
     * @param id identificador de el author a eliminar
     * @throws BookLogicException cuando no existe una author con el id
     * suministrado
     */
    @Override
    public void deleteAuthor(Long id) throws BookLogicException {
        logger.info("recibiendo solictud de eliminar author con id " + id);

        // busca el author con el id suministrado
        for (AuthorDTO author : authors) {
            if (Objects.equals(author.getId(), id)) {

                // elimina el author
                logger.info("eliminando author " + author);
                authors.remove(author);
                return;
            }
        }

        // no encontró el author con ese id ?
        logger.severe("No existe un author con ese id");
        throw new BookLogicException("No existe un author con ese id");
    }

    /**
     * Actualiza la lista de autores de un book
     *
     * @param id identificador del book
     * @param authors lista nueva de authors
     * @return authors lista nueva de authors
     * @throws BookLogicException cuando no existe una author con el id
     * suministrado
     */
    @Override
    public List<AuthorDTO> updateBookAuthors(Long id, List<AuthorDTO> updatedAuthors) throws BookLogicException {
        logger.info("recibiendo solictud de modificar los autores del libro " + id + "con los autores [" );
        for (int i= 0; i < updatedAuthors.size(); i++)
            logger.info(updatedAuthors.get(i).toString()+ ",");
        BookDetailDTO book = bookLogic.getBook(id);
        book.setAuthors(updatedAuthors);
        logger.info("se actualizaron los autores del libro " + id + "con los autores " + book.getAuthors().toString());
        return updatedAuthors;

    }
    
    /**
     * Actualiza la lista de autores de un book
     *
     * @param id identificador del book
     * @param authors  de authors del libro id
     * @return authors del libro id
     * @throws BookLogicException cuando no existe una author con el id
     * suministrado
     */
    @Override
    public List<AuthorDTO> getBookAuthors(Long id) throws BookLogicException {
        logger.info("recibiendo solictud de modificar los autores del libro " + id);
        BookDetailDTO book = bookLogic.getBook(id);
       
        return book.getAuthors();

    }


}
