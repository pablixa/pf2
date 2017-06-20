/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * 
 */
public class CidadeDao {
    
    Connection conn = ConnectionFactory.getConnection();
    Statement stmt = null;
    ResultSet rs;
    
    public Map getMunicipios(String uf) throws SQLException {
        
        Map<String, String> muni = new TreeMap<String, String>();
        
        String sql = "select distinct cidade.Cod_IBGE,cidade.nome from cidade where UF='" + uf + "' order by nome ASC";
        System.out.println("DAO.CidadeDao.getMunicipios() sql:"+sql);
        stmt = conn.prepareStatement(sql);
        
        rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            
            
            muni.put(rs.getString(1), rs.getString(2));
        }
        
        return muni;
    }
    
    public List getUFs() throws SQLException {
        List<String> ufs = new ArrayList<String>();
        String sql = "select distinct cidade.uf from cidade order by cidade.uf ";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ufs.add(rs.getString(1));
            
        }
        
        return ufs;
    }
    
    public Cidade pesquisado(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Cidade ci = new Cidade();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `cidade` WHERE `Cod_IBGE`=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ci.setCodIBGE(rs.getInt("Cod_IBGE"));
            ci.setNome(rs.getString("nome"));
            ci.setUF(rs.getString("UF"));
            
        }
        return ci;
    }
}
