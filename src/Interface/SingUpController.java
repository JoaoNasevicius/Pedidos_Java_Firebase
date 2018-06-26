package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SingUpController {



    private File logo;

    @FXML
    TextField nomeRestaurante;
    @FXML private TextField userName;
    @FXML private TextField shoppingLocalizacao;
    @FXML private TextField senhaRestaurante;
    @FXML private TextField dadosRestaurante;


    public void handleGoToLogin(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Login");
        Parent loginPage = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void hangleGoMenu(javafx.event.ActionEvent event) throws IOException{
        System.out.println("Novo usuario cadastrado");


        //pega informações de cadastro
        String usuario = userName.getText();
        String praca = shoppingLocalizacao.getText();
        String senha = senhaRestaurante.getText();
        String[] dados = dadosRestaurante.getText().split(",");


        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();

    }

    public void escolheLogo(javafx.event.ActionEvent event){

        FileChooser logoFile = new FileChooser();
        logoFile.setTitle("Escolha o logo logo");
        this.logo= logoFile.showOpenDialog(null);

        String extension = "";
        int i = this.logo.getPath().lastIndexOf('.');
        if (i > 0) {
            extension = this.logo.getPath().substring(i+1);
            if (extension.equals("png")){
                System.out.println("Formato Correto de Image  Inserrida, parabéns filhos");
            }
        }
    }
}