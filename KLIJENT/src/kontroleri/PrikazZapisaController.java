/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.FormaModovi;
import forme.PrikazZapisaForma;
import forme.model.ModelTabeleStavke;
import forme.model.ModelTabeleZapisi;
import glavnikontroler.GlavniKontroler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.StavkaZapisaOIznajmljivanju;
import model.ZapisOIznajmljivanju;

/**
 *
 * @author damja
 */
public class PrikazZapisaController {
    private final PrikazZapisaForma pzf;

    public PrikazZapisaController(PrikazZapisaForma pzf) {
        this.pzf = pzf;
        pzf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
        addMouseListener();
        
       
    }

    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    private void pripremiFormu() {
        pzf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        List<ZapisOIznajmljivanju> zapisi = Komunikacija.getInstanca().vratiZapise();
        ModelTabeleZapisi mtz = new ModelTabeleZapisi(zapisi);
        pzf.getjTableZapisi().setModel(mtz);
        
        List<StavkaZapisaOIznajmljivanju> stavke = new ArrayList<>();
        ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
        pzf.getjTableStavke().setModel(mts);
        
    }

    private void addActionListener() {
    }


    private void addMouseListener() {
        pzf.addKlikniMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pzf.getjTableZapisi().getSelectedRow();
                if (red != -1){
                    ModelTabeleZapisi mtz = (ModelTabeleZapisi) pzf.getjTableZapisi().getModel();
                    ZapisOIznajmljivanju z = mtz.getLista().get(red);
                    StavkaZapisaOIznajmljivanju s = new StavkaZapisaOIznajmljivanju();
                    s.setZapis(z.getIdZapis());
                    try{
                        List<StavkaZapisaOIznajmljivanju> stavke = Komunikacija.getInstanca().vratiStavke(s);
                        ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
                        pzf.getjTableStavke().setModel(mts);
                        JOptionPane.showMessageDialog(pzf, "Sistem je nasao zapis o iznajmljivanju","USPEH",JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception exc){JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje zapis o iznajmljivanju","GRESKA",JOptionPane.ERROR_MESSAGE);}
                }
                
            }
        });
        pzf.addIzmeniActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZapisi().getSelectedRow();
                if (red == -1)
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje zapis o iznajmljivanju");
                else{
                    ModelTabeleZapisi mtz = (ModelTabeleZapisi) pzf.getjTableZapisi().getModel();
                    ZapisOIznajmljivanju zapis = mtz.getLista().get(red);
                    
                    StavkaZapisaOIznajmljivanju stavka = new StavkaZapisaOIznajmljivanju();
                    stavka.setZapis(zapis.getIdZapis());
                    List<StavkaZapisaOIznajmljivanju> stavke = Komunikacija.getInstanca().vratiStavke(stavka);
                    zapis.setStavke(stavke);
                    
                    GlavniKontroler.getInstanca().dodajParametar("ZapisIzmena", zapis);
                    GlavniKontroler.getInstanca().otvoriGlavnuFormu(FormaModovi.IZMENI);
                }
            }
            
        
        });
        pzf.addDugmePretraziActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                osvezi();
                String citalac =  pzf.getjTextFieldCitalac().getText().strip();
                String bibliotekar = pzf.getjTextFieldBibliotekar().getText().strip();
                
                ModelTabeleZapisi mtz = (ModelTabeleZapisi) pzf.getjTableZapisi().getModel();
                boolean nasao = mtz.pretrazi(citalac,bibliotekar);
                if (nasao)
                    JOptionPane.showMessageDialog(pzf, "Sisem je nasao zapise o iznajmljivanju po zadatim kriterijumima","USPEH",JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(pzf, "Sisem ne moze da nadje zapise o iznajmljivanju po zadatim kriterijumima","GRESKA",JOptionPane.ERROR_MESSAGE);
            }

            private void osvezi() {
                List<ZapisOIznajmljivanju> zapisi = Komunikacija.getInstanca().vratiZapise();
                ModelTabeleZapisi mtz = new ModelTabeleZapisi(zapisi);
                pzf.getjTableZapisi().setModel(mtz);
            }
            
        });
        
    }
    
}
