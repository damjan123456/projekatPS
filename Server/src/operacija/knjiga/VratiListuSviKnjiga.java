/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjiga;

import java.util.List;
import model.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author damja
 */
public class VratiListuSviKnjiga extends ApstraktnaGenerickaOperacija {
    List<Knjiga> knjige;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        knjige = broker.getAll(new Knjiga(), null);
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
}
