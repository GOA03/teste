package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UsuarioModel; // Importação da classe Usuario

import database.DatabaseConnection;

public class UsuarioDAO {
    
    public UsuarioModel findUserByRA(int ra) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE ra = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioModel(
                        rs.getInt("id"),
                        rs.getInt("ra"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("token"),
                        rs.getBoolean("avisos_esportes"),
                        rs.getBoolean("avisos_clima"),
                        rs.getBoolean("avisos_tecnologia"),
                        rs.getBoolean("avisos_saude"),
                        rs.getBoolean("avisos_economia")
                    );
                }
            }
        }
        return null;
    }

    public void cadastrarNovoUsuario(int ra, String nome, String senha) throws SQLException {
        String query = "INSERT INTO usuarios (ra, nome, senha) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ra);
            stmt.setString(2, nome);
            stmt.setString(3, senha);
            stmt.executeUpdate();
        }
    }

    public void updateAvisos(int ra, boolean esportes, boolean clima, boolean tecnologia, boolean saude, boolean economia) throws SQLException {
        String query = "UPDATE usuarios SET avisos_esportes=?, avisos_clima=?, avisos_tecnologia=?, avisos_saude=?, avisos_economia=? WHERE ra=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, esportes);
            stmt.setBoolean(2, clima);
            stmt.setBoolean(3, tecnologia);
            stmt.setBoolean(4, saude);
            stmt.setBoolean(5, economia);
            stmt.setInt(6, ra);
            stmt.executeUpdate();
        }
    }

    public void atualizarToken(int id, String token) throws SQLException {
        String query = "UPDATE usuarios SET token = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, token);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}
