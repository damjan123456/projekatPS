/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.citalac;

import model.Citalac;
import model.Sertifikat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class DodajCitaocaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Citalac)){
            throw new Exception("Sistem ne moze da doda citaoca");
        } 
        Citalac c = (Citalac) objekat;
        if (c.getIme() == null || c.getIme().isEmpty())
            throw new Exception("GRESKA IME");
        if (c.getPrezime()== null || c.getPrezime().isEmpty())
            throw new Exception("GRESKA PREZIME");
        if (c.getBrojTel()== null || c.getBrojTel().isEmpty() || c.getBrojTel().length() >10)
            throw new Exception("GRESKA BROJ TELEFONA");
        if (c.getMesto()== null)
            throw new Exception("GRESKA MESTO");
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Citalac)objekat);
    }
    
}
