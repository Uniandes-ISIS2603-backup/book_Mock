/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.api;

import co.edu.uniandes.rest.books.dtos.AuthorDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import java.util.List;

/**
 *
 * @author asistente
 */
public interface IAuthorLogicMock {
    public List<AuthorDTO> getAuthors() throws BookLogicException;
    public AuthorDTO getAuthor(Long id) throws BookLogicException;
    public AuthorDTO createAuthor(AuthorDTO newAuthor) throws BookLogicException;
    public AuthorDTO updateAuthor(Long id, AuthorDTO updatedAuthor) throws BookLogicException;
    public void deleteAuthor(Long id) throws BookLogicException;
    public List<AuthorDTO> updateBookAuthors(Long id, List<AuthorDTO> updatedAuthors) throws BookLogicException;
    public List<AuthorDTO> getBookAuthors(Long id) throws BookLogicException;
}
