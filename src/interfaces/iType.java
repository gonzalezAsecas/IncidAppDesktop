/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.ReadException;
import java.util.List;
import javabeans.TypeBean;

/**
 *
 * @author Jon Gonzalez
 */
public interface iType {
    public List<TypeBean> findAllTypes() throws ReadException;
}
