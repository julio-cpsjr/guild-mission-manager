package br.com.guilda.repository;

import br.com.guilda.db.ConnectionFactory;
import br.com.guilda.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MissionRepository {

    /* ======================
       CREATE
       ====================== */
    public void salvar(Mission missao) {
        String sql = """
            INSERT INTO missao_guilda
            (nome,  dificuldade, status)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, missao.getNome());
            stmt.setString(2, missao.getDificuldade().name());
            stmt.setString(3, missao.getStatus().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar missão", e);
        }
    }

    /* ======================
       READ (por ID)
       ====================== */
    public Mission buscarPorId(int id) {
        String sql = "SELECT * FROM missao_guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return mapearMission(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar missão", e);
        }
    }

    /* ======================
       READ (listar)
       ====================== */
    public List<Mission> listarTodas() {
        String sql = "SELECT * FROM missao_guilda";
        List<Mission> missoes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                missoes.add(mapearMission(rs));
            }

            return missoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar missões", e);
        }
    }

    /* ======================
       UPDATE - status
       ====================== */
    public void atualizarStatus(int MissionId, StatusMission status) {
        String sql = "UPDATE missao_guilda SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, MissionId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status", e);
        }
    }

    /* ======================
       UPDATE - atribuir membro
       ====================== */
    public void atribuirMembro(int MissionId, int membroId) {
        String sql = "UPDATE missao_guilda SET membro_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membroId);
            stmt.setInt(2, MissionId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atribuir membro", e);
        }
    }

    /* ======================
       UPDATE - finalizar
       ====================== */
    public void finalizar(int MissionId, StatusMission status, LocalDate dataFechamento) {
        String sql = """
            UPDATE missao_guilda
            SET status = ?, data_fechamento = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setDate(2, Date.valueOf(dataFechamento));
            stmt.setInt(3, MissionId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao finalizar missão", e);
        }
    }

    /* ======================
       DELETE
       ====================== */
    public void deletar(int id) {
        String sql = "DELETE FROM missao_guilda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar missão", e);
        }
    }

    /* ======================
       DROP
       ====================== */
    public void drop(int id) {
        String sql = "DROP TABLE missao_guilda";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar missão", e);
        }
    }

    /* ======================
       Mapper
       ====================== */
    private Mission mapearMission(ResultSet rs) throws SQLException {

        return new Mission(
                rs.getInt("id"),
                rs.getString("nome"),
                DificultMission.valueOf(rs.getString("dificuldade")),
                StatusMission.valueOf(rs.getString("status"))
        );
    }
}
