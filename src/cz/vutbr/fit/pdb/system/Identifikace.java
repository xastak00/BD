/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.system;

import cz.vutbr.fit.pdb.hlavni.DataBase;
import java.util.Properties;

/**
 *
 * @author Iuliia
 */
public class Identifikace {
     private Identifikace1 identity = null;
     
     public Identifikace1 getIdentifikace() {
        if (identity == null) {
            Properties current_properties = DataBase.getProperties();
            identity = new Ident (current_properties.getProperty("DB.LOGIN"), 
                    current_properties.getProperty("DB.PASSWORD"));
        }
        return identity;
}
}
