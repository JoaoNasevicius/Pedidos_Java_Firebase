package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguracoesController {


    @FXML TextField nomeCadastrado;
    @FXML TextField emailCadastrado;
    @FXML TextField tipoComidaCadastrado;
    @FXML TextField corCadastrada;
    @FXML PasswordField senhaCadastrada;

    public ConfiguracoesController(){
        //carrega inicialmente os dados do Restaurante
        loadItensUsuario();
    }

    public void handleGoPedidos(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }


    public void handleGoToMenu(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Menu");
        Parent loginPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void handleAttDados(javafx.event.ActionEvent event){

        String nome = nomeCadastrado.getText();
        String email = emailCadastrado.getText();
        String tipoComida = nomeCadastrado.getText();
        String cor = corCadastrada.getText();
        String senha = senhaCadastrada.getText();

        //passa as informações para o banco de dados para atualziar dos dados
    }

    private void loadItensUsuario(){

        System.out.println("Carregando dado dos usuario no banco");

        //chama uma função para pegar os dados do data base dado o nome do Restaurate

    }
}
