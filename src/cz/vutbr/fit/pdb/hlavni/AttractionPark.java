/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.hlavni;

/**
 *Hlavni trida zajistujici vykreslení hlavniho okna
 * @author Iuliia Ogloblina
 */
public class AttractionPark {
    
/*
    * Hlavni main pro spusteni aplikace
    * @author Iuliia Ogloblina
    */
 public static void main(String[] args) {
        ParkMainPanel park = new ParkMainPanel();
        park.setLocationRelativeTo(null);
        park.setVisible(true); /* метод, с помощью которого задается видимость */
    }
}