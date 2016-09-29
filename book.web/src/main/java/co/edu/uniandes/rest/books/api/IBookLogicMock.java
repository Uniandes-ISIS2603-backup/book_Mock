/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.api;

import co.edu.uniandes.rest.books.dtos.BookDTO;
import co.edu.uniandes.rest.books.dtos.BookDetailDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import co.edu.uniandes.rest.books.mocks.BookLogicMock;
import java.util.List;

/**
 *
 * @author asistente
 */
public interface IBookLogicMock {
    public List<BookDTO> getBooks()throws BookLogicException;
    public BookDetailDTO getBook(Long id)throws BookLogicException;
    public BookDTO createBook(BookDTO newBook)throws BookLogicException;
    public BookDTO updateBook(Long id, BookDTO updatedBook)throws BookLogicException;
    public void deleteBook(Long id)throws BookLogicException;
    
}
