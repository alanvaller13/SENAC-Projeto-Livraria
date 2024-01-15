/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.conexaoMysql;
import Model.livros;
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
public class livrosDao {

    public void salvar(livros l) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (fk_editoras_id_editora, fk_autores_id_autor, Titulo, Descrição, Ano) VALUES (?,?,?,?,?)");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setString(4, l.getDescricao());
            stmt.setInt(5, l.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livros Salvo com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(livrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<livros> listar() {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<livros> livros = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from livros");
            rs = stmt.executeQuery();

            while (rs.next()) {
                livros l = new livros();
                l.setId(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setDescricao(rs.getString("descrição"));
                l.setAno(rs.getInt("Ano"));
                l.setInt(rs.getString("autor"));
                l.setInt(rs.getString("editora"));

                livros.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(editorasDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;
    }

    public void alterar(livros l) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE livros set fk_editora_id_editora = ?, fk_autores_id_autor = ?, titulo = ?, descricao = ?, ano = ? WHERE id_livro = ?");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(4, l.getTitulo());
            stmt.setInt(5, l.getAno());
            stmt.setString(6, l.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro Alterado com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(livrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(livros l) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM livros WHERE id_livro = ?");
            stmt.setInt(1, l.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro Excluido com Sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(livrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<livros> pesquisar(String texto) {

        Connection conn = conexaoMysql.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<livros> livros = new ArrayList();

        try {
            stmt = conn.prepareStatement("SELECT * from livros WHERE nome like ?");
            stmt.setString(1, '%' + texto + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                livros l = new livros();

                l.setId(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));
                l.setDescricao(rs.getString("descicao"));

                livros.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;

    }
}
