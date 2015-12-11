/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.interf;

import cz.vutbr.fit.pdb.Base.Bilding;
import cz.vutbr.fit.pdb.Base.Obrazky;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;




/**
 * Zobrazovani obrazku se stavbami
 * @author Olga
 */
public class SeznamObjektu extends javax.swing.JPanel {

    private ParkMainPanel1 mainPanel;
    
    public void setParkMainPanel(ParkMainPanel1 mainPanel) {
        this.mainPanel = mainPanel;
    }
    /**
     * Creates new form SeznamObjektu
     * Konstruktor
     */
    public SeznamObjektu() {
        initComponents();
        Inicializace();
    }
    
      private void Inicializace() {
       Obraz = new Obrazky();
        fc = new JFileChooser();
        
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")
                        || file.getName().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "Only jpeg & png files";
            }
        });   
        
         iconList = new ArrayList<>();
         defaultSearchDir = "src/images/";
        
        
        
      } 
    /**
     * Obnovime obrazky
     */
        private Map<Integer, Objekty> updateImages(int Bilding){
        Map<Integer, Objekty> result = null;
        try {
            result =  Obraz.getImagesObjektu(Bilding);
        } catch (SQLException e) {
            Logger.getLogger(SeznamObjektu.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return result;
    }
    
     /**
     * Nastavime novy obrazek
     * @param o obrazek
     * @param nula pokud neni zadny obrazek 
     */
    public void setNewImage (ImageIcon o, boolean nula){
        if(o != null){
            if(nula) {
                obrazek.setText("No IMAGE");
                obrazek.setFocus(false);
                Index.setText("Index: 0/0");
            } else {
                obrazek.setText("");
                obrazek.setFocus(true);
            }
            obrazek.setVisible(true);
            obrazek.setIcon(o);
            obrazek.setIndex(Bilding);
            Kontejner.add(obrazek);
            Kontejner.revalidate();
        }
    }
 
    /**
     * Zmena indexu
     */
    private void getPristiImage() {
        if(AktualniObrazky != null) {
            if(List!=null && !List.isEmpty() && aktIndex < List.size()-1) {
                aktIndex += 1;
                Map.Entry<Integer, Objekty> novyItem = List.get(aktIndex);
                o = novyItem.getValue().getObjekty();
                Smazat.setEnabled(true);
                Otocit.setEnabled(true);
                obrazek.setIndex(novyItem.getKey());
                setNewImage(o, false);
                Index.setText("Index: "+(aktIndex+1)+"/"+List.size());
            }
        }
    }
    
     /**
     * Zmena indexu
     */
    private void getMinulyImage() {
        if(AktualniObrazky != null) {
            if(List!=null && !List.isEmpty() && aktIndex > 0) {
                aktIndex -= 1;
                Map.Entry<Integer, Objekty> novyItem = List.get(aktIndex);
                o = novyItem.getValue().getObjekty();
                Smazat.setEnabled(true);
                Otocit.setEnabled(true);
                obrazek.setIndex(novyItem.getKey());
                setNewImage(o, false);
                Index.setText("Index: "+(aktIndex+1)+"/"+List.size());
            } 
        }
    }
    
     /**
     * Obnovime seznam combboxu - on nacte znova data z db
     */
    public void updateCombo(){
        bilding_dbId = new HashMap<>();
        Bilding stavba = new Bilding();

        try {
            int i = 0;
            Map<Integer, String> list = stavba.getList();

            String[] items = new String[list.size() + 1];
            items[i++] = "";
            for (Map.Entry<Integer, String> entry : list.entrySet()) {
                items[i] = entry.getValue();
                bilding_dbId.put(entry.getKey(), i);
                i++;
            }
            ComBox = items;
        } catch (SQLException e) {
            ComBox = new String[]{"chyba při načítání.."};
        }
        ComboBox.setModel(new DefaultComboBoxModel(ComBox));
        ComboBox.setSelectedIndex(0);
        obrazek.setVisible(false);
        obrazek.setIcon(null);
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kontejner = new javax.swing.JPanel();
        jScrollBar1 = new javax.swing.JScrollBar();
        Smazat = new javax.swing.JButton();
        Pridat = new javax.swing.JButton();
        Index = new javax.swing.JLabel();
        Otocit = new javax.swing.JButton();
        ComboBox = new javax.swing.JComboBox();
        Sprava = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Podobne = new javax.swing.JButton();

        javax.swing.GroupLayout KontejnerLayout = new javax.swing.GroupLayout(Kontejner);
        Kontejner.setLayout(KontejnerLayout);
        KontejnerLayout.setHorizontalGroup(
            KontejnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KontejnerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        KontejnerLayout.setVerticalGroup(
            KontejnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        Smazat.setText("Smazat");
        Smazat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmazatActionPerformed(evt);
            }
        });

        Pridat.setText("Přidat");
        Pridat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PridatActionPerformed(evt);
            }
        });

        Index.setText("Index 0/0");

        Otocit.setText("Otočit");
        Otocit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtocitActionPerformed(evt);
            }
        });

        ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Vyberte obrázek, levym tlačítkem myši.");

        javax.swing.GroupLayout SpravaLayout = new javax.swing.GroupLayout(Sprava);
        Sprava.setLayout(SpravaLayout);
        SpravaLayout.setHorizontalGroup(
            SpravaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SpravaLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel1)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        SpravaLayout.setVerticalGroup(
            SpravaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        Podobne.setText("Podobné");
        Podobne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PodobneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Sprava, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Kontejner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Index, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Podobne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Otocit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Pridat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Smazat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Index, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addComponent(Kontejner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sprava, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Smazat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Otocit)
                    .addComponent(Pridat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Podobne))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SmazatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmazatActionPerformed
        iter = iconList.listIterator();
        Objekty tmp;
        while (iter.hasNext()) {
            tmp = iter.next();
            if (tmp.Active()) {
                tmp.setVisible(false);
                tmp.setIcon(null);
                iter.remove();
            }
        }                     
    }//GEN-LAST:event_SmazatActionPerformed
   
    private void OtocitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtocitActionPerformed
        // TODO add your handling code here:       
         if(obrazek.Active()){
            try {
                Sprava.setVisible(false);
                Obraz.rotateImage(obrazek.getIndex());
                ImageIcon im = new ImageIcon(Obraz.getImage(obrazek.getIndex()));
                obrazek.setNewImage(im);
                setNewImage(im, false);
                Map.Entry<Integer, Objekty> en = new AbstractMap.SimpleEntry<Integer, Objekty>(obrazek.getIndex(), obrazek);
                AktualniObrazky = updateImages(Bilding);
                List = new ArrayList<>(AktualniObrazky.entrySet());
                List.set(aktIndex, en);
            } catch (SQLException e) {
                Logger.getLogger(SeznamObjektu.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Sprava.setVisible(true);
            Sprava.setBackground(Color.red);
        }
    }//GEN-LAST:event_OtocitActionPerformed

    private void ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox) evt.getSource();
        String ComBox = (String) cb.getSelectedItem();
        List = null;
        Sprava.setVisible(false);
        if(!ComBox.equals("")){
            String substring = ComBox.substring(0, ComBox.indexOf(" "));
            Bilding = Integer.parseInt(substring);
            System.out.println(Bilding);
            boolean nula = false;
            AktualniObrazky = updateImages(Bilding);
            if(AktualniObrazky.isEmpty()) {
                System.out.println("NULL");
                String path = "/images/Null.png";
                o = new ImageIcon(getClass().getResource(path));
                Smazat.setEnabled(false);
                Otocit.setEnabled(false);
                nula = true;
            } else {
                
                List = new ArrayList<>(AktualniObrazky.entrySet());
                aktIndex = 0;
                Map.Entry<Integer, Objekty> entry = List.get(aktIndex);
                o = entry.getValue().getObjekty();
                obrazek.setIndex(entry.getKey());
                Smazat.setEnabled(true);
                Otocit.setEnabled(true);
                Index.setText("Index: "+(aktIndex+1)+"/"+(List.size()));
            }
            setNewImage(o,nula);
        } else {
            obrazek.setVisible(false);
            obrazek.setIcon(null);
            obrazek.setText("");
            Index.setText("Index: 0/0");
        }
    }//GEN-LAST:event_ComboBoxActionPerformed

    private void PodobneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PodobneActionPerformed
                if(obrazek.Active()){
            Sprava.setVisible(false);
            JFrame frame = new JFrame("Nejpodobnejsi vysledky");
            ViewObjektu vv = new ViewObjektu(obrazek.getIndex());
            frame.add(vv);
            frame.pack();
            frame.setVisible(true);
        } else {
            Sprava.setVisible(true);
            Sprava.setBackground(Color.red);
        }
    }//GEN-LAST:event_PodobneActionPerformed

    private void PridatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PridatActionPerformed
            fc.setCurrentDirectory(new File(defaultSearchDir));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(fc);
        Objekty im = null;
        boolean load = true;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                System.out.println(file.getAbsoluteFile());
                try {
                    o = new ImageIcon(file.getCanonicalPath().toString());
                } catch (IOException e) {
                    Logger.getLogger(SeznamObjektu.class.getName()).log(Level.SEVERE, null, e);
                }
                im = new Objekty();
                im.setPreferredSize(new Dimension(60, 60));
                im.setCesta(file.getCanonicalPath().toString());
            } catch (IOException e) {
                Logger.getLogger(SeznamObjektu.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            System.out.println("Cancelled by user.");
            load = false;
        }
        if (o != null && load) {
            //Jen pro zobrazeni, stejne se do DB ulozi cely obrazek
            Image img = o.getImage();
            Image scaledImg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
            im.setIcon(new ImageIcon(scaledImg));
            iconList.add(im);
            Kontejner.add(iconList.get(iconList.size() - 1));
            Kontejner.revalidate();
        }
    }//GEN-LAST:event_PridatActionPerformed
    private JFileChooser fc;
    private ImageIcon o;
    private String defaultSearchDir;
    private List<Objekty> iconList;
    private Obrazky Obraz;  
    private Objekty obrazek;
    private Map<Integer, Objekty> AktualniObrazky;
    private int Bilding;
    private List<Map.Entry<Integer, Objekty>> List;
    private Integer aktIndex;
    private String[] ComBox;
    private Map<Integer, Integer> bilding_dbId;
    private ListIterator<Objekty> iter;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox;
    private javax.swing.JLabel Index;
    private javax.swing.JPanel Kontejner;
    private javax.swing.JButton Otocit;
    private javax.swing.JButton Podobne;
    private javax.swing.JButton Pridat;
    private javax.swing.JButton Smazat;
    private javax.swing.JPanel Sprava;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollBar jScrollBar1;
    // End of variables declaration//GEN-END:variables

  
}
