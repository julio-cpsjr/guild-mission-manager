package br.com.guilda.repository;

import br.com.guilda.db.ConnectionFactory;
import br.com.guilda.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberGuildaRepository {

    /* ======================
       CREATE
       ====================== */
    public void salvar(MemberGuilda membro) {
        String sql = """
            INSERT INTO membro_guilda
            (nome, classe, nivel, status, guilda_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membro.getNome());
            stmt.setString(2, membro.getClasse().name());
            stmt.setInt(3, membro.getNivel());
            stmt.setString(4, membro.getStatus().name());
            stmt.setInt(5, membro.getGuildaId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar membro da guilda", e);
        }
    }

    /* ======================
       READ (por ID)
       ====================== */
    public MemberGuilda buscarPorId(int id) {
        String sql = "SELECT * FROM membro_guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return mapearMembro(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar membro", e);
        }
    }

    /* ======================
       READ (listar)
       ====================== */
    public List<MemberGuilda> listarTodos() {
        String sql = "SELECT * FROM membro_guilda";
        List<MemberGuilda> membros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                membros.add(mapearMembro(rs));
            }

            return membros;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar membros", e);
        }
    }

    /* ======================
       UPDATE
       ====================== */
    public void atualizar(MemberGuilda membro) {
        String sql = """
            UPDATE membro_guilda
            SET nome = ?, classe = ?, nivel = ?, status = ?, guilda_id = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membro.getNome());
            stmt.setString(2, membro.getClasse().name());
            stmt.setInt(3, membro.getNivel());
            stmt.setString(4, membro.getStatus().name());
            stmt.setInt(5, membro.getGuildaId());
            stmt.setInt(6, membro.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar membro", e);
        }
    }

    /* ======================
       DELETE
       ====================== */
    public void deletar(int id) {
        String sql = "DELETE FROM membro_guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar membro", e);
        }
    }

    /* ======================
       Mapper
       ====================== */
    private MemberGuilda mapearMembro(ResultSet rs) throws SQLException {

        return new MemberGuilda(
                rs.getInt("id"),
                rs.getString("nome"),
                ClassMember.valueOf(rs.getString("classe")),
                rs.getInt("nivel"),
                StatusMember.valueOf(rs.getString("status")),
                rs.getInt("guilda_id")
        );
    }
}
