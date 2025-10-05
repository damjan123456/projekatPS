/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DodajCitaocaForma;
import forme.FormaModovi;
import glavnikontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Citalac;
import model.Mesto;

/**
 *
 * @author damja
 */
public class DodajCitaocaController {
    private final DodajCitaocaForma dcf;
    private int id;

    public DodajCitaocaController(DodajCitaocaForma dcf) {
        this.dcf = dcf;
        addActionListener();
       
    }

    public void otvoriFormu(FormaModovi mod) {
        pripremiFormu(mod);
        dcf.setVisible(true);
        dcf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }

    private void pripremiFormu(FormaModovi mod) {
        if (mod == FormaModovi.DODAJ){
            dcf.getjButtonIzmeni().setEnabled(false);
            dcf.getjButtonDodaj().setEnabled(true);
            napuniCombo();
            dcf.getjTextFieldIme().setText("");
            dcf.getjTextFieldPrezime().setText("");
            dcf.getjTextFieldBrojTel().setText("");
            dcf.getjComboBoxMesta().setSelectedIndex(-1);
        }
        else{
            dcf.getjButtonIzmeni().setEnabled(true);
            dcf.getjButtonDodaj().setEnabled(false);
            
            Citalac c = (Citalac) GlavniKontroler.getInstanca().vratiParametar("Citalac");
            
            dcf.getjTextFieldIme().setText(c.getIme());
            dcf.getjTextFieldPrezime().setText(c.getPrezime());
            dcf.getjTextFieldBrojTel().setText(c.getBrojTel());
            id = c.getIdCitalac();
            napuniCombo();
            List<Mesto> mesta = Komunikacija.getInstanca().vratiMesta();
            for (Mesto m : mesta) {
                if (m.equals(c.getMesto())){
                    dcf.getjComboBoxMesta().setSelectedItem(m);
                    break;
                }
            }
            
            
            
        }
        
    }

    private void addActionListener() {
        dcf.dodajActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime =  dcf.getjTextFieldIme().getText().strip();
                String prezime = dcf.getjTextFieldPrezime().getText().strip();
                String brojTel = dcf.getjTextFieldBrojTel().getText().strip();
                Mesto mesto = (Mesto) (dcf.getjComboBoxMesta().getSelectedItem());
                Citalac citalac = new Citalac(ime,prezime,brojTel,mesto);
                
                try{
                    Komunikacija.getInstanca().unesiCitaoca(citalac);
                    JOptionPane.showMessageDialog(dcf, "Sistem je zapamtio citaoca","USPEH",JOptionPane.INFORMATION_MESSAGE);
                    FormaModovi mod = FormaModovi.DODAJ;
                    pripremiFormu(mod);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da zapamti citaoca","GRESKA",JOptionPane.ERROR_MESSAGE);
                }
            }    
        });
        
        dcf.izmeniActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime =  dcf.getjTextFieldIme().getText().strip();
                String prezime = dcf.getjTextFieldPrezime().getText().strip();
                String brojTel = dcf.getjTextFieldBrojTel().getText().strip();
                Mesto mesto = (Mesto) (dcf.getjComboBoxMesta().getSelectedItem());
                Citalac citalac = new Citalac(ime,prezime,brojTel,mesto);
                citalac.setIdCitalac(id);
                
                try{
                    Komunikacija.getInstanca().izmeniCitaoca(citalac);
                    JOptionPane.showMessageDialog(dcf, "Sistem je zapamtio citaoca","USPEH",JOptionPane.INFORMATION_MESSAGE);
                    dcf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da zapamti citaoca","GRESKA",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }
    
    private void napuniCombo(){
        List<Mesto> mesta = Komunikacija.getInstanca().vratiMesta();
        for (Mesto m : mesta) {
            dcf.getjComboBoxMesta().addItem(m);
        }
    }
}
