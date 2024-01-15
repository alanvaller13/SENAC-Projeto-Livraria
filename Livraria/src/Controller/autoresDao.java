/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.conexaoMysql;
import Model.autores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author guest01
 */
//DAO = Data Access Objeto = Acesso ao Objetos de Dados
public class autoresDao {

    /**
     *
     * @param a
     */
    public void salvar(autores a) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO autores (nome, email) VALUES (?,?)");
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEmail());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autores Salvo com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<autores> listar() {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<autores> autores = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from autores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                autores a = new autores();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));

                autores.add(a);

            }

        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;
    }

    public void alterar(autores a) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE autores set nome = ?, email = ? WHERE id_autor = ?");
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEmail());
            stmt.setInt(3, a.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autores Alterado com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(autores a) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM autores WHERE id_autor = ?");
            stmt.setInt(1, a.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor Excluido com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public List<autores> pesquisar(String texto) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<autores> autores = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from autores WHERE nome like ?");
            stmt.setString(1, '%' + texto + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                autores a = new autores();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));

                autores.add(a);

            }

        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;
    }  

    public Iterable<autores> pesquisar(JTextField tfPesquisar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
