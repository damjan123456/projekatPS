/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavke;

import java.util.List;
import model.StavkaZapisaOIznajmljivanju;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class VratiStavkeSO extends ApstraktnaGenerickaOperacija {
    List<StavkaZapisaOIznajmljivanju> stavke;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN knjiga ON stavkazapisaoiznajmljivanju.idKnjiga=knjiga.idKnjiga WHERE stavkazapisaoiznajmljivanju.idZapis="+ ((StavkaZapisaOIznajmljivanju)objekat).getZapis();
        stavke = broker.getAll(objekat, uslov);
    }

    public List<StavkaZapisaOIznajmljivanju> getStavke() {
        return stavke;
    }
    
    
    
}
