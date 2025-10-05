/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zapis;

import java.util.List;
import model.ZapisOIznajmljivanju;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class VratiZapiseSO extends ApstraktnaGenerickaOperacija {
    List<ZapisOIznajmljivanju> zapisi;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN citalac ON zapisoiznajmljivanju.idCitalac=citalac.idCitalac JOIN bibliotekar ON zapisoiznajmljivanju.idBibliotekar=bibliotekar.idBibliotekar JOIN mesto ON citalac.idMesto=mesto.idMesto";
        zapisi = broker.getAll(new ZapisOIznajmljivanju(), uslov);
                
                        
    }

    public List<ZapisOIznajmljivanju> getZapisi() {
        return zapisi;
    }
    
}
