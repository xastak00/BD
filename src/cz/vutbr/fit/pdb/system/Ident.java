/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.system;

/**
 *
 * @author Iuliia
 */
public class Ident extends BaseIdentity implements Identifikace1 {
     /**
     * 
     * @param username
     * @param password
     */
    public Ident(String username, String password) {
        super(username, password);
    }

    /**
     *
     * @return
     */

    @Override
    public boolean isLoggendIn() {
        return false;
    }
}
