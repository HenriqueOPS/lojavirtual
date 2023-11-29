package br.edu.ifrs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private long id =0;
    

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", id=" + id + "]";
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean insert(){
        Conexao bd = new Conexao(); 
        String sql =  "INSERT INTO usuario (id, nome) VALUES (?, ?)";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, this.getId());
            ps.setString(2, this.getNome());
            
            ps.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace(); //Não façam isso em casa crianças
            return  false;
        } finally{
            bd.desconecta();
        }
        return true;
    }

    public boolean update(){
        Conexao bd = new Conexao();    
        String sql = "UPDATE usuario SET nome = ? WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(2, this.getId());
            ps.setString(1, this.getNome());
            
            ps.executeUpdate();     
        } catch (Exception e) {        
            e.printStackTrace(); //Não façam isso em casa crianças
            return false;
        } finally{
            bd.desconecta();
        }
        return true;
    }
    
    public boolean delete(){
        Conexao bd = new Conexao();  
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, this.id);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); //Não façam isso em casa crianças
            return false;
        } finally{
            bd.desconecta();
        }
        return true;
    
    }
    

    public static ArrayList<Usuario> getAll(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Usuario";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                usuarios.add(u);
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return usuarios;
    }

    public boolean load(){

        Conexao bd = new Conexao();  
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {                   
            PreparedStatement ps = bd.getConexao().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               
                this.setNome(rs.getString("nome"));
                return true;
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados");
            e.printStackTrace(); //Não façam isso em casa crianças
        } finally{
            bd.desconecta();
        }
        return false;
    }


    
}
