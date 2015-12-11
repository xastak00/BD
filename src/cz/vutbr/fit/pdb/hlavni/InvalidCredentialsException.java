/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.hlavni;

/**
 *
 * @author Olga
 */
public class InvalidCredentialsException extends Exception {
    /**
     * @param message zprava popisuji, odkud pochazi nevalidni prihlasovaci udaje
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
