package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {

    // metodo responsavel por estabeler a conexao com o banco
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // a linha abixo chama o driver
        String driver = "com.mysql.jdbc.Driver";
        // armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/engsoft?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "";
        // estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
            
        } catch (Exception e) {
            // a linha abaixo serve de apoio para esclarecer o erro
            System.out.println(e);
            return null;
        }
    }
}
