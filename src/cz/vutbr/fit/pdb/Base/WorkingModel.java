/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.Base;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

/**
 *
 * @author Iuliia
 */
public class WorkingModel extends Base{
    
        /**
     * Vrací working v zadaném období pro zvolený pokoj.
     * @param datum_od Ve formátu YYYY-mm-dd
     * @param datum_do Ve formátu YYYY-mm-dd
     * @param pokoj
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    public Map<Integer, Map<String, Object>> getWorkingVObdobi(String datum_od, String datum_do, Integer bilding) throws SQLException, ParseException {
        String[] pokoje = {bilding.toString()};
        return getWorkingVObdobi(datum_od, datum_do, bilding);
    }
    
    
    
}
