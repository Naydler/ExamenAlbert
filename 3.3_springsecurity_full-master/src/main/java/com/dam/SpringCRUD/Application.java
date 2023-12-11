package com.dam.SpringCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/* - Totes les classes que creem, han de portar una anotació que ha de fer referència a un component 
 *   (classe, interface, etc) predefinit d'Spring. D'aquesta manera el sistema les reconeix com a classes 
 *   de Spring.
 *
 * - Spring és un contenidor de classes anomenades beans.
 *
 * - Qualsevol classe s'ha de trobar dins el paquet generat en el directori SourcePackages, ja que
 *   és aquí on el sistema va a cercar les classes de l'aplicació, si estan fora d'aquest paquet,
 *   no les reconeix com a classes de l'aplicació.
 */


//Anotació que fa referència a la interface SpringBootApplication
@SpringBootApplication
public class Application {

    /*El mètode run, és l'encarregat d'executar l'aplicació a la que pertany la classe que passem com a 
     *primer paràmetre i que ha de contenir el mètode main.
     *Si l'execució necessita arguments, els passarem com a segon paràmetre en format d'array de Strings.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
