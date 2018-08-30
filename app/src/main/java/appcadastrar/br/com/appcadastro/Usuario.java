package appcadastrar.br.com.appcadastro;

import android.graphics.Bitmap;

public class Usuario {

    private String nome;
    private String Sobrenome;
    private String email;
    private String endereco;
    private String cidade;
    private String uf;
    private String username;
    private String senha;
    private Bitmap foto;



    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String email, String endereco, String cidade, String uf, String username, String senha, Bitmap foto) {
        this.nome = nome;
        Sobrenome = sobrenome;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.username = username;
        this.senha = senha;
        this.foto = foto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
