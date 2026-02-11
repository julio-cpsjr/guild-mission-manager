package br.com.guilda.repository;

import br.com.guilda.db.ConnectionFactory;
import br.com.guilda.model.Guilda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuildaRepository {

    /* ======================
       CREATE
       ====================== */
    public void salvar(Guilda guilda) {
        String sql = """
            INSERT INTO guilda (nome, nivel, reputacao)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guilda.getNome());
            stmt.setInt(2, guilda.getNivel());
            stmt.setInt(3, guilda.getReputacao());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar guilda", e);
        }
    }

    /* ======================
       READ (por ID)
       ====================== */
    public Guilda buscarPorId(int id) {
        String sql = "SELECT * FROM guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return mapearGuilda(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar guilda", e);
        }
    }

    /* ======================
       READ (listar)
       ====================== */
    public List<Guilda> listarTodas() {
        String sql = "SELECT * FROM guilda";
        List<Guilda> guildas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                guildas.add(mapearGuilda(rs));
            }

            return guildas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar guildas", e);
        }
    }

    /* ======================
       UPDATE
       ====================== */
    public void atualizar(Guilda guilda) {
        String sql = """
            UPDATE guilda
            SET nome = ?, nivel = ?, reputacao = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guilda.getNome());
            stmt.setInt(2, guilda.getNivel());
            stmt.setInt(3, guilda.getReputacao());
            stmt.setInt(4, guilda.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar guilda", e);
        }
    }

    /* ======================
       DELETE
       ====================== */
    public void deletar(int id) {
        String sql = "DELETE FROM guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar guilda", e);
        }
    }

    /* ======================
       Mapper
       ====================== */
    private Guilda mapearGuilda(ResultSet rs) throws SQLException {
        return new Guilda(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("nivel"),
                rs.getInt("reputacao")
        );
    }
}
