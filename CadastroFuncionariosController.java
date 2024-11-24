package br.com.fatec.controller;

import br.com.fatec.dao.FuncionariosDAO;
import br.com.fatec.model.Funcionarios;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroFuncionariosController implements Initializable {
    
    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtDataNasc;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnGravar;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtFuncao;

    @FXML
    private TextField txtCPF;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtBairro;
    
    private FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private Funcionarios funcionario;
    private boolean incluindo = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializações se necessárias
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        System.out.println("Botão Pesquisar clicado");
        funcionario = new Funcionarios(0);
        funcionario.setCpf_func(Long.parseLong(txtCPF.getText().trim()));
        
        try {
            funcionario = funcionariosDAO.buscaID(funcionario);
            if(funcionario != null) { 
                carregar_View(funcionario);
                habilitarBotoes(false);
                incluindo = false;
            } else {
                mensagem("Funcionário não encontrado");
                txtCPF.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na procura do CPF: " + ex.getMessage());
            System.err.println("Erro na procura do CPF: " + ex.getMessage());
        }
    }

    @FXML
    private void btnGravar_Click(ActionEvent event) {
        System.out.println("Botão Gravar clicado");
        if (!validarDados()) {
            return;
        }

        funcionario = carregar_Model();
        System.out.println("Model carregado: " + funcionario);
    
        try {
            if (incluindo) { 
                if (funcionariosDAO.insere(funcionario)) {
                    mensagem("Funcionário incluído com sucesso!!!");
                    limparDados();
                } else {
                    mensagem("Erro na Inclusão");
                }
            } else { 
                System.out.println("Tentando alterar dados do funcionário...");
                if (funcionariosDAO.altera(funcionario)) {
                    mensagem("Funcionário alterado com sucesso!!!");
                    limparDados();
                } else {
                    mensagem("Erro na Alteração");
                }
            }
        } catch (SQLException ex) {
            mensagem("Erro na Gravação\n" + ex.getMessage());
            System.err.println("Erro na Gravação: " + ex.getMessage());
        }
    }


    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        System.out.println("Botão Excluir clicado");
        funcionario = new Funcionarios(0);
        funcionario.setCpf_func(Long.parseLong(txtCPF.getText().trim()));
        
        try {
            if (funcionariosDAO.remove(funcionario)) {
                mensagem("Funcionário excluído com sucesso!");
                limparDados();
            } else {
                mensagem("Ocorreu algum erro para exclusão");
            }
        } catch (SQLException ex) {
            mensagem("Erro de Exclusão\n" + ex.getMessage());
            System.err.println("Erro de Exclusão: " + ex.getMessage());
        }
    }

    @FXML
    private void btnFechar_Click(ActionEvent event) {
        System.out.println("Botão Fechar clicado");
        Stage st = (Stage) btnFechar.getScene().getWindow();
        st.close();
    }
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        alerta.showAndWait();
    }

    private Funcionarios carregar_Model() {
        try {
            String cpfTexto = txtCPF.getText().trim().replaceAll("[^\\d]", "");
            if (cpfTexto.isEmpty()) {
                System.err.println("O campo CPF está vazio.");
                return null;
            }
            long cpf = Long.parseLong(cpfTexto); // Use long em vez de int
            Funcionarios model = new Funcionarios(cpf);
            
            System.out.println("CPF: " + cpf);

            String nome = txtNome.getText();
            System.out.println("Nome: " + nome);
            model.setNome_func(nome);

            String cep = (txtCEP.getText());
            System.out.println("CEP: " + cep);
            model.setCep_func(cep);

            String email = txtEmail.getText();
            System.out.println("Email: " + email);
            model.setEmail_func(email);

            String funcao = txtFuncao.getText();
            System.out.println("Função: " + funcao);
            model.setFuncao_func(funcao);

            String senha = txtSenha.getText();
            System.out.println("Senha: " + senha);
            model.setSenha(senha);

            int dataNasc = Integer.parseInt(txtDataNasc.getText().trim());
            System.out.println("Data de Nascimento: " + dataNasc);
            model.setData_nasc_func(dataNasc);

            String logradouro = txtLogradouro.getText();
            System.out.println("Logradouro: " + logradouro);
            model.setLogradouro(logradouro);

            int numero = Integer.parseInt(txtNumero.getText().trim());
            System.out.println("Número: " + numero);
            model.setNumero(numero);

            String bairro = txtBairro.getText();
            System.out.println("Bairro: " + bairro);
            model.setBairro(bairro);

            return model;
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    private void carregar_View(Funcionarios model) {
        txtCPF.setText(String.valueOf(model.getCpf_func()));
        txtNome.setText(model.getNome_func());
        txtCEP.setText(String.valueOf(model.getCep_func()));
        txtEmail.setText(model.getEmail_func());
        txtFuncao.setText(model.getFuncao_func());
        txtSenha.setText(model.getSenha());
        txtDataNasc.setText(String.valueOf(model.getData_nasc_func()));
        txtLogradouro.setText(model.getLogradouro());
        txtNumero.setText(String.valueOf(model.getNumero()));
        txtBairro.setText(model.getBairro());

        System.out.println("Dados carregados para a View:");
        System.out.println("CPF: " + model.getCpf_func());
        System.out.println("Nome: " + model.getNome_func());
        System.out.println("CEP: " + model.getCep_func());
        System.out.println("Email: " + model.getEmail_func());
        System.out.println("Função: " + model.getFuncao_func());
        System.out.println("Senha: " + model.getSenha());
        System.out.println("Data Nasc.: " + model.getData_nasc_func());
        System.out.println("Logradouro: " + model.getLogradouro());
        System.out.println("Número: " + model.getNumero());
        System.out.println("Bairro: " + model.getBairro());
    }

    private boolean validarDados() {
        System.out.println("Validando dados...");
        if (txtCPF.getText().length() == 0 ||
            txtBairro.getText().length() == 0 ||
            txtCEP.getText() == null ||
            txtDataNasc.getText().length() == 0 || 
            txtEmail.getText().length() == 0 ||
            txtFuncao.getText().length() == 0 ||
            txtLogradouro.getText().length() == 0 ||
            txtNome.getText().length() == 0 ||
            txtNumero.getText().length() == 0 ||
            txtSenha.getText().length() == 0) {
            mensagem("Por favor, preencher todos os dados");
            System.out.println("Dados inválidos.");
            return false;
        } else {
            System.out.println("Dados validados com sucesso.");
            return true;
        }
    }

    public void habilitarBotoes(boolean inclusao) {
        btnExcluir.setDisable(inclusao);
        System.out.println("Botões habilitados: " + !inclusao);
    }

    public void limparDados() {
        System.out.println("Limpando dados...");
        txtBairro.setText("");
        txtCEP.setText("");
        txtCPF.setText("");
        txtDataNasc.setText("");
        txtEmail.setText("");
        txtFuncao.setText("");
        txtLogradouro.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtSenha.setText("");
        
        habilitarBotoes(true);
        txtCPF.requestFocus();
    }
}
