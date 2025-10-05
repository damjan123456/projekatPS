/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DodajSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Sertifikat;

/**
 *
 * @author damja
 */
public class DodajSertifikatController {
    private final DodajSertifikatForma dsf;
    
    public DodajSertifikatController(DodajSertifikatForma dsf) {
        this.dsf = dsf;
        dsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    private void pripremi(){
        dsf.getjTextFieldInstitucija().setText("");
        dsf.getjTextFieldNaziv().setText("");
    }
    
    private void addActionListener() {
        dsf.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = dsf.getjTextFieldNaziv().getText().strip();
                String institucija = dsf.getjTextFieldInstitucija().getText().strip();
                Sertifikat sertifikat = new Sertifikat(ime,institucija);
                try{
                    Komunikacija.getInstanca().unesiSertifikat(sertifikat);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sertifikat");
                    pripremi();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti sertifikat");
                }
            }
        });
    }

    public void otvoriFormu() {
        dsf.setVisible(true);
    }
    
}
