package br.com.guilda.db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS guilda (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    nivel INTEGER NOT NULL,
                    reputacao INTEGER NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS membro_guilda (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    classe TEXT NOT NULL,
                    nivel INTEGER NOT NULL,
                    status TEXT NOT NULL,
                    guilda_id INTEGER,
                    FOREIGN KEY (guilda_id) REFERENCES guilda(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS missao_guilda(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    dificuldade TEXT NOT NULL,
                    status TEXT NOT NULL
                )
            """);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar banco", e);
        }
    }
}
