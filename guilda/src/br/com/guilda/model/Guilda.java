package br.com.guilda.model;

public class Guilda {

    private int id;
    private String nome;
    private int nivel;
    private int reputacao;

    public Guilda(int id, String nome, int nivel, int reputacao) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.reputacao = reputacao;
    }

    public Guilda(String nome){
        this.nome = nome;
        this.nivel = 1;
        this.reputacao = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getReputacao() {
        return reputacao;
    }

    public void setReputacao(int reputacao) {
        this.reputacao = reputacao;
    }
}
