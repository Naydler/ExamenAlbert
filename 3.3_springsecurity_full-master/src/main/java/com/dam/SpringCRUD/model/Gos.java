package com.dam.SpringCRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author albert grau
 */

/*@Entity converteix la classe en una entitat per poder treballar amb els seus objectes
 *com entitats de la base de dades
 */

/*@Table avisa al sistema que la classe Gos és la classe relacionada amb la taula gos de la BBDD.
 * D'aquesta manera evitem problemes en relacionar l'entitat amb la taula corresponent a causa dels noms,
 * ja que el de la taula comença amb minúscules i el de la classe amb majúscula. Això ens donarà problemes
 * sobretot en els S.O. Linux i MAC.
 * Aquesta anotació no és obligatòria però altament recomanable per evitar problemes.
 */

/*Implementem la interfície Serializable perquè els objectes de gos es puguin guardar
 *com un objecte en la base de dades.
 */


@Data
@Entity
@Table(name = "gossos")
public class Gos implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    private int idgos;
    
    
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    @NotEmpty
    @Size(min = 5) //Validem un nombre mínim de caràcters, en el nostre cas el nom ha de tenir mínim 5 caràcters
    private String nom;
    
    /*Validació per comprovar que el sexe no està buit. Com a paràmetre no li passem res, per tant
     *ens mostrarà el missatge per defecte del sitema.
     */
    @NotEmpty
    private String sexe;
    
    @Min(value = 0) //Validació que el valor de l'edat no sigui negatiu, per tant mínim 0.
    private int edat;
    
}
