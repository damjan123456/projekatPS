/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.citalac;

import model.Citalac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class ObrisiCitaocaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception{
        if (param == null || !(param instanceof Citalac)){
            throw new Exception("Sistem ne moze da obrise citaoca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Citalac)objekat);
    }
    
}
