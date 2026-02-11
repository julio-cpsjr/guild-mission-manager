package br.com.guilda.model;

import jdk.jshell.Snippet;

public class MemberGuilda {
    private int id;
    private String nome;
    private ClassMember classe;
    private int nivel;
    private StatusMember status;
    private int guildaId;

    public MemberGuilda(int id, String nome, ClassMember classe, int nivel, StatusMember status, int guildaId) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.status = status;
        this.guildaId = guildaId;
    }

    public MemberGuilda(String nome, ClassMember classe, int nivel, int guildaId, StatusMember status) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.guildaId = guildaId;
        this.status = status;
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

    public ClassMember getClasse() {
        return classe;
    }

    public void setClasse(ClassMember classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public StatusMember getStatus() {
        return status;
    }

    public void setStatus(StatusMember status) {
        this.status = status;
    }

    public int getGuildaId() {
        return guildaId;
    }

    public void setGuildaId(int guildaId) {
        this.guildaId = guildaId;
    }
}
