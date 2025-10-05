/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import java.util.List;
import model.Bibliotekar;
import model.Citalac;
import operacija.ApstraktnaGenerickaOperacija;



/**
 *
 * @author damja
 */
public class LoginSO extends ApstraktnaGenerickaOperacija {
    Bibliotekar bibliotekar;
    @Override
    protected void preduslovi(Object param) throws Exception{
        if (param == null || !(param instanceof Bibliotekar))
            throw new Exception("Prijava nije moguca");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception{
        List<Bibliotekar> bibliotekari = broker.getAll((Bibliotekar)param, null);
        System.out.println("KLASA LoginOperacija SO" + bibliotekari);
        
        for (Bibliotekar b : bibliotekari) {
            if(b.equals((Bibliotekar)param)){
                bibliotekar = b;
                return;
            }
        }
        bibliotekar = null;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }
    
    
    
}
