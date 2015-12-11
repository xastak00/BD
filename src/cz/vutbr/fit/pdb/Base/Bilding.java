/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.Base;

import cz.vutbr.fit.pdb.hlavni.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Prace  s tabukou BILDING
 * @author Olga
 */
public class Bilding extends Base {
    
    
     /**
     * Vratime bilding se zadanym ID
     * @param id
     * @return Klic je atribut bilding, hodnota je typu bilding. Vypisovat pomoci .toString()
     * @throws SQLException
     * @throws Exception
     */
    public Map<String,Object> get(int id) throws SQLException, Exception {
        
        Map<String,Object> map = new HashMap<>();
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM BILDING WHERE ID_BILDING = ?");
             )
        {
            stmt.setInt(1,id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {
                    map.put("ID_BILDING",id);
                    map.put("BILDING_COL",rs.getString("BILDING_COL"));
                }
                else {
                    return null;
                }
            }
        }
        
        return map; 
    
    }
    
    /**
     * Vrati seznam workers
     * @return Klic je ID_BILDING, hodnota je BILDING_COL
     * @throws SQLException
     */
    public Map<Integer, String> getList() throws SQLException {
        
        Map<Integer,String> listOfCustomers = new LinkedHashMap<>();
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM BILDING ORDER BY BILDING_COL");
             )
        {

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next()) {
                    listOfCustomers.put(rs.getInt("ID_BILDING"), rs.getString("ID_BILDING")+" "+rs.getString("BILDING_COL"));
                }
            }
        }
        
        return listOfCustomers;
    }
    
    /**
     * Vlozime noveho workera
     * @param nazev
     * @param type
     * @return
     * @throws SQLException
     */
    public int insert(String nazev, String type) throws SQLException {
    
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO WORKER (BILDING_COL, TYPE_BILDING_ID) VALUES(?,?)");
             )
        {
            stmt.setString(1,nazev);   
            stmt.setString(2,type);
            


            stmt.execute();
            
            try (Statement stmt2 = conn.createStatement();
                 ResultSet rs = stmt2.executeQuery("SELECT ID_BILDING FROM BILDING ORDER BY ID_BILDING DESC"))
            {
                rs.next();
                
                return rs.getInt("ID_BILDING");
            }
        }
    }
    
    
    
    
}
