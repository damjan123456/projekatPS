/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.StavkaZapisaOIznajmljivanju;
import model.ZapisOIznajmljivanju;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class PromeniZapisOIznajmljivanju extends ApstraktnaGenerickaOperacija {

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
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        ZapisOIznajmljivanju zapis = (ZapisOIznajmljivanju)objekat;
        broker.edit(zapis);

        String uslov = " JOIN knjiga ON stavkazapisaoiznajmljivanju.idKnjiga=knjiga.idKnjiga WHERE stavkazapisaoiznajmljivanju.idZapis=" + zapis.getIdZapis();
        List<StavkaZapisaOIznajmljivanju> stareStavke = broker.getAll(new StavkaZapisaOIznajmljivanju(), uslov);

//        List<StavkaZapisaOIznajmljivanju> zaBrisanje = new ArrayList<>(stareStavke);
//
//        for (StavkaZapisaOIznajmljivanju novaStavka : zapis.getStavke()) {
//            novaStavka.setZapis(zapis.getIdZapis());
//            boolean postoji = false;
//            for (StavkaZapisaOIznajmljivanju stara : stareStavke) {
//                if(novaStavka.equals(stara)){
//                    postoji = true;
//                    broker.edit(novaStavka);
//                    zaBrisanje.remove(stara);
//                    break; 
//                }
//            }
//            if(!postoji) {
//                broker.add(novaStavka);
//            }
//        }
//        
//        for (StavkaZapisaOIznajmljivanju s : zaBrisanje) {
//            broker.delete(s);
//        }
        Map<String, StavkaZapisaOIznajmljivanju> mapaStarih = new HashMap<>();
        for (StavkaZapisaOIznajmljivanju s : stareStavke) {
            String key = s.getZapis() + "-" + s.getRb(); 
            mapaStarih.put(key, s);
        }

        for (StavkaZapisaOIznajmljivanju nova : zapis.getStavke()) {
            nova.setZapis(zapis.getIdZapis());
            String key = nova.getZapis() + "-" + nova.getRb();
            if (mapaStarih.containsKey(key)) {
                broker.edit(nova);
                mapaStarih.remove(key);
            } else {
                broker.add(nova);
            }
        }

        for (StavkaZapisaOIznajmljivanju s : mapaStarih.values()) {
            broker.delete(s);
        }


    }
    
}
