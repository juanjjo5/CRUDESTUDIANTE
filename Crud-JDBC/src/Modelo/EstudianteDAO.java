package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Conexion conectar = new Conexion();
    Estudiante p;

    public List listar() {
        List<Estudiante> datos = new ArrayList<>();
        try {
            con = conectar.getConnnection();
            ps = con.prepareStatement("select * from estudiante");
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante p = new Estudiante();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);

            }

        } catch (Exception e) {
            System.out.println(e);

        }
        return datos;
    }

    public int agregar(Estudiante per) {
        int r = 0;
        String sql = "insert into estudiante(nombres,correo,telefono)values (?,?,?)";
        try {
            con = conectar.getConnnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNom());
            ps.setString(2, per.getCorreo());
            ps.setString(3, per.getTelefono());
            r= ps.executeUpdate();
            if(r==1){
            return 1;
            }else{
                return 0;
          }

        } catch (Exception e) {
            System.out.println(e);
        }

        return r;
    }
    
    public int actualizar(Estudiante per){
    int r =0;
    String sql ="update estudiante set nombres=?,correo=?,telefono=? where id =?";
    try{
    con = conectar.getConnnection();
    ps =con.prepareStatement(sql);
    ps.setString(1, per.getNom());
    ps.setString(2, per.getCorreo());
    ps.setString(3, per.getTelefono());
    ps.setInt(4, per.getId());
    r= ps.executeUpdate();
    if(r==1){
        return 1;
    }else{
    return 0;
    }
    }catch(Exception e){
        System.out.println(e);
    }
    return r;
   }
    public int  delete(int id ){
    int r=0;
    String sql="delete  from estudiante where id="+id;
    try{
        con=conectar.getConnnection();
        ps=con.prepareStatement(sql);
        r=ps.executeUpdate();
        if(r==1){
        return 1;
        }else{
        return 0;
        }
        
    }catch(Exception e){
        System.out.println(e);
    }
    
    
    return r;
    }
    
    
}