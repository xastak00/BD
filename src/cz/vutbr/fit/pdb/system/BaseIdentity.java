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
public abstract class BaseIdentity implements Identifikace1 {
    private String username;
    private String password;

    /**
     *
     * @param username
     * @param password
     */
    public BaseIdentity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
