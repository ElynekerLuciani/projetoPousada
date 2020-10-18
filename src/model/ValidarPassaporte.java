/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.PassaporteInvalidoExpection;


/**
 *
 * @author Elyneker Luciani
 */
public class ValidarPassaporte{
    
    public static boolean isValid(String passaporte) throws PassaporteInvalidoExpection {
        passaporte = passaporte.trim().replaceAll("\\D", "");
        if(passaporte.isEmpty()) 
            throw new PassaporteInvalidoExpection();
        return true;
    }
    
}
