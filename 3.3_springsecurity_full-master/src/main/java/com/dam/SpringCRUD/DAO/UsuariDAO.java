package com.dam.SpringCRUD.DAO;

import com.dam.SpringCRUD.model.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Albert Grau
 */


/*Els tipus de classes que en el nostre cas utilitza JpaRepository són Usuari i Long, Usuari fa refèrencia a 
 *la classe que defineix l'entitat amb la que treballarem i Long el tipus de l'atribut que defineix la clau 
 *primaria d'aquesta entitat.
*/
    

public interface UsuariDAO extends JpaRepository<Usuari,Long>{ 
    
    /*Mètode que retornarà l'usuari que passem per paràmetre. 
    *El nom d'aquest mètode ha de ser findByUsername, ja que és el que reconeix Spring Boot
    *com a mètode de seguretat per recuperar l'usuari.
    */
    Usuari findByUsername(String username);
    
}
