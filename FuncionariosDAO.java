package br.com.fatec.dao;

import br.com.fatec.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import br.com.fatec.persistencia.Banco;

public class FuncionariosDAO implements DAO<Funcionarios> {

    @Override
    public boolean insere(Funcionarios model) throws SQLException {
        String sql = "INSERT INTO tb_funcionarios (CPF, Nome, Funcao, Senha, Data_Nasc, Email, CEP, Logradouro, Numero, Bairro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try (Connection conn = Banco.obterConexao();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            System.out.println("Conexão obtida: " + (conn != null));
            System.out.println("SQL de Inserção: " + sql);

            pst.setLong(1, model.getCpf_func());
            pst.setString(2, model.getNome_func());
            pst.setString(3, model.getFuncao_func());
            pst.setString(4, model.getSenha());
            pst.setInt(5, model.getData_nasc_func());
            pst.setString(6, model.getEmail_func());
            pst.setString(7, model.getCep_func());
            pst.setString(8, model.getLogradouro());
            pst.setInt(9, model.getNumero());
            pst.setString(10, model.getBairro());

            System.out.println("Executando SQL...");
            boolean result = pst.executeUpdate() >= 1;
            System.out.println("Resultado da Inserção: " + result);

            return result;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean remove(Funcionarios model) throws SQLException {
        String sql = "DELETE FROM tb_funcionarios WHERE CPF = ?;";
        
        try (Connection conn = Banco.obterConexao();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            System.out.println("Conexão obtida: " + (conn != null));
            System.out.println("SQL de Remoção: " + sql);

            pst.setLong(1, model.getCpf_func());

            System.out.println("Executando SQL...");
            boolean result = pst.executeUpdate() >= 1;
            System.out.println("Resultado da Remoção: " + result);

            return result;
        } catch (SQLException e) {
            System.err.println("Erro ao remover: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean altera(Funcionarios model) throws SQLException {
        String sql = "UPDATE tb_funcionarios SET Nome = ?, Funcao = ?, Senha = ?, Data_Nasc = ?, Email = ?, CEP = ?, Logradouro = ?, Numero = ?, Bairro = ? WHERE CPF = ?;";
        
        try (Connection conn = Banco.obterConexao();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            System.out.println("Conexão obtida: " + (conn != null));
            System.out.println("SQL de Alteração: " + sql);

            pst.setString(1, model.getNome_func());
            pst.setString(2, model.getFuncao_func());
            pst.setString(3, model.getSenha());
            pst.setInt(4, model.getData_nasc_func());
            pst.setString(5, model.getEmail_func());
            pst.setString(6, model.getCep_func());
            pst.setString(7, model.getLogradouro());
            pst.setInt(8, model.getNumero());
            pst.setString(9, model.getBairro());
            pst.setLong(10, model.getCpf_func());

            System.out.println("Executando SQL...");
            boolean result = pst.executeUpdate() >= 1;
            System.out.println("Resultado da Alteração: " + result);

            return result;
        } catch (SQLException e) {
            System.err.println("Erro ao alterar: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Funcionarios buscaID(Funcionarios model) throws SQLException {
        String sql = "SELECT * FROM tb_funcionarios WHERE CPF = ?;";
        
        try (Connection conn = Banco.obterConexao();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            System.out.println("Conexão obtida: " + (conn != null));
            System.out.println("SQL de Busca: " + sql);

            pst.setLong(1, model.getCpf_func());

            System.out.println("Executando SQL...");
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    model.setNome_func(rs.getString("Nome"));
                    model.setFuncao_func(rs.getString("Funcao"));
                    model.setSenha(rs.getString("Senha"));
                    model.setData_nasc_func(rs.getInt("Data_Nasc"));
                    model.setEmail_func(rs.getString("Email"));
                    model.setCep_func(rs.getString("CEP"));
                    model.setLogradouro(rs.getString("Logradouro"));
                    model.setNumero(rs.getInt("Numero"));
                    model.setBairro(rs.getString("Bairro"));
                    System.out.println("Funcionário encontrado: " + model);
                    return model;
                } else {
                    System.out.println("Funcionário não encontrado");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Collection<Funcionarios> lista(String criterio) throws SQLException {
        String sql = "SELECT * FROM tb_funcionarios WHERE " + criterio;
        Collection<Funcionarios> funcionarios = new ArrayList<>();

        try (Connection conn = Banco.obterConexao();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("Conexão obtida: " + (conn != null));
            System.out.println("SQL de Listagem: " + sql);

            while (rs.next()) {
                Funcionarios model = new Funcionarios(rs.getLong("CPF"));
                model.setNome_func(rs.getString("Nome"));
                model.setFuncao_func(rs.getString("Funcao"));
                model.setSenha(rs.getString("Senha"));
                model.setData_nasc_func(rs.getInt("Data_Nasc"));
                model.setEmail_func(rs.getString("Email"));
                model.setCep_func(rs.getString("CEP"));
                model.setLogradouro(rs.getString("Logradouro"));
                model.setNumero(rs.getInt("Numero"));
                model.setBairro(rs.getString("Bairro"));
                funcionarios.add(model);
            }
            System.out.println("Número de funcionários encontrados: " + funcionarios.size());
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
            throw e;
        }

        return funcionarios;
    }
}
