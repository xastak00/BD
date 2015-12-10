/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.system;

import cz.vutbr.fit.pdb.Base.ReloadDatabaseModel;
import cz.vutbr.fit.pdb.hlavni.DataBase;
import cz.vutbr.fit.pdb.hlavni.InvalidCredentialsException;
import java.util.Properties;

/**
 *
 * @author Iuliia
 */
public class Identifikace {
     private Identifikace1 identity = null;
     
     public Identifikace1 getIdentity() {
        if (identity == null) {
            Properties current_properties = DataBase.getProperties();
            identity = new Ident (current_properties.getProperty("DB.LOGIN"), 
                    current_properties.getProperty("DB.PASSWORD"));
        }
        return identity;
}
     
     /**
     *
     * @param username
     * @param password
     * @return
     * @throws InvalidCredentialsException
     */
    public Identifikace1 login(String username, String password) throws InvalidCredentialsException {

        identity = new Identity(username, password);
        if (!ReloadDatabaseModel.isConnectionValid()) {
            logout();
            throw new InvalidCredentialsException("Neplatné uživatelské jméno nebo heslo.");
        }
        return getIdentity(); 
    }

  public void logout() {
        identity = null;
}
}
