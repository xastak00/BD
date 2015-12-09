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
import java.util.HashMap;//https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
import java.util.LinkedHashMap;
import java.util.Map;//https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html
import oracle.jdbc.pool.OracleDataSource;

/**
 * Prace s tabulkou WORKER
 * @author Olga
 */
public class Worker extends Base {
    
      /**
     * Vrati workera se zadanym ID
     * @param id
     * @return Klic je atribut workera, hodnota je typu object. Vypisovat pomocí .toString()
     * @throws SQLException
     * @throws Exception
     */
    public Map<String,Object> get(int id) throws SQLException, Exception {
        
        Map<String,Object> map = new HashMap<>();
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WORKER WHERE ID_WORKER = ?");
             )
        {
            stmt.setInt(1,id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {
                    map.put("ID_WORKER",id);
                    map.put("FIRST_NAME",rs.getString("FIRST_NAME"));
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
     * @return Klic je ID_WORKER, hodnota je FIRST_NAME
     * @throws SQLException
     */
    public Map<Integer, String> getList() throws SQLException {
        
        Map<Integer,String> listOfCustomers = new LinkedHashMap<>();
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WORKER ORDER BY FIRST_NAME");
             )
        {

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next()) {
                    listOfCustomers.put(rs.getInt("ID_WORKER"), rs.getString("ID_WORKER")+" "+rs.getString("FIRST_NAME"));
                }
            }
        }
        
        return listOfCustomers;
    }
    
    /**
     * Vlozime noveho workera
     * @param jimeno
     * @param prijmeni
     * @param profese
     * @param telefon
     * @return
     * @throws SQLException
     */
    public int insert(String jimeno, String prijmeni, String profese, String telefon) throws SQLException {
    
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO WORKER (FIRST_NAME, LAST_NAME, PROF, NUMBER_TEL) VALUES(?,?,?,?)");
             )
        {
            stmt.setString(1,jimeno);   
            stmt.setString(2,prijmeni);
            stmt.setString(3,profese);
            stmt.setString(4,telefon);


            stmt.execute();
            
            try (Statement stmt2 = conn.createStatement();
                 ResultSet rs = stmt2.executeQuery("SELECT ID_WORKER FROM WORKER ORDER BY ID_WORKER DESC"))
            {
                rs.next();
                
                return rs.getInt("ID_WORKER");
            }
        }
    }
    
}
