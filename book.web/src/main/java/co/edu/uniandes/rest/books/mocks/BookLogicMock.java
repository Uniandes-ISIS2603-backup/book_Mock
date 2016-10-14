package co.edu.uniandes.rest.books.mocks;

/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 *
 * @citi Asistente
 */
import co.edu.uniandes.rest.books.api.IBookLogicMock;
import co.edu.uniandes.rest.books.dtos.BookDTO;
import co.edu.uniandes.rest.books.dtos.BookDetailDTO;
import co.edu.uniandes.rest.books.dtos.EditorialDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.ejb.Stateless;

@Stateless
public class BookLogicMock implements IBookLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(BookLogicMock.class.getName());

    // listado de books
    private static ArrayList<BookDetailDTO> BOOKS;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    protected BookLogicMock() {
        getInstance();
    }

    private synchronized static void createInstance() {
        Date date = new Date();

        if (BOOKS == null) {
            BOOKS = new ArrayList<>();
            BOOKS.add(new BookDetailDTO(1L, "Cien años de Soledad", "123", "imagen", "Wonderful!", date, new EditorialDTO(1L, "Plaza y Janes")));
            BOOKS.add(new BookDetailDTO(2L, "El Coronel no tiene quien le escriba", "123", "imagen", "Wonderful!", date, new EditorialDTO(2L, "Siruela")));
            BOOKS.add(new BookDetailDTO(3L, "Ojos de Perro Azul", "123", "imagen", "Wonderful!", date, new EditorialDTO(1L, "Plaza y Janes")));
        }
        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de Books");
        logger.log(Level.INFO, "Books {0}", BOOKS);
    }

    public static ArrayList<BookDetailDTO> getInstance() {
        if (BOOKS == null) {
            createInstance();
        }
        return BOOKS;
    }

    /**
     * Obtiene el listado de books.
     *
     * @return lista de books
     * @throws BookLogicException cuando no existe la lista en memoria
     */
    @Override
    public List<BookDTO> getBooks() throws BookLogicException {
        if (BOOKS == null) {
            logger.severe("Error interno: lista de books no existe.");
            throw new BookLogicException("Error interno: lista de books no existe.");
        }

        logger.info("retornando todas las Books");

        ArrayList<BookDTO> booksMinimum = new ArrayList<>();
        for (int i = 0; i < BOOKS.size(); i++) {
            booksMinimum.add(BOOKS.get(i));
        }
        return booksMinimum;
    }

    /**
     * Obtiene una book
     *
     * @param id identificador de la book
     * @return book encontrada
     * @throws BookLogicException cuando la book no existe
     */
    @Override
    public BookDetailDTO getBook(Long id) throws BookLogicException {
        logger.log(Level.INFO, "getbook con id {0}", id);

        // busca la book con el id suministrado
        for (BookDetailDTO book : BOOKS) {
            if (Objects.equals(book.getId(), id)) {
                logger.log(Level.INFO, "getbook {0}", book);
                return book;
            }
        }

        // si no encuentra la book
        logger.severe("No existe book con ese id");
        throw new BookLogicException("No existe book con ese id");
    }

    /**
     * Agrega una book a la lista.
     *
     * @param newBook book a adicionar
     * @throws BookLogicException cuando ya existe una book con el id
     * suministrado
     * @return book agregada
     */
    @Override
    public BookDTO createBook(BookDTO newBook) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solicitud de agregar book {0}", newBook);

        // la nueva book tiene id ?
        if (newBook.getId() != null) {
            // busca la book con el id suministrado
            for (BookDTO book : BOOKS) {
                // si existe una book con ese id
                if (Objects.equals(book.getId(), newBook.getId())) {
                    logger.severe("Ya existe una book con ese id");
                    throw new BookLogicException("Ya existe una book con ese id");
                }
                if (Objects.equals(book.getName(), newBook.getName())) {
                    logger.severe("Ya existe una book con ese nombre");
                    throw new BookLogicException("Ya existe una book con ese nombre");
                }

            }

            // la nueva book no tiene id ? 
        } else {
            for (BookDTO book : BOOKS) {
                // si existe una book con ese id

                if (Objects.equals(book.getName(), newBook.getName())) {
                    logger.severe("Ya existe una book con ese nombre");
                    throw new BookLogicException("Ya existe una book con ese nombre");
                }

            }
            // genera un id para la book
            logger.info("Generando id para la nueva book");
            long newId = 1;
            for (BookDTO book : BOOKS) {
                if (newId <= book.getId()) {
                    newId = book.getId() + 1;
                }
            }
            newBook.setId(newId);
        }

        // agrega la book
        logger.log(Level.INFO, "agregando book {0}", newBook);
        BOOKS.add(new BookDetailDTO(newBook));
        return newBook;
    }

    /**
     * Actualiza los datos de una book
     *
     * @param id identificador de la book a modificar
     * @param updatedBook
     * @return datos de la book modificada
     * @throws BookLogicException cuando no existe una book con el id
     * suministrado
     */
    @Override
    public BookDTO updateBook(Long id, BookDTO updatedBook) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solictud de modificar book {0}", updatedBook);

        // busca la book con el id suministrado
        for (BookDTO book : BOOKS) {
            if (Objects.equals(book.getId(), id)) {

                // modifica la book
                book.setId(updatedBook.getId());
                book.setName(updatedBook.getName());
                book.setDescription(updatedBook.getDescription());
                book.setImage(updatedBook.getImage());
                book.setIsbn(updatedBook.getIsbn());
                book.setPublishingdate(updatedBook.getPublishingdate());
                book.setEditorial(updatedBook.getEditorial());

                // retorna la book modificada
                logger.log(Level.INFO, "Modificando book {0}", book);
                return book;
            }
        }

        // no encontró la book con ese id ?
        logger.severe("No existe una book con ese id");
        throw new BookLogicException("No existe una book con ese id");
    }

    /**
     * Elimina los datos de una book
     *
     * @param id identificador de la book a eliminar
     * @throws BookLogicException cuando no existe una book con el id
     * suministrado
     */
    @Override
    public void deleteBook(Long id) throws BookLogicException {
        logger.log(Level.INFO, "recibiendo solictud de eliminar book con id {0}", id);

        // busca la book con el id suministrado
        for (BookDTO book : BOOKS) {
            if (Objects.equals(book.getId(), id)) {
                // elimina la book
                logger.log(Level.INFO, "eliminando book {0}", book);
                BOOKS.remove(book);
            }
        }

        // no encontró la book con ese id ?
        logger.severe("No existe una book con ese id");
        throw new BookLogicException("No existe una book con ese id");
    }
}
