package br.com.fatec.model;

public class Funcionarios {
    private String nome_func;
    private String email_func;
    private int data_nasc_func; 
    private long cpf_func; // Usar long em vez de int
    private String funcao_func;
    private String senha; 
    private String cep_func;
    private String logradouro;
    private String bairro;
    private int numero;

    // Construtor para CPF
    public Funcionarios(long cpf_func) {
        this.cpf_func = cpf_func;
    }

    // Construtor para nome e senha
    public Funcionarios(String nome_func, String senha) {
        this.nome_func = nome_func;
        this.senha = senha;
    }
   
    // Construtor vazio
    public Funcionarios() {
    }

    // Getters e Setters

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getEmail_func() {
        return email_func;
    }

    public void setEmail_func(String email_func) {
        this.email_func = email_func;
    }

    public int getData_nasc_func() {
        return data_nasc_func;
    }

    public void setData_nasc_func(int data_nasc_func) {
        this.data_nasc_func = data_nasc_func;
    }

    public long getCpf_func() {
        return cpf_func;
    }

    public void setCpf_func(long cpf_func) {
        this.cpf_func = cpf_func;
    }

    public String getFuncao_func() {
        return funcao_func;
    }

    public void setFuncao_func(String funcao_func) {
        this.funcao_func = funcao_func;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep_func() {
        return cep_func;
    }

    public void setCep_func(String cep_func) {
        this.cep_func = cep_func;
    }

    @Override
    public String toString() {
        return "Funcionarios{" +
                "cpf_func=" + cpf_func +
                ", nome_func='" + nome_func + '\'' +
                ", funcao_func='" + funcao_func + '\'' +
                ", senha='" + senha + '\'' +
                ", data_nasc_func=" + data_nasc_func +
                ", email_func='" + email_func + '\'' +
                ", cep_func=" + cep_func +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
