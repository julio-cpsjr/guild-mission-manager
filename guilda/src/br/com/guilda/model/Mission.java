package br.com.guilda.model;

public class Mission {

    private int id;
    private String nome;
    private DificultMission dificuldade;
    private StatusMission status;

    // Construtor completo (Repository)
    public Mission(int id, String nome,
                  DificultMission dificult,
                  StatusMission status) {
        this.id = id;
        this.nome = nome;
        this.dificuldade = dificult;
        this.status = status;
    }

    // Construtor de criação (UI)
    public Mission(String nome, DificultMission dificult, StatusMission status) {
        this.nome = nome;
        this.dificuldade = dificult;
        this.status = status;
    }

    /* ==========
       Getters
       ========== */
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public DificultMission getDificuldade() {
        return dificuldade;
    }

    public StatusMission getStatus() {
        return status;
    }

    /* ==========
       Regras de negócio
       ========== */
    public void iniciar() {
        if (status != StatusMission.ABERTA) {
            throw new IllegalStateException("Missão não pode ser iniciada.");
        }
        this.status = StatusMission.EM_ANDAMENTO;
    }

    public void concluir() {
        if (status != StatusMission.EM_ANDAMENTO) {
            throw new IllegalStateException("Missão não pode ser concluída.");
        }
        this.status = StatusMission.CONCLUIDA;
    }

    public void falhar() {
        if (status == StatusMission.CONCLUIDA) {
            throw new IllegalStateException("Missão já foi concluída.");
        }
        this.status = StatusMission.FALHOU;
    }
}
