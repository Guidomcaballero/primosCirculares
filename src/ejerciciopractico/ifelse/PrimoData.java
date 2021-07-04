/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciopractico.ifelse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Guido Caballero
 */
public class PrimoData {
     private Connection con;
    
    public PrimoData(Conexion conn){
        try {
            this.con =  conn.getConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de conexion");
        }
    }
    
    public void crearTabla(){
        String sql ="CREATE TABLE IF NOT EXISTS primoscirculares(idPrimo SERIAL NOT NULL PRIMARY KEY,nroPrimo int(7))";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crer la tabla");
        }
    }
    public void ingresarPrimo(Primo p){
        String sql="INSERT INTO primoscirculares (nroPrimo) VALUES (?)";       
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getNroCircular());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
                p.setIdPrimo(rs.getInt(1));
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al ingresar el numero");
        }
    }
    public boolean primoEsta(Primo p){
        String sql="SELECT * FROM `primoscirculares` WHERE `nroPrimo`=?";
        boolean esPrimo = false;
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getNroCircular());
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                esPrimo = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al intentar recuperar un elemento de la bd");
        }
        return esPrimo;
    }
    public List<Primo> obtenerPrimos(){
        ArrayList<Primo> primos=new ArrayList<>();        
        String sql="SELECT * FROM `primoscirculares`";
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Primo p = new Primo();
                p.setIdPrimo(rs.getInt("idPrimo"));
                p.setNroCircular(rs.getInt("nroPrimo"));
                primos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de conexion al intentar obtener todos los primos");
        }
        return primos;
    }


}
