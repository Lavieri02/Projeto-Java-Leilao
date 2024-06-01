/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    conectaDAO conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
        try{
        
        conn = new conectaDAO();
        conn.connectDB();
        String sql = "INSERT INTO produtos (  nome, valor, status ) VALUES ( ? , ? , ?  );";

            prep = conn.getConn().prepareStatement(sql);

            prep.setString(1, produto.getNome());
           prep.setInt(2, produto.getValor());
           prep.setString(3, produto.getStatus());
            prep.execute();

            conn.desconnectDB();
             JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!!");
            return true;
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!\n   Contate o suporte");

            return false;
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

