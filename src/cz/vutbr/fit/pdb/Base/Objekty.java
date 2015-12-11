/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.Base;


import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;





/**
 * Zobrazeni obrazku
 * @author Olga
 */
public class Objekty extends JLabel{ //http://www.javadocexamples.com/javax/swing/JLabel/setVerticalTextPosition(int%20textPosition).html
    
    private boolean active = false;
    private int index;
    private String cesta = null;
    private boolean focusable = true;
    private ImageIcon im; 
    private Double score = 0.0;
    
    //Konstruktor
    public Objekty(){
        addMouseListener(new java.awt.event.MouseAdapter() { //https://www.cis.upenn.edu/~bcpierce/courses/629/jdkdocs/api/java.util.EventObject.html#_top_
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               // ObjektyMouseClicked(evt);
            }
        });
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        setText("");
        //setSize(60, 60);
    }
    
     /**
     * Konstruktor, ktery nastavi navic tridni promennou im
     * @param o je obrazek
     */
    public Objekty(ImageIcon o){
       addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ObrazkyMouseKlick(evt);
            }
        });
        setText("");
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        im = o;
    }
    
    /**
     * Nastavime aktualni obrazek
     * @param o je obrazek
     */
    public void setNewImage(ImageIcon o){
        im = o;
    }
    
     /**
     * Zpracovani kliku na obrazek
     * @param evt event
     */
    public void ObrazkyMouseKlick(java.awt.event.MouseEvent evt){
        //System.out.println("Klik obrazky");
        if(focusable) {
            if(!active){
                setBorder(BorderFactory.createLineBorder(Color.yellow,8));
                active = true;
            } else {
                active = false;
                setBorder(null);
            }
        }
    }
    
     /**
     * Nastaveni zavislosti
     * @return focusable
     */
    public boolean Focus() {
        return focusable;
    }
    
    /**
     * Nastavi focusable atribut
     * @param f nastaveni
     */
    public void setFocus(boolean f) {
        focusable = f;
    }
    
    /**
     * 
     * @return vraci zda je aktivni obrazek
     */
    public boolean Active(){
        return active;
    }

 /**
     * Nastavi index obrazku s DataBaze
     * @param i index
     */
    public void setIndex(int i) {
        this.index = i;
    }
    
    /**
     * Vrati 
     * @return index obrazku
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Nastavi cestu pro nacteni obrazku do DataBaze
     * @param c cesta
     */
    public void setCesta(String c){
        this.cesta = c;
    }
    
    /**
     * Vraci 
     * @return cesta
     */
    public String getCesta(){
        return this.cesta;
    }
    
    /**
     * Vrati 
     * @return aktualni zekobr
     */
    public ImageIcon getObjekty(){
        return this.im;
    }
    
    /**
     * Nastavime pro porovnani obrazku
     * @param s skore
     */
    public void setScore(Double s){
        this.score = s;
    }
    
    /**
     * Vrati 
     * @return skore
     */
    public Double getScore(){
        return this.score;
    }
    
    /**
     * Vrati skore jako string
     * @return skore
     */
    public String getScoreAsString(){
        return String.valueOf(this.score);
    }
    
    /**
     * Nastavi,ze obrazek je aktivni
     * @param isActive aktivni
     */
    public void setActive (boolean isActive) {
        this.active = isActive;
    }

  
   
}
    
    
    
    
    
    
    
    
    
    

