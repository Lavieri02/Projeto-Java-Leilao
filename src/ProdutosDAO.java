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
import java.util.List;

public class ProdutosDAO {

    conectaDAO conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) throws SQLException {

        try {

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

    public static List<ProdutosDTO> listarProdutos() {
        //Declaração da variável lista que será retornada
        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {

            conectaDAO conexao = new conectaDAO();
            conexao.connectDB();

            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.getConn().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO f = new ProdutosDTO();

                f.setId(resposta.getInt("id"));
                f.setNome(resposta.getString("nome"));

                f.setValor(resposta.getInt("valor"));
                f.setStatus(resposta.getString("status"));

                lista.add(f);
            }

            //Desconectar do banco
            conexao.desconnectDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"""
                               Erro ao listar os registros do banco de dados!
                                          Contate o suporte!""");
        }
        return lista;
    }
     public static List<ProdutosDTO> listarVendas() {
        //Declaração da variável lista que será retornada
        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {

            conectaDAO conexao = new conectaDAO();
            conexao.connectDB();

            String sql = "SELECT * FROM produtos WHERE status LIKE ?";
            PreparedStatement consulta = conexao.getConn().prepareStatement(sql);
             consulta.setString(1, "%" + "Vendido" + "%");

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO f = new ProdutosDTO();

                f.setId(resposta.getInt("id"));
                f.setNome(resposta.getString("nome"));

                f.setValor(resposta.getInt("valor"));
                f.setStatus(resposta.getString("status"));

                lista.add(f);
            }

            //Desconectar do banco
            conexao.desconnectDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"""
                               Erro ao listar os registros do banco de dados!
                                          Contate o suporte!""");
        }
        return lista;
    }

     public static boolean venderProduto(int id) {
        try {
                conectaDAO conexao = new conectaDAO();
            conexao.connectDB();

          
            String sql = "UPDATE produtos SET status=? WHERE id=?;";
            PreparedStatement query = conexao.getConn().prepareStatement(sql);

            
            
            query.setString(1, "Vendido");
            
            query.setInt(2, id);

            
            query.execute();
             JOptionPane.showMessageDialog(null, "Produto vendido!");
           
            conexao.desconnectDB();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender!\n   Contate o suporte\n" + e);
            return false;
        }
    }
}
