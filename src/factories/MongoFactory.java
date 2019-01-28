/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import implementations.MongoImplementation;
import interfaces.iMongo;

/**
 *
 * @author Jon Gonzalez
 */
public class MongoFactory {
    public static iMongo getIMongo(){
        return new MongoImplementation();
    }
}
