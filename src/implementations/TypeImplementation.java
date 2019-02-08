/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import exceptions.ReadException;
import interfaces.iType;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TypeBean;
import javax.ws.rs.core.GenericType;
import restfuls.TypeRestFul;

/**
 *
 * @author Gorka Redondo
 */
public class TypeImplementation implements iType{
    
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    //REST incident web client
    private TypeRestFul webClient;

    public TypeImplementation() {
        webClient = new TypeRestFul();
    }
    
    /**
     * 
     * @param type
     * @return typ
     * @throws ReadException 
     */
    @Override
    public TypeBean findTypeByName(TypeBean type) throws ReadException {
        List<TypeBean> types = null;
        TypeBean typ = null;
        try{
            types = webClient.findAll(new GenericType<List<TypeBean>>() {});
            for(TypeBean t : types){
                if(t.getName().equalsIgnoreCase(type.getName())) {
                    typ = t;
                    break;
                }
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding type",ex.getMessage());
            throw new ReadException("Error finding type:\n"+ex.getMessage());
        }
        return typ;
    }
    
    /**
     * 
     * @return types
     * @throws ReadException 
     */
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
