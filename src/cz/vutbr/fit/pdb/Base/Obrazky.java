/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.Base;

import cz.vutbr.fit.pdb.hlavni.DataBase;
import cz.vutbr.fit.pdb.interf.Objekty;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;//https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html
import javax.swing.ImageIcon;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.pool.OracleDataSource;//http://docs.oracle.com/cd/E11882_01/appdev.112/e13995/oracle/jdbc/pool/OracleDataSource.html
import oracle.ord.im.OrdImage;//https://docs.oracle.com/cd/B14099_19/web.1012/b14025/oracle/ord/im/OrdImage.html

/**
 *
 * @author Olga
 */
public class Obrazky extends Base {
    
    
     /**
     * Vlozi obrazek se zadanou cestou "cesta" k bilding
     * @param cesta
     * @param bilding
     * @return ID vlozeneho obrazku
     * @throws SQLException pokud dojde k chybe v pristupe k místnu atributa.
     */
    public Integer insertImage(String cesta, int bilding) throws SQLException {
        
        Integer id;
        
        OracleDataSource ods = DataBase.getConnection();// Объект, который реализует интерфейс DataSource
        try (Connection conn = ods.getConnection();)
        {
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO IMAGES (id, img, BILDING) VALUES (obrazky_seq.nextval, ORDSYS.ORDImage.init(), ?)"); )
            {
                stmt.setInt(1, bilding);
                
                stmt.executeUpdate();
            }
            
            try (Statement stmt = conn.createStatement(); )
            {
                OracleResultSet rs = (OracleResultSet) stmt.executeQuery("SELECT id, img FROM IMAGES ORDER BY id DESC FOR UPDATE");
                
                if (!rs.next()) {
                    return null;
                }
                
                OrdImage imgProxy = (OrdImage) rs.getORAData("img", OrdImage.getORADataFactory());
                id = rs.getInt("id");
                
                rs.close();
                
                try {
                    imgProxy.loadDataFromFile(cesta);
                    imgProxy.setProperties();
                }
                catch (IOException e) {
                    return null;
                }
                
                try (OraclePreparedStatement pstmt = (OraclePreparedStatement) conn.prepareStatement("UPDATE IMAGES SET img = ? WHERE id = ?"))
                {
                    pstmt.setORAData(1, imgProxy);
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();
                }
                
                try (OraclePreparedStatement pstmt = (OraclePreparedStatement) conn.prepareStatement("UPDATE IMAGES o SET o.img_si = SI_StillImage(o.img.getContent()) WHERE id = ?"))
                {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                }
                
                try (OraclePreparedStatement pstmt = (OraclePreparedStatement) conn.prepareStatement("UPDATE IMAGES SET "
                        + "img_ac = SI_AverageColor(img_si), "
                        + "img_ch = SI_ColorHistogram(img_si), "
                        + "img_pc = SI_PositionalColor(img_si), "
                        + "img_tx = SI_Texture(img_si) "
                        + "WHERE id = ?"))
                {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                }
            }
            
            conn.commit();
        }
        
        return id;
    }

  /**
     * Vyhledame obrazky objektu.
     * @param objekt
     * @return Obrazky objektu - klic je ID obrazku v DataBase, hodnota je objekt typu Objekty.
     * @throws SQLException
     */
    public Map<Integer, Objekty> getImagesObjektu(int objekt) throws SQLException {
    
        Map<Integer, Objekty> result = new HashMap<>();
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection();
               OraclePreparedStatement pstmt = (OraclePreparedStatement)conn.prepareStatement("SELECT id, img FROM IMAGES WHERE ID_BILDING = ?"))
        {
            pstmt.setInt(1, objekt);
            
            OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
            
            while (rs.next()) {
                OrdImage img = (OrdImage) rs.getORAData("img", OrdImage.getORADataFactory());
                byte[] tmp = img.getDataInByteArray();
                
                ImageIcon o = new ImageIcon (tmp);          
                Objekty tmpImage = new Objekty(o);
                result.put(rs.getInt("id"), tmpImage);
            }
        } 
        catch (IOException e) {
            result = null;
        }
        
        return result;
    }

      /**
     * Vratime obrazek se zadanym ID.
     * @param id
     * @return Obrazek reprezentovany polem typu byte.
     * @throws SQLException
     */
    public byte[] getImage(Integer id) throws SQLException {
        

        byte[] result;
        OrdImage img;
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection();
             OraclePreparedStatement pstmt = (OraclePreparedStatement)conn.prepareStatement("SELECT img FROM IMAGES WHERE id = ?"))
        {
            pstmt.setInt(1, id);
            
            OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
            
            if (!rs.next()) {
                result = null;
            }
            else {
                img = (OrdImage) rs.getORAData("img", OrdImage.getORADataFactory());

                result = img.getDataInByteArray();
            }
        } 
        catch (IOException e) {
            result = null;
        }
        
        return result;
    }
    
    
     /**
     * Smazeme obrazek se zadanym ID.
     * @param id
     * @return 
     * @throws SQLException
     */
    public boolean delete(Integer id) throws SQLException {
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM IMAGES WHERE id = ?");
             )
        {
            stmt.setInt(1, id);
            return stmt.execute();
        }
    }
    
    /**
     * Otocime obrazek se zadanym ID zavolame procedury, ktere provede otoceni o 90 stupnu
     * @param id
     * @throws SQLException
     */
    public void rotateImage(int id) throws SQLException {
        
        OracleDataSource ods = DataBase.getConnection();
        try (Connection conn = ods.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("CALL Rotate_image(?)");)
        {
            stmt.setInt(1,id);
            
            stmt.execute();
        }
    }
    
        /**
     * Vyhledá nejpodobnější obrázky na základě zadaného obrázku pomocí ID.
     * @param id
     * @param weightAC
     * @param weightCH
     * @param weightPC
     * @param weightTX
     * @return Podobné obrázky - klíč je ID obrázku, hodnota jeobjekt typu myIcon. Obrázky jsou seřazeny podle podobnosti.
     * @throws SQLException
     */
    public Map<Integer, Objekty> getTheMostSimilar(Integer id, double weightAC, double weightCH, double weightPC, double weightTX) throws SQLException {
        Map<Integer, Objekty> result = new LinkedHashMap<>();
        OracleDataSource ods = DataBase.getConnection();
         try (Connection conn = ods.getConnection();
               OraclePreparedStatement pstmt = (OraclePreparedStatement)conn.prepareStatement("SELECT dst.id, dst.img, SI_ScoreByFtrList("
                + "new SI_FeatureList(src.img_ac,?,src.img_ch,?,src.img_pc,?,src.img_tx,?),dst.img_si)"
                + " as similarity FROM obrazky src, obrazky dst "
                + "WHERE src.id = ? AND dst.id <> src.id ORDER BY similarity ASC")
              )
        {
            //pstmt.setInt(1, customer);
            pstmt.setDouble(1, weightAC);
            pstmt.setDouble(2, weightCH);
            pstmt.setDouble(3, weightPC);
            pstmt.setDouble(4, weightTX);
            pstmt.setInt(5, id);
            
            OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
            
            while (rs.next()) {
                OrdImage img = (OrdImage) rs.getORAData("img", OrdImage.getORADataFactory());
                byte[] tmp = img.getDataInByteArray();
                
                ImageIcon o = new ImageIcon(tmp);
                Objekty tmpIcon = new Objekty(o);
                tmpIcon.setScore(rs.getDouble("similarity"));
                result.put(rs.getInt("id"), tmpIcon);
            }
        } 
        catch (IOException e) {
            result = null;
        }
        
        return result;
    }
    
}
    
    
    
    
