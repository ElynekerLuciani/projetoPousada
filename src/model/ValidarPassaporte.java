package model;

import exception.PassaporteInvalidoExpection;

/**
 *
 * @author Elyneker Luciani
 */
public class ValidarPassaporte {

    public static boolean isValid(String passaporte) throws PassaporteInvalidoExpection {
        passaporte = passaporte.trim().replaceAll("\\D", "");
        if (passaporte.isEmpty()) {
            throw new PassaporteInvalidoExpection();
        }
        return true;
    }

}
