/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class config {
    private Properties properties;

    /**
     * Zjisti, zda existuje konfiguracni soubor s udaji pro pripojeni defaultniho uzivatele
     * @return
     */
    public static boolean existsLocalConfig() {
        config loader = new config();
        if (loader.getClass().getResourceAsStream("config.local.properties") == null) {
            return false;
        }
        return true;
    }

    /**
     * Zpracovani souboru s konfiguracemi.
     * Nejprve se zpracuje soubor se zakladni konfiguraci a pote
     * jsou prepsana lokalne zavisla data ze souboru config.local.properties
     * @return
     */
    public Properties getProperties() {
        if (properties == null) {
            Properties prop1 = parsePropertiesFrom("config.properties");
            Properties prop2 = parsePropertiesFrom("config.local.properties");

            prop1.putAll(prop2);

            properties = prop1;
        }
        return properties;
    }

    private Properties parsePropertiesFrom(String configFileName) {
        Properties prop = new Properties();
        try {
            if (getClass().getResourceAsStream(configFileName) != null) {
                prop.load(getClass().getResourceAsStream(configFileName));
            }
        } catch (IOException ex) {
            Logger.getLogger(config.class
                    .getName()).log(Level.SEVERE, configFileName + " not found", ex);
            prop.setProperty(
                    "HOST", "gort.fit.vutbr.cz");
            prop.setProperty(
                    "PORT", "1521");
            prop.setProperty(
                    "SID", "dbgort");
        }
        return prop;
    }
}
    

