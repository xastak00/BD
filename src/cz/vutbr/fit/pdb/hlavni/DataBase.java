/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.hlavni;



import cz.vutbr.fit.pdb.system.Identifikace;
import cz.vutbr.fit.pdb.system.Identifikace1;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;


/**
 *Tovarna na vytvareni instanci pripojeni k databazi
 * @author Iuliia
 */
public class DataBase {
    
private static Properties properties = null;
private static Identifikace identifikace = null;

  public static OracleDataSource getConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        Identifikace1 identity = (Identifikace1) DataBase.getConnection();
        String connectionString = "jdbc:oracle:thin:@gort.fit.vutbr.cz:1521:dbgort";
        ods.setURL(connectionString);

        ods.setUser(identity.getUsername());
        ods.setPassword(identity.getPassword());
        return ods;
    }
    public static Properties getProperties() {
        if (DataBase.properties == null) {
            DataBase.properties = new Properties();
        }
        return DataBase.properties;

    }
       public static Identifikace getIdentifikace() {
        if (DataBase.identifikace  == null) {
            DataBase.identifikace = new Identifikace();
        }
        return DataBase.identifikace;

}
}