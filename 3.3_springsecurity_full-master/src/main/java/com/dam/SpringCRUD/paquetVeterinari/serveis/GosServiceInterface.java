package com.dam.SpringCRUD.paquetVeterinari.serveis;


import com.dam.SpringCRUD.model.Gos;
import java.util.List;

/**
 *
 * @author Albert Grau
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface GosServiceInterface {
    
    public List<Gos> llistarGossos(); //Mètode que implementarem per llistar gossos
    
    public void afegirGos(Gos gos); //Mètode que implementarem per afegir un gos
    
    public void eliminarGos(Gos gos); //Mètode que implementarem per eliminar un gos
    
    public Gos cercarGos(Gos gos); //Mètode que implementarem per cercar un gos
}
