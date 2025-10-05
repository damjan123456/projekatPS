/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zapis;

import java.util.List;
import model.StavkaZapisaOIznajmljivanju;
import model.ZapisOIznajmljivanju;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class IzmeniZapisSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof ZapisOIznajmljivanju)){
            throw new Exception("Sistem ne moze da doda zapis o iznajmljivanju");
        } 
        ZapisOIznajmljivanju z = (ZapisOIznajmljivanju) objekat;
        if (z.getDatumIznajmljivanja()== null)
            throw new Exception("GRESKA DATUM");
        if (z.getUkupanIznos() <0)
            throw new Exception("GRESKA UKUPAN IZNOS");
        if (z.getBibliotekar()== null || z.getBibliotekar().getIdBibliotekar() < 0)
            throw new Exception("GRESKA BIBLIOTEKAR");
        if (z.getCitalac()== null || z.getCitalac().getIdCitalac() < 0)
            throw new Exception("GRESKA CITALAC");
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        ZapisOIznajmljivanju zapis = (ZapisOIznajmljivanju)objekat;
        broker.edit(zapis);
        
        String uslov = " JOIN knjiga ON stavkazapisaoiznajmljivanju.idKnjiga=knjiga.idKnjiga WHERE stavkazapisaoiznajmljivanju.idZapis="+ zapis.getIdZapis();
        List<StavkaZapisaOIznajmljivanju> stareStavke = broker.getAll(new StavkaZapisaOIznajmljivanju(), uslov);
        for (StavkaZapisaOIznajmljivanju s : stareStavke) 
            broker.delete(s);
        
        List<StavkaZapisaOIznajmljivanju> noveStavke = zapis.getStavke();
        for (StavkaZapisaOIznajmljivanju s : noveStavke) {
            s.setZapis(zapis.getIdZapis());
            broker.add(s);
        }
    }
    
}
