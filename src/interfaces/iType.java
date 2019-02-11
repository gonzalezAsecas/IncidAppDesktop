/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.ReadException;
import java.util.Collection;
import javabeans.TypeBean;

/**
 *
 * @author Gorka Redondo
 */
public interface iType {
    public TypeBean findTypeByName(TypeBean type) throws ReadException;
    
    public Collection<TypeBean> findAllTypes() throws ReadException;
}
