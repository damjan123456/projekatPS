/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Bibliotekar;
import model.Citalac;
import model.Knjiga;
import model.Mesto;
import model.Sertifikat;
import model.StavkaZapisaOIznajmljivanju;
import model.ZapisOIznajmljivanju;
import operacija.bibliotekar.VratiBibliotekareSO;
import operacija.zapis.VratiZapiseSO;
import operacija.citalac.DodajCitaocaSO;
import operacija.citalac.IzmeniCitaocaSO;
import operacija.citalac.ObrisiCitaocaSO;
import operacija.citalac.VratiCitaoceSO;
import operacija.knjiga.VratiKnjigeSO;
import operacija.login.LoginSO;
import operacija.mesto.VratiMestaSO;
import operacija.sertifikat.DodajSertifikatSO;
import operacija.stavke.VratiStavkeSO;
import operacija.zapis.IzmeniZapisSO;
import operacija.zapis.KreirajZapisSO;

/**
 *
 * @author damja
 */
public class Controller {
    private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public Bibliotekar login(Bibliotekar b) throws Exception {
        LoginSO operacija = new LoginSO();
        operacija.izvrsi(b, null);
        System.out.println("Klasa Controller: " + operacija.getBibliotekar());
        return operacija.getBibliotekar();
    }

    public List<Citalac> vratiCitaoce() throws Exception {
        VratiCitaoceSO operacija = new VratiCitaoceSO();
        operacija.izvrsi(null, null);
        System.out.println("KLasa Controller: " + operacija.getCitaoci());
        return operacija.getCitaoci();
    }

    public void obrisiCitaoca(Citalac par) throws Exception {
        ObrisiCitaocaSO operacija = new ObrisiCitaocaSO();
        operacija.izvrsi(par, null);
        
    }

    public void dodajSertifikat(Sertifikat sertifikat) throws Exception {
        DodajSertifikatSO operacija = new DodajSertifikatSO();
        operacija.izvrsi(sertifikat, null);
        
    }

    public List<Mesto> vratiMesta() throws Exception {
        VratiMestaSO operacija = new VratiMestaSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller: " + operacija.getMesta());
        return operacija.getMesta();
    }

    public void dodajCitaoca(Citalac citalac) throws Exception {
        DodajCitaocaSO operacija = new DodajCitaocaSO();
        operacija.izvrsi(citalac, null);
    }

    public void izmeniCitaoca(Citalac citalac) throws Exception {
        IzmeniCitaocaSO operacija = new IzmeniCitaocaSO();
        operacija.izvrsi(citalac, null);
    }

    public List<ZapisOIznajmljivanju> vratiZapise() throws Exception {
        VratiZapiseSO operacija = new VratiZapiseSO();
        operacija.izvrsi(null, null);
        System.out.println("KLasa Controller: " + operacija.getZapisi());
        return operacija.getZapisi();    
    }

    public List<StavkaZapisaOIznajmljivanju> vratiStavke(StavkaZapisaOIznajmljivanju stavka) throws Exception {
        VratiStavkeSO operacija = new VratiStavkeSO();
        operacija.izvrsi(stavka, null);
        System.out.println("Klasa Controller: " + operacija.getStavke());
        return operacija.getStavke();
    }

    public List<Knjiga> vratiKnjige() throws Exception {
        VratiKnjigeSO operacija = new VratiKnjigeSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller: " + operacija.getKnjige());
        return operacija.getKnjige();
    }

    public void kreirajZapisOIznajmljivanju(ZapisOIznajmljivanju zapis) throws Exception {
        KreirajZapisSO operacija = new KreirajZapisSO();
        operacija.izvrsi(zapis, null);
    }

    public void izmeniZapis(ZapisOIznajmljivanju zapis) throws Exception {
        IzmeniZapisSO operacija = new IzmeniZapisSO();
        operacija.izvrsi(zapis, null);
    }

    public List<Bibliotekar> vratiBibliotekare() throws Exception {
        VratiBibliotekareSO operacija = new VratiBibliotekareSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller: " + operacija.getBibliotekari());
        return operacija.getBibliotekari();
    }
    
}
