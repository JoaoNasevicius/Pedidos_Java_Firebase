package Interface;

import AcessoBD.acesso;
import Firebase.FirebaseException;
import Firebase.JacksonUtilityException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SingUpController {


    acesso meuBD;

    {
        try {
            meuBD = new acesso();
        } catch (FirebaseException e) {
            e.printStackTrace();
        }
    }

    private File logo;

    @FXML private TextField nomeRestaurante;
    @FXML private TextField userName;
    @FXML private TextField shoppingLocalizacao;
    @FXML private TextField senhaRestaurante;
    @FXML private TextField dadosRestaurante;
    @FXML private TextField urlLogo;
    @FXML private ColorPicker corRestaurante;

    /**
     * Go back to login page
     * @param event information about the panel
     * @throws IOException
     */
    public void handleGoToLogin(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Abre tela para adicionar produto");
        Parent loginPage = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    /**
     * Function to handle when click in the Button to create new Login
     * @param event information about the panel
     * @throws IOException
     * @throws JacksonUtilityException
     * @throws FirebaseException
     */
    public void hangleGoMenu(javafx.event.ActionEvent event) throws IOException, JacksonUtilityException, FirebaseException {
        System.out.println("Novo usuario cadastrado");


        //pega informações de cadastro
        String nome = nomeRestaurante.getText();
        String email = userName.getText();
        String praca = shoppingLocalizacao.getText();
        String senha = senhaRestaurante.getText();
        String dados = dadosRestaurante.getText();
        String url = urlLogo.getText();
        String cor = corRestaurante.getValue().toString();

        meuBD.criarCadastro(nome, senha, dados, email, url, cor);
        meuBD.criaMenu(nome);

        //salva nome restaurante
        File file = new File("userInfo.txt");
        FileWriter W = new FileWriter(file);
        W.write(nome);
        W.close();


        //vai para pagina de pedidos
        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();

    }


}
