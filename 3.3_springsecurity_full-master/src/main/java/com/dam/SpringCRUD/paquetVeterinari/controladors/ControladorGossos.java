package com.dam.SpringCRUD.paquetVeterinari.controladors;

import com.dam.SpringCRUD.model.Gos;
import com.dam.SpringCRUD.paquetVeterinari.serveis.GosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Albert Grau
 */
@Controller
public class ControladorGossos {
        
    /*Modifiquem l'atribut gosDAO que teniem en el projecte anterior per un atribut de tipus GosService, ja que
     *ara accedirem a la base de dades mitjançant els mètodes d'aquesta classe, afegint una nova capa al 
     *nostre projecte.
     */
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private GosService gosService;

    
         @GetMapping("/gossos") //Pàgina inicial dels gossos    
    public String iniciGossos2(Model model, @AuthenticationPrincipal User username) {

        //llistarGossos() retorna el llistat d'objectes gos guardats en la taula gossos de la BBDD    
        model.addAttribute("gossos", gosService.llistarGossos());     

        return "iniciGossos"; //Retorna la pàgina iniciEnviarDades
    }
   
    
    
     @GetMapping("/iniciGossos") //Pàgina inicial dels gossos    
    public String iniciGossos(Model model, @AuthenticationPrincipal User username) {

        //llistarGossos() retorna el llistat d'objectes gos guardats en la taula gossos de la BBDD    
        model.addAttribute("gossos", gosService.llistarGossos());     

        return "iniciGossos"; //Retorna la pàgina iniciEnviarDades
    }
   
    
    
    /*@AuthenticationPrincipal retorna l'usuari autenticat actualment com un objecte User de Spring security*/
    @GetMapping("/") //Pàgina inicial dels gossos    
    public String inici(Model model, @AuthenticationPrincipal User username) {

        //llistarGossos() retorna el llistat d'objectes gos guardats en la taula gossos de la BBDD    
        model.addAttribute("gossos", gosService.llistarGossos());     

        return "iniciGossos"; //Retorna la pàgina iniciEnviarDades
    }
    
    /*Definim el mètode per mostrar la pàgina amb el forumlari de les dades del gos passat com a paràmetre.
     *Aquest gos, si no existeix, es crearà de manera automàtica amb els seu atributs buits (recordem que el 
     *constructor construeix un objecte buit) en el moment que executem aquest mètode.
     *
     */
        @GetMapping("/formulariGos") //URL a la pàgina amb el formulari de les dades del gos
    public String crearFormulariGos(Gos gos) {

        return "formulariGos"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }
    
    /*Definim el mètode per assignar els valors introduïts en el formulari a l'objecte gos
     *passat com a paràmetre.
     *
     *L'anotació @PostMapping, indica al sistema que el mètode que fem servir per enviar les dades és
     *post. Com a paràmetre hem de passar el valor de l'action del formulari, d'aquesta manera el sistema 
     *identifica el mètode al qual ha d'enviar les dades introduïdes mitjançant el formulari.
    */
    
    /*Abans de guardar les dades del gos, és quan comprovem si són valides o no, perquè el sistema
     *realitzi aquesta validació, utilitzem l'anotació @Valid precedint a l'objecte al qual pertanyen
     *els valors a validar, en el nostre cas, l'objecte Gos passat per paràmetre.
     *Per un altre costat, al mètode li passem el paràmetre error, objectede la classe Errors per saber
     *si el formulari té errors.
    */
    @PostMapping("/guardarGos") //action=guardarGos
    public String guardarGos(@Valid Gos gos, Errors errors) {
        
        if(errors.hasErrors()){ //Si s'han produït errors...
             return "formulariGos"; //Mostrem la pàgina del formulari
        }

        gosService.afegirGos(gos); //Afegim el gos passat per paràmetre a la base de dades

        return "redirect:/iniciGossos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    
    /*Definim el mètode que ens retornarà la pàgina formulariGos on se'ns mostraran les dades del gos
     *amb l'idgos que enviem des de la pàgina inici.
     *El sistema Spring associa l'idgos passat com a paràmetre en @GetMapping al gos 
     *passat com a paràmetre en el mètode editar i crida automàticament al mètode setIdgos 
     *de la classe Gos per fer aquesta associació, és a dir, el que fa és gos.setIdgos(idgos).
     *IMPORTANT: idgos ha de tenir el mateix nom que l'atribut id de la classe a la que fa referència,
     *en el nostre cas la classe Gos.
     */
    @GetMapping("/editar/{idgos}")
    public String editar(Gos gos, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("gos", gosService.cercarGos(gos));

        return "formulariGos"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    /*Definim el mètode per eliminar el gos en la base de dades i finalment retornar
     *a la pàgina d'inici. El gos l'eliminarem mitjançant el mètode eliminarGos de
     *la classe GosService.
     *El sistema per associar l'id del gos a l'objecte gos passat per paràmetre, és el mateix
     *que el del mètode editar.
     *IMPORTANT: idgos ha de tenir el mateix nom que l'atribut id de la classe a la que fa referència,
     *en el nostre cas la classe Gos.
     */
    @GetMapping("/eliminar/{idgos}")
    public String eliminar(Gos gos) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        gosService.eliminarGos(gos);
        
        return "redirect:/iniciGossos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    
}
