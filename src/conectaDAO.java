
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    
    public Connection connectDB(){
       
        
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?autoReconnect=true&useSSL=false", "admin", "1234");
            
        } catch (ClassNotFoundException e) {
             JOptionPane.showMessageDialog(null, "Falha ao carregar a classe de conexão. Classe não encontrada!\n Contate o suporte!");
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    public void desconnectDB() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar.Contate o suporte!");
        }
    }
    
}
