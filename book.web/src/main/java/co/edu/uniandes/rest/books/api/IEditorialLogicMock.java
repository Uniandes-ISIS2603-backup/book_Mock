/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.books.api;

import co.edu.uniandes.rest.books.dtos.EditorialDTO;
import co.edu.uniandes.rest.books.exceptions.BookLogicException;
import java.util.List;

/**
 *
 * @author asistente
 */
public interface IEditorialLogicMock {
    public List<EditorialDTO> getEditorials() throws BookLogicException;
    public EditorialDTO getEditorial(Long id) throws BookLogicException;
    public EditorialDTO createEditorial(EditorialDTO newEditorial) throws BookLogicException;
    public EditorialDTO updateEditorial(Long id, EditorialDTO updatedEditorial) throws BookLogicException;
    public void deleteEditorial(Long id) throws BookLogicException;
    
}
