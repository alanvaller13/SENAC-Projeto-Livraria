/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.conexaoMysql;
import Model.editoras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author guest01
 */
//DAO = Data Access Objeto = Acesso ao Objetos de Dados
public class editorasDao {

    public void salvar(editoras e) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO editoras (nome, cidade, estado) VALUES (?,?,?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCidade());
            stmt.setString(3, e.getEstado());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Editora Salvo com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(editorasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<editoras> listar() {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<editoras> editoras = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from editoras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                editoras e = new editoras();
                e.setId(rs.getInt("id_editora"));
                e.setNome(rs.getString("nome"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));

                editoras.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(editorasDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return editoras;
    }

    public void alterar(editoras e) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE editoras SET nome = ?, cidade = ?, estado = ? WHERE id_editora = ?");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCidade());
            stmt.setString(3, e.getEstado());
            stmt.setInt(4, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Editora Alterado com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(editorasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(editoras e) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM editoras WHERE id_editora = ?");
            stmt.setInt(1, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Editora Excluida com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(editorasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<editoras> pesquisarDados(String texto) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<editoras> editoras = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from editoras WHERE nome like ?");
            stmt.setString(1, '%' + texto + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                editoras e = new editoras();
                e.setId(rs.getInt("id_editora"));
                e.setNome(rs.getString("nome"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));

                editoras.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(autoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return editoras;
    }

}
