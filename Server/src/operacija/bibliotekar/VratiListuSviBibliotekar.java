/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.bibliotekar;

import java.util.List;
import model.Bibliotekar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class VratiListuSviBibliotekar extends ApstraktnaGenerickaOperacija {
    List<Bibliotekar> bibliotekari;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        bibliotekari = broker.getAll(new Bibliotekar(), null);
    }

    public List<Bibliotekar> getBibliotekari() {
        return bibliotekari;
    }
}
