/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import model.Bibliotekar;
import model.Citalac;
import model.Knjiga;
import model.Mesto;
import model.Sertifikat;
import model.StavkaZapisaOIznajmljivanju;
import model.ZapisOIznajmljivanju;

/**
 *
 * @author damja
 */
public class ObradaKlijentskihZahteva extends Thread {
    private final Socket socket;
    private final Posiljalac posiljalac;
    private final Primalac primalac;
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while(!kraj){
            try {   
                Zahtev zahtev = (Zahtev) primalac.primi();
                if (zahtev == null){
                    prekiniNit();
                    break;
                }
                Odgovor odgovor = new Odgovor();
                switch(zahtev.getOperacija()){
                    case LOGIN -> {
                        Bibliotekar b = (Bibliotekar) zahtev.getParametar();
                        b = Controller.getInstance().login(b);
                        odgovor.setOdgovor(b);
                        }
                    case VRATI_CITAOCE -> {
                        List<Citalac> citaoci = Controller.getInstance().vratiCitaoce();
                        odgovor.setOdgovor(citaoci);
                        }
                    case OBRISI_CITAOCA -> {
                        try{
                            Citalac citalac = (Citalac) zahtev.getParametar(); 
                            Controller.getInstance().obrisiCitaoca(citalac);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                        }
                    case UNESI_SERTIFIKAT -> {
                        try{
                            Sertifikat sertifikat = (Sertifikat) zahtev.getParametar();
                            Controller.getInstance().dodajSertifikat(sertifikat);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                        }
                    case VRATI_MESTA -> {
                        List<Mesto> mesta = Controller.getInstance().vratiMesta();
                        odgovor.setOdgovor(mesta);
                        }
                    case UNESI_CITAOCA ->{
                        try{
                            Citalac citalac = (Citalac) zahtev.getParametar();
                            Controller.getInstance().dodajCitaoca(citalac);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                    }
                    case IZMENI_CITAOCA ->{
                        try{
                            Citalac citalac = (Citalac) zahtev.getParametar();
                            Controller.getInstance().izmeniCitaoca(citalac);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                    }
                    case VRATI_ZAPISE ->{
                        List<ZapisOIznajmljivanju> zapisi = Controller.getInstance().vratiZapise();
                        odgovor.setOdgovor(zapisi);
                    }
                    case VRATI_STAVKE ->{
                        StavkaZapisaOIznajmljivanju s = (StavkaZapisaOIznajmljivanju) zahtev.getParametar();
                        List<StavkaZapisaOIznajmljivanju> stavke = Controller.getInstance().vratiStavke(s);
                        odgovor.setOdgovor(stavke);
                    }
                    case VRATI_KNJIGE ->{
                        List<Knjiga> knjige = Controller.getInstance().vratiKnjige();
                        odgovor.setOdgovor(knjige);
                    }
                    case KREIRAJ_ZAPIS ->{
                        try{
                            ZapisOIznajmljivanju zapis = (ZapisOIznajmljivanju) zahtev.getParametar();
                            Controller.getInstance().kreirajZapisOIznajmljivanju(zapis);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                    }
                    case IZMENI_ZAPIS ->{
                        try{
                            ZapisOIznajmljivanju zapis = (ZapisOIznajmljivanju) zahtev.getParametar();
                            Controller.getInstance().izmeniZapis(zapis);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){odgovor.setOdgovor(e);}
                    }
                    case VRATI_BIBLIOTEKARE ->{
                        List<Bibliotekar> bibliotekari = Controller.getInstance().vratiBibliotekare();
                        odgovor.setOdgovor(bibliotekari);
                    }
                    default -> System.out.println("TA OPERACIJA NE POSTOJI");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
    public void prekiniNit(){
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
 
}
