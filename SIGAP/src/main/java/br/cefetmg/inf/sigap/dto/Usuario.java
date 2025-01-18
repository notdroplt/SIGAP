package br.cefetmg.inf.sigap.dto;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private long cpf;
    private final int id;

    private int autoridade;

    public Usuario(String nome, String email, String senha, long cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.id = 0;
    }

    public Usuario(String nome, String email, String senha, long cpf, int id, int autoridade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.autoridade = autoridade;
        this.id = id;
    }

    public Usuario(String nome, String email, String senha, long cpf, int id) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public int getAutoridade() {
        return autoridade;
    }

    public void setAutoridade(int autoridade) {
        this.autoridade = autoridade;
    }
}
