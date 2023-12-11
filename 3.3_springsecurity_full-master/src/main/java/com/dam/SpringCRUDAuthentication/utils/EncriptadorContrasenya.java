package com.dam.SpringCRUDAuthentication.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Albert Grau
 */
public class EncriptadorContrasenya {

    public static void main(String[] args) {

        var password = "123";
        System.out.println("Contrasenya: " + password);
        System.out.println("Contrasenya encriptada:" + encriptarContrasenya(password));
    }

    public static String encriptarContrasenya(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
