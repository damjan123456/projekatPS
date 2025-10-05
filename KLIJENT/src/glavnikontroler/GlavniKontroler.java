/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glavnikontroler;

import forme.DodajCitaocaForma;
import forme.DodajSertifikatForma;
import forme.DodajZapisForma;
import forme.FormaModovi;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazCitalacaForma;
import forme.PrikazZapisaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajCitaocaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazCitalacaController;
import kontroleri.DodajSertifikatController;
import kontroleri.PrikazZapisaController;
import model.Bibliotekar;

/**
 *
 * @author damja
 */
public class GlavniKontroler {
    private static GlavniKontroler instanca;
    private Bibliotekar ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazCitalacaController prikazCitalacaController;
    private DodajSertifikatController sertifikatController;
    private DodajCitaocaController dodajCitaocaController;
    private PrikazZapisaController prikazZapisaController;
    private Map<String,Object> parametri;
    
    private GlavniKontroler() {
        parametri = new HashMap<>();
    }
    public static GlavniKontroler getInstanca(){
        if (instanca == null)
            instanca = new GlavniKontroler();
        return instanca;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
                
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    
    public void otvoriPrikazCitalacaFormu() {
        prikazCitalacaController = new PrikazCitalacaController(new PrikazCitalacaForma());
        prikazCitalacaController.otvoriFormu();
    }
    
    public void otvoriDodajSertifikatFormu() {
        sertifikatController = new DodajSertifikatController(new DodajSertifikatForma());
        sertifikatController.otvoriFormu();
    }
    
    public void otvoriDodajCitaocaFormu() {
        dodajCitaocaController = new DodajCitaocaController(new DodajCitaocaForma());
        dodajCitaocaController.otvoriFormu(FormaModovi.DODAJ);
    }
    
    public void otvoriIzmeniCitaocaFormu() {
        dodajCitaocaController = new DodajCitaocaController(new DodajCitaocaForma());
        dodajCitaocaController.otvoriFormu(FormaModovi.IZMENI);
    }
    
    public void otvoriPrikazZapisaFormu() {
        prikazZapisaController = new PrikazZapisaController(new PrikazZapisaForma());
        prikazZapisaController.otvoriFormu();
    }


    public Bibliotekar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Bibliotekar ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void dodajParametar(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParametar(String s){
        return parametri.get(s);
    }

    public void osveziFormu() {
        prikazCitalacaController.osvezi();
    }

    public void otvoriGlavnuFormu(FormaModovi formaMod) {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu(formaMod);
    }

}
