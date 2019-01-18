/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import static implementations.FTPCliente.LOGGER;
import exceptions.ReadException;
import interfaces.iType;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javabeans.TypeBean;
import javax.ws.rs.core.GenericType;
import restfuls.TypeRestFulClient;

/**
 *
 * @author Gorka Redondo
 */
public class TypeImplementation implements iType{
    
    //REST incident web client
    private TypeRestFulClient webClient;

    public TypeImplementation() {
        webClient = new TypeRestFulClient();
    }
    
    @Override
    public Collection<TypeBean> findAllTypes() throws ReadException {
        List<TypeBean> types = null;
        try{
            types = webClient.findAll(new GenericType<List<TypeBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding all types",ex.getMessage());
            throw new ReadException("Error finding all types:\n"+ex.getMessage());
        }
        return types;
    }
}
